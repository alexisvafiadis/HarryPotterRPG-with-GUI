package Core.Characters;

import Core.Characters.Characteristics.House;
import Core.Characters.Wizard;
import Core.Game.Game;
import Core.Magic.ActiveEffect;
import Core.Magic.EffectType;
import Core.Magic.Potion;
import Core.Magic.PotionType;
import Core.Magic.Spells.WingardiumLeviosa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WizardTest {
    Wizard wizard;
    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        wizard = game.getPlayer();
        wizard.setDefaultAttributes();
        wizard.spawn();
    }

    @Test
    void damage() {
        wizard.damage(40);
        assertTrue(wizard.isAlive());
        wizard.damage(200);
        assertTrue(!wizard.isAlive());
    }

    @Test
    void amplifyDamage() {
        double damage = wizard.amplifyDamage(200.0);
        assertTrue(damage >= 200);
    }

    @Test
    void amplifySpellDamage() {
        double damage = wizard.amplifySpellDamage(200.0);
        assertTrue(damage >= 200);
    }

    @Test
    void defendDamage() {
        double damage = wizard.defendDamage(200.0);
        assertTrue(damage <= 200);
    }

    @Test
    void learnSpell() {
        wizard.learnSpell(new WingardiumLeviosa(game, wizard));
    }

    @Test
    void knowsSpell() {
        wizard.learnSpell(new WingardiumLeviosa(game, wizard));
        assertTrue(wizard.knowsSpell("Wingardium Leviosa"));
    }

    @Test
    void giveEffect() {
        wizard.giveEffect(EffectType.BURN, new ActiveEffect(5,2));
        assertEquals(wizard.hasEffect(EffectType.BURN), true);
    }

    @Test
    void doEffectsGoAway() {
        int duration = 4;
        for (EffectType effectType : EffectType.values()) {
            wizard.giveEffect(effectType, new ActiveEffect(duration, 2));
        }
        for (int i = 0 ; i <= duration ; i++) {
            wizard.finishRound();
        }
        for (EffectType effectType : EffectType.values()) {
            assertFalse(wizard.hasEffect(effectType), "The effect " + effectType.toString() + " doesn't go away.");
        }
    }

    @Test
    void doPotionsWork() {
        for (PotionType potionType : PotionType.values()) {
            if ((potionType.equals(PotionType.WIGGENWELD_POTION) ||potionType.equals(PotionType.ESSENCE_OF_DITTANY))) {
                continue;
            }
            Potion potion = new Potion(game, wizard, potionType);
            wizard.addPotion(potion);
            wizard.consumePotion(potion);
            assertTrue(wizard.hasEffect(potionType.getEffect()), "The potion " + potionType.toString() + " doesn't give the effect it is supposed to");
        }
    }

}