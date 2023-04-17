package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.ActiveEffect;
import alexis.isep.harrypotter.Core.Magic.EffectType;
import alexis.isep.harrypotter.Core.Magic.SimpleSpell;

public class Confundus extends SimpleSpell {
    private final int EFFECT_DURATION = 4;

    public Confundus(Game game, Character wizard) {
        super(game, wizard, "Confundus", 5, 1, 0.38, 0.24);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful()) {
            target.giveEffect(EffectType.CONFUSION, new ActiveEffect(EFFECT_DURATION, 0.6));
            displayCastMessage("visibly disoriented " + target.getName());
        }
    }

    public void displayInstructions() {
        inputParser.waitForYes("Point your wand at your opponent and say \"Confundus\" clearly.\n" +
                "If the spell is successful, your opponent should become confused and disoriented.\n" +
                "This spell can be useful for confusing opponents and creating an opportunity to attack or escape.\n" +
                "Be careful not to use it too much, as it can cause unintended consequences or lead to a loss of points." +
                "Understood?");
    }
}
