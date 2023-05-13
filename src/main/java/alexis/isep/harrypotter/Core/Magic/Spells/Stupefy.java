package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.ActiveEffect;
import alexis.isep.harrypotter.Core.Magic.EffectType;
import alexis.isep.harrypotter.Core.Magic.SimpleSpell;
import javafx.scene.paint.Color;

public class Stupefy extends SimpleSpell {
    private final int EFFECT_DURATION = 3;

    public Stupefy(Game game, Character wizard) {
        super(game, wizard, "Stupefy", Color.RED, 5, 1, 0.58, 0.28);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            showSuccessfulCast(" hit " + target.getName() + " with a blast of red light, dropping to the ground.");
            target.giveEffect(EffectType.STUN, new ActiveEffect(EFFECT_DURATION, 0.59));
        }
    }

    public void displayInstructions() {
        inputParser.waitForConfirmation("Focus your mind on the target you wish to stun and visualize the stunning impact.\n" +
                "Say Stupefy with conviction while flicking your wand in a powerful straight motion\n" +
                "A bright light, red in color, will shoot from your wand and hit the target, causing them to become momentarily stunned and unable to move.\n");
    }
}
