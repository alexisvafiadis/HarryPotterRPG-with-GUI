package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.Core.Characters.EnemyWizard;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.ActiveEffect;
import alexis.isep.harrypotter.Core.Magic.EffectType;
import alexis.isep.harrypotter.Core.Magic.SimpleSpell;
import javafx.scene.paint.Color;

public class Expelliarmus extends SimpleSpell {
    private final int EFFECT_DURATION = 3;

    public Expelliarmus(Game game, Character wizard) {
        super(game, wizard, "Expelliarmus", Color.GOLD, 5, 1, 0.66, 0.29);
    }

    public void cast(Character target) {
        if (!target.hasWeapon() && !((target instanceof EnemyWizard) || (target instanceof Wizard))) {
            displayCustomFailMessage("failed to cast Expelliarmus because " + target.getName() + " doesn't have a weapon.");
            wizard.decideWhichRoundAction();
        }
        else {
            use();
            if (isCastSuccessful(target)) {
                showSuccessfulCast(null);
                target.giveEffect(EffectType.DISARM, new ActiveEffect(EFFECT_DURATION, 1));
            }
        }
    }

    @Override
    public void displayInstructions() {
        inputParser.waitForConfirmation("To cast this spell, point your wand at your opponent and say \"Expelliarmus\" clearly.\n" +
                "If the spell is successful, your opponent's weapon should be knocked out of their hand.\n");
        display.displayInfo("This spell can be useful for disarming opponents and leaving them vulnerable to attack.");
    }
}
