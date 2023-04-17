package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Levels.Essentials.LevelMap;
import alexis.isep.harrypotter.Core.Magic.Spell;

public class Lumos extends Spell {

    public Lumos(Game game, Character wizard) {
        super(game, wizard, "Lumos", 5, 1, 1, 0.45);
    }

    public void cast(LevelMap map) {
        use();
        if (isCastSuccessful()) {
            displayCastMessage("see your wand light up and illuminate the area around you, revealing new hidden details.");
            display.displayInfo("Map of the Level: ");
            map.display();
        }
    }

    public void displayInstructions() {
        inputParser.waitForYes("Hold your wand tightly in your hand.\n" +
                "Focus on the tip of your wand and visualize a bright light appearing there.\n" +
                "Say Lumos in a clear and firm voice while pointing your wand upward.\n" +
                "A bright light should appear at the tip of your wand, illuminating the area around you.\n" +
                "Understood?");
    }
}
