package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.ActiveEffect;
import alexis.isep.harrypotter.Core.Magic.EffectType;
import alexis.isep.harrypotter.Core.Magic.SimpleSpell;
import javafx.scene.paint.Color;

public class Fiendfyre extends SimpleSpell {
    private final double DAMAGE = 30;
    private final int EFFECT_DURATION = 3;

    public Fiendfyre(Game game, Character wizard) {
        super(game, wizard, "Fiendfyre", Color.DARKRED,5, 1, 0.19, 0.15);
        setForbidden(true);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            target.damage(calculateDamage(DAMAGE));
            showSuccessfulCast("the cursed flames erupted, consuming everything in their path with an inferno of heat and destruction.");
            target.giveEffect(EffectType.BURN, new ActiveEffect(EFFECT_DURATION, calculateDamage(DAMAGE)));
        }
    }

    @Override
    public void displayInstructions() {

    }
}
