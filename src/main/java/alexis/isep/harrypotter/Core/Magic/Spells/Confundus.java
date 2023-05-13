package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.ActiveEffect;
import alexis.isep.harrypotter.Core.Magic.EffectType;
import alexis.isep.harrypotter.Core.Magic.SimpleSpell;
import javafx.scene.paint.Color;

public class Confundus extends SimpleSpell {
    private final int EFFECT_DURATION = 4;

    public Confundus(Game game, Character wizard) {
        super(game, wizard, "Confundus", Color.LIGHTBLUE, 5, 1, 0.39, 0.24);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful()) {
            showSuccessfulCast("visibly disoriented " + target.getName());
            target.giveEffect(EffectType.CONFUSION, new ActiveEffect(EFFECT_DURATION, 0.6));
        }
    }

    public void displayInstructions() {
        inputParser.waitForConfirmation("Point your wand at your opponent and say \"Confundus\" clearly.\n" +
                "If the spell is successful, your opponent should become confused and disoriented.\n" +
                "This spell can be useful for confusing opponents and creating an opportunity to attack or escape.\n");
    }
}
