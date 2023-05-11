package alexis.isep.harrypotter.Core.Levels;

import alexis.isep.harrypotter.Core.Characters.Enemies.Dementor;
import alexis.isep.harrypotter.Core.Characters.Enemies.Voldemort;
import alexis.isep.harrypotter.GUI.ChoiceGridController;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Items.Item;
import alexis.isep.harrypotter.Core.Items.ItemType;
import alexis.isep.harrypotter.Core.Levels.Essentials.LevelMap;
import alexis.isep.harrypotter.Core.Magic.Spells.*;

import java.util.HashMap;

public class Level4 extends Level{
    private final int MIN_POTTER_VOLDEMORT_DISTANCE = 4;
    private boolean hasPortkey;
    private Item portkey;
    private LevelMap map;
    private Voldemort voldemort;
    private ChoiceGridController choiceGridController;

    public Level4(Game game) {
        super(game, "The Goblet of Fire","Little Hangleton graveyard", 4, true);
        display = game.getDisplay();
        inputParser = game.getInputParser();
    }
    @Override
    public void start() {
        map = new LevelMap(10,10);
        player.spawn(5,0,map);
        portkey = new Item(ItemType.PORTKEY, 7,9, map, 'P');
        hasPortkey = false;
        super.start();
        voldemort = new Voldemort(game);
        voldemort.spawn(3,5,map);
        choiceGridController = new ChoiceGridController(this);
        game.showElement("ChoiceGrid", param -> choiceGridController,2);
        display.setOnFinish((e) -> askForAction());
    }

    public void finishRound() {
        if (!seenByVoldemort() && !hasPortkey) {
            askForAction();
        }
        else if (seenByVoldemort()) {
            startBattle(voldemort);
        }
        else {
            inputParser.waitForConfirmation("Visualize your room in Hogwarts in your head, then touch the Portkey.");
            display.displayInfo("The power of the Portkey teleports you back to Hogwarts...");
            display.setOnFinish((e) -> finish());
        }
    }

    public void askForDirections() {
        HashMap<Integer, String> directionInputs = new HashMap<>();
        directionInputs.put(1, "move forwards");
        directionInputs.put(2, "move backwards");
        directionInputs.put(3, "move to the left");
        directionInputs.put(4, "move to the right");
        String directionChoice = inputParser.getNumberToStringInput("Where do you want to go?", directionInputs, "to");
        boolean canMove = false;
        switch (directionChoice) {
            case "move forwards":
                canMove = player.moveForwards();
                break;
            case "move backwards":
                canMove = player.moveBackwards();
                break;
            case "move to the left":
                canMove = player.moveLeft();
                break;
            case "move to the right":
                canMove = player.moveRight();
                break;
        }
        if (canMove) {
            display.displayInfo("You successfully " + directionChoice);
        } else {
            display.announceFail("You cannot move there, sorry. Choose another direction");
        }
    }

    public boolean seenByVoldemort() {
        return (map.calculateDistance(player, voldemort) < MIN_POTTER_VOLDEMORT_DISTANCE);
    }

    public void askForAction() {
        display.displayInfo("What do you want to do?");
        display.setOnFinish((e) -> choiceGridController.enableAllActionButtons());
    }

    public void chooseAction(String actionChoice) {
        switch(actionChoice) {
            case "Move":
                askForDirections();
                break;
            case "Try to use Accio on the Portkey":
                if ((((Accio) player.getKnownSpells().get("Accio")).cast(portkey))) {
                    hasPortkey = true;
                }
                break;
            case "Use Lumos":
                ((Lumos) player.getKnownSpells().get("Lumos")).cast(map);
                break;
        }
    }

    public void goBackAndAskForAction() {
        game.closeSubWindows();
        askForAction();
    }

    @Override
    public void conclude() {
        display.congratulate("Good job, you have gotten away!");
        teachFunnySpells();
    }

    @Override
    public void introduce() {
        showLevelScene();
        display.displayInfo("Voldemort and Peter Pettigrew are nearby. You should probably get away.");
        display.displayInfo("You have to get close to the Portkey to attract it to you using Accio then use the Portkey");
        display.displayInfo("If you get spotted by Voldemort, you will have to defend yourself.");
        display.displayInfo("I doubt you're skilled enough to survive yet, but we never know. Let me teach you Protego");
        (new Protego(game, player)).teach(player);
        wishGoodLuck();
    }

    public void teachFunnySpells() {
        display.displayInfo("To help you recover, let me teach you some funny spells. Let's start with Rictumsempra.");
        (new Rictumsempra(game, player)).teach(player);
        display.displayInfo("There's also Slugulus Erecto, a spell that causes the target to vomit slugs for a short period");
        (new SlugulusErecto(game, player)).teach(player);
        display.displayInfo("You can learn Tarantallegra as well.");
        (new Tarantallegra(game, player)).teach(player);
    }


}
