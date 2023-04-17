package Core.Magic;

import alexis.isep.harrypotter.Core.Characters.AbstractEnemy;
import alexis.isep.harrypotter.Core.Characters.Enemies.Troll;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Levels.Essentials.Battle;
import alexis.isep.harrypotter.Core.Magic.EffectType;
import alexis.isep.harrypotter.Core.Magic.Spells.Confundus;
import alexis.isep.harrypotter.Core.Magic.Spells.Protego;
import alexis.isep.harrypotter.Core.Magic.Spells.Sectumsempra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleSpellTest {
    Wizard wizard;
    AbstractEnemy enemy;
    Game game;
    Confundus confondus; //offensive effect spell
    Sectumsempra sectumsempra; //damage spell
    Protego protego; //defensive effect spell
    Battle battle;

    @BeforeEach
    void setUp() {
        game = new Game();
        wizard = game.getPlayer();
        wizard.setDefaultAttributes();
        wizard.upgradeAccuracy(10);
        wizard.spawn();
        enemy = new Troll(game);
        enemy.spawn();
        confondus = new Confundus(game, wizard);
        confondus.setNbOfUses(1000);
        sectumsempra = new Sectumsempra(game, wizard);
        sectumsempra.setNbOfUses(1000);
        protego = new Protego(game, wizard);
        protego.setNbOfUses(1000);
    }

    @Test
    void doesSpellWork() {
        confondus.cast(enemy);
        assertTrue(enemy.hasEffect(EffectType.CONFUSION), "Offensive effect spells do not work");
        double previousHP = enemy.getHP();
        sectumsempra.cast(enemy);
        assertTrue((enemy.getHP() < previousHP) || !enemy.isAlive());
        protego.cast(enemy);
        assertTrue(enemy.hasEffect(EffectType.SHIELD), "Defensive effect spells do not work");
    }

    @Test
    void calculateDamage() {
        assertNotNull(confondus.calculateDamage(10));
        assertNotNull(sectumsempra.calculateDamage(10));
        assertNotNull(protego.calculateDamage(10));
    }

    @Test
    void getMasteryScore() {
        assertTrue(confondus.getMasteryScore() != 0);
        assertTrue(sectumsempra.getMasteryScore() != 0);
        assertTrue(protego.getMasteryScore() != 0);
    }
}
