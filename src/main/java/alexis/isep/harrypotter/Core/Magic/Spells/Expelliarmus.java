package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.Core.Characters.EnemyWizard;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.ActiveEffect;
import alexis.isep.harrypotter.Core.Magic.EffectType;
import alexis.isep.harrypotter.Core.Magic.SimpleSpell;

public class Expelliarmus extends SimpleSpell {
    private final int EFFECT_DURATION = 3;

    public Expelliarmus(Game game, Character wizard) {
        super(game, wizard, "Expelliarmus", 5, 1, 0.66, 0.29);
    }

    public void cast(Character target) {
        if (!target.hasWeapon() && !(target instanceof EnemyWizard)) {
            display.announceFail(target.getName() + " doesn't have a weapon");
        }
        else {
            use();
            if (isCastSuccessful(target)) {
                target.giveEffect(EffectType.DISARM, new ActiveEffect(EFFECT_DURATION, 1));
                displayCastMessage(null);
            }
        }
    }

    @Override
    public void displayInstructions() {
        inputParser.waitForConfirmation("To cast this spell, point your wand at your opponent and say \"Expelliarmus\" clearly.\n" +
                "If the spell is successful, your opponent's weapon should be knocked out of their hand.\n" +
                "This spell can be useful for disarming opponents and leaving them vulnerable to attack.\n"
                + "Understood?");
    }
}
