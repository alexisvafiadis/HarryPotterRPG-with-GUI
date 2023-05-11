package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.Spell;
import javafx.scene.paint.Color;

public class Expectopatronum extends Spell {

    public Expectopatronum(Game game, Wizard wizard) {
        super(game, wizard, "Expecto Patronum", Color.WHITE, 5, 1, 0.05, 0);
    }

    public boolean cast() {
        use();
        boolean isCastSuccessful = isCastSuccessful();
        if (isCastSuccessful) {
            showSuccessfulCast("summoned a glowing, silvery animal called Patronus to repel the Dementors.");
        }
        return isCastSuccessful;
    }

    @Override
    public void displayInstructions() {
        inputParser.waitForConfirmation("When casting Expecto Patronum, focus on a positive memory that makes you feel happy and confident.\n" +
                "Point your wand in front of you and make a sweeping motion upward as you say the incantation.\n" +
                "This spell can be useful for confusing opponents and creating an opportunity to attack or escape.\n" +
                "A silvery-white mist will appear, and as you continue to focus on your happy memory, the mist will take the shape of an animal, your Patronus." +
                "Understood?");
    }
}
