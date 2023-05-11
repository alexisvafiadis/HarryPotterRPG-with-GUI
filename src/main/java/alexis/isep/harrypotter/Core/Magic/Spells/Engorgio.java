package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Items.Item;
import javafx.scene.paint.Color;

public class Engorgio extends ItemSpell {
    //WILL NEED TO IMPLEMENT A MAXIMUM SIZE THAT THE ITEM CAN REACH OTHERWISE THE SPELL IS OVERPOWERED
    private final double ENLARGE_COEFFICIENT = 2.25;

    public Engorgio(Game game, Wizard wizard) {
        super(game, wizard, "Engorgio", Color.ORANGE,5, 1, 0.36, 0.23,"to engorge");
    }

    public void cast(Item item) {
        use();
        if (isCastSuccessful()) {
            showSuccessfulCast("enlarged this " + item.getItemType().toString());
            item.getItemType().enlarge(ENLARGE_COEFFICIENT);
        }
    }

    @Override
    public void displayInstructions() {
        inputParser.waitForConfirmation("First, point your wand at the object you want to enlarge and say \"Engorgio\" clearly.\n" +
                "If the spell is successful, the object should become larger and more cumbersome. It can be useful to amplify the damage an object deals to enemies.\n" +
                "Be careful not to overuse this spell, as it can also make objects too big to control.\n");
    }
}
