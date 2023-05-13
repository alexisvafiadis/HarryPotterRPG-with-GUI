package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Game.LevelMap;
import alexis.isep.harrypotter.Core.Magic.Spell;
import javafx.scene.paint.Color;

public class Lumos extends Spell {
    private final int LIGHT_DURATION = 3;

    public Lumos(Game game, Character wizard) {
        super(game, wizard, "Lumos", Color.LIGHTYELLOW,5, 1, 1, 0.45);
    }

    public void cast(LevelMap map) {
        use();
        if (isCastSuccessful()) {
            showSuccessfulCast("see your wand light up and temporarily illuminate the area around you, revealing new hidden details.");
            display.setOnFinish((e) -> { map.display(LIGHT_DURATION); });
        }
    }

    public void displayInstructions() {
        inputParser.waitForConfirmation("First, hold your wand tightly in your hand.\n" +
                "Focus on the tip of your wand and visualize a bright light appearing there.\n" +
                "Say Lumos in a clear and firm voice while pointing your wand upward.\n" +
                "A bright light should appear at the tip of your wand, illuminating the area around you.\n");
    }
}
