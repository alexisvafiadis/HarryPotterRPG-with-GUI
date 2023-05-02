package alexis.isep.harrypotter.Console;

import alexis.isep.harrypotter.GUI.Display;
import alexis.isep.harrypotter.GUI.Game;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputParser {
    private Scanner sc;
    private Game game;
    private Display display;

    public InputParser(Game game, Scanner sc) {
        this.game = game;
        this.display = game.getDisplay();
        this.sc = sc;
    }

    public String getNumberToStringInput(String request, HashMap<Integer, String> validInputs, String linkword) {
        boolean validInput = false;
        do {
            display.askForNumberToStringInput(request, validInputs, linkword);
            try{
                sc.nextLine();
                int input = sc.nextInt();
                if (validInputs.containsKey(input)) {
                    return validInputs.get(input);
                }
            } catch(InputMismatchException e) {

            }
        } while (!validInput);
        return "";
    }

    public int getNumberInput(String request, HashMap<Integer, String> validInputs, String linkword) {
        boolean validInput = false;
        do {
            display.askForNumberToStringInput(request, validInputs, linkword);
            try{
                sc.nextLine();
                int input = sc.nextInt();
                if (validInputs.containsKey(input)) {
                    return input;
                }
            } catch(InputMismatchException e) {

            }

        } while (!validInput);
        return 0;
    }

    public void waitForConfirmation(String question) {
        display.displayInfo(question + "\n" + "Press enter if you understand.");
    }

    public Scanner getSc() {
        return sc;
    }
}
