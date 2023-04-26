package alexis.isep.harrypotter.Core.Game;

import alexis.isep.harrypotter.GUI.Display;
import alexis.isep.harrypotter.Console.InputParser;
import alexis.isep.harrypotter.Core.Characters.Characteristics.Pet;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Items.Wand;
import alexis.isep.harrypotter.Core.Items.WandCore;
import alexis.isep.harrypotter.Core.Items.Wood;
import alexis.isep.harrypotter.Core.Magic.Spells.Confundus;
import alexis.isep.harrypotter.Core.Magic.Spells.Engorgio;
import alexis.isep.harrypotter.Core.Magic.Spells.Expelliarmus;
import alexis.isep.harrypotter.GUI.Game;

import java.util.HashMap;
import java.util.Random;

public class WizardMaker {
    Game game;
    Display display;
    InputParser inputParser;
    Wizard player;

    public WizardMaker(Game game) {
        this.game = game;
        display = game.getDisplay();
        inputParser = game.getInputParser();
        player = game.getPlayer();
        askName();
        display.displayInfo("Welcome to Hogwarts, " + player.getName());
        display.displayInfo("Now let's get some things sorted.");
        askPet();
        makeWand();
        teachBasicSpells();
    }

    public void askName() {
        display.displayRequest("Hello, what would you like your wizard name to be?");
        player.setName(inputParser.getSc().nextLine());
    }

    public void askPet() {
        HashMap<Integer, String> validInputs = new HashMap<>();
        validInputs.put(1, "Owl");
        validInputs.put(2, "Cat");
        validInputs.put(3, "Rat");
        validInputs.put(4, "Toad");

        String petName = inputParser.getNumberToStringInput("Choose your pet.", validInputs, "for");
        display.displayInfo("You chose the pet " + petName + ". I hope you two will get along!");
        game.getPlayer().setPet(Pet.valueOf(petName.toUpperCase()));
    }

    public void makeWand() {
        display.displayInfo("It's now time to give you your wand. Let me search for the right one for you... ");
        Random random = new Random();
        int wandCoreIndex = random.nextInt(WandCore.values().length);
        WandCore wandCore = WandCore.values()[wandCoreIndex];
        int woodIndex = random.nextInt(Wood.values().length);
        Wood wood = Wood.values()[woodIndex];
        player.setWand(new Wand(wandCore, wood, 1,player));
        display.displayInfo("... Found it! It's an amazing wand, made of " + wandCore.toString() + " and " + wood.toString());
        display.displayInfo("I'm sure you'll make great use of it.");
    }

    public void teachBasicSpells() {
        display.displayInfo("Now let's make sure you know how to cast some basic spells.");
        display.displayInfo("We can start with Engorgio, a pretty simple spell that seems useless but can be amazing if you use it cleverly");
        (new Engorgio(game, player)).teach(player);
        display.displayInfo("Now you should learn a spell that is useful in combat");
        (new Confundus(game, player)).teach(player);
        display.displayInfo("Another spell that you may need in combat is Expelliarmus");
        (new Expelliarmus(game, player)).teach(player);
        player.learnSpell(new Engorgio(game, player));
        display.displayInfo("Nice, this should be enough to get you started.");
        display.displayInfo("Remember, you may not be able to cast them successfully or perfectly the first few times you use them, but don't worry, the more you use a spell and get familiar with your wand, the easier it will get.");
    }
}
