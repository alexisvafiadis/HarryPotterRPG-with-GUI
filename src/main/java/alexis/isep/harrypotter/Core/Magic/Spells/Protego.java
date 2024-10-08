package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.ActiveEffect;
import alexis.isep.harrypotter.Core.Magic.EffectType;
import alexis.isep.harrypotter.Core.Magic.SimpleSpell;
import javafx.scene.paint.Color;

public class Protego extends SimpleSpell {
    private final int EFFECT_DURATION = 2;

    public Protego(Game game, Character wizard) {
        super(game, wizard, "Protego", Color.LIGHTBLUE, 5, 1, 0.63, 0.29);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful()) {
            showSuccessfulCast("created a shimmering, transparent shield that can block most spells.");
            wizard.giveEffect(EffectType.SHIELD, new ActiveEffect(EFFECT_DURATION, 0.8));
        }
    }

    public void displayInstructions() {
        inputParser.waitForConfirmation("Point your wand in the direction of potential attacks and make a circular motion while saying \"Protego\"\n" +
                "A translucent shield will appear, which will protect you from the incoming attack.\n" +
                "Remember to stay careful and act quickly, as the shield will only last for a short time.");
    }
}
