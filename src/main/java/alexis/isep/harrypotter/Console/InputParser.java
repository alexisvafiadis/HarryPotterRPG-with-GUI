package alexis.isep.harrypotter.Console;

import alexis.isep.harrypotter.Core.Game.Game;

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

    public void waitForYes(String question) {
        HashMap<Integer, String> validInputs = new HashMap<>();
        validInputs.put(1, "Yes");
        validInputs.put(0, "No");
        int choice = 1;
        do {
            if (choice == 0) { display.displayInfo("Oh. Let me explain again then."); }
            choice = getNumberInput(question, validInputs, "for");
        } while (choice != 1);
    }

    public Scanner getSc() {
        return sc;
    }
}
