package alexis.isep.harrypotter.Core.Game;

import alexis.isep.harrypotter.Core.Characters.Characteristics.House;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.GUI.Game;

import java.util.HashMap;

public class SortingHat{
    public void askHouse(Game game, Wizard wizard) {
        //Note : Made it at the very start, so that' why it's great code quality
        HashMap<Integer, String> validInputs = new HashMap<>();
        validInputs.put(1, "Gryffindor");
        validInputs.put(2, "Hufflepuff");
        validInputs.put(3, "Ravenclaw");
        validInputs.put(4, "Slytherin");

        String houseName = game.getInputParser().getNumberToStringInput("It's time to choose your house.", validInputs, "for");
        game.getDisplay().displayInfo("You chose the house " + houseName + ". Have fun!");
        wizard.setHouse(House.valueOf(houseName.toUpperCase()));
    }
}
