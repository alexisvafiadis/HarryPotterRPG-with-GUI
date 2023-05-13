package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.ActiveEffect;
import alexis.isep.harrypotter.Core.Magic.EffectType;
import alexis.isep.harrypotter.Core.Magic.SimpleSpell;
import javafx.scene.paint.Color;

public class Rictumsempra extends SimpleSpell {
    private final int EFFECT_DURATION = 3;

    public Rictumsempra(Game game, Character wizard) {
        super(game, wizard, "Rictumsempra", Color.PINK,5, 1, 0.41, 0.25);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            showSuccessfulCast(target.getName() + "'s contorted into a grimace of pain and mirth");
            target.giveEffect(EffectType.LAUGH, new ActiveEffect(EFFECT_DURATION, 0.6));
        }
    }

    @Override
    public void displayInstructions() {
        inputParser.waitForConfirmation("First, make sure you have your wand at the ready and choose your target carefully.\n" +
                "Point your wand at your target and clearly enunciate Rictumsempra while flicking your wand in a zig-zag motion.\n" +
                "This  spell is a jinx that causes your target to experience a tickling sensation and laugh, so it's a relatively harmless spell.");
    }
}
