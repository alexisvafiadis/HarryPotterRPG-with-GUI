package alexis.isep.harrypotter.Core.Game;

import alexis.isep.harrypotter.GUI.*;
import alexis.isep.harrypotter.Console.InputParser;
import alexis.isep.harrypotter.Core.Characters.AbstractEnemy;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Items.Item;
import alexis.isep.harrypotter.Core.Items.ItemType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Level {
    //Global attributes
    protected Game game;
    protected Display display;
    protected InputParser inputParser;
    protected Wizard player;
    protected LevelController levelController;

    //Level specific attributes
    protected int number;
    protected String name;
    protected String place;
    protected boolean outdoors;

    protected List<Item> items;
    protected List<ItemType> possibleItemTypes;
    protected boolean levelCompleted;

    //Constants
    protected final double HP_UPGRADE = 35;
    protected final double SPELL_DAMAGE_UPGRADE = 0.28;
    protected final double DAMAGE_RESISTANCE_UPGRADE = 0.27;
    protected final double ACCURACY_UPGRADE = 0.32;

    public Level(Game game, String name, String place, int number, boolean outdoors) {
        this.game = game;
        display = game.getDisplay();
        inputParser = game.getInputParser();
        this.player = game.getPlayer();

        this.name = name;
        this.place = place;
        this.number = number;
        this.outdoors = outdoors;
    }

    public abstract void introduce();

    public void initialize() {
        levelCompleted = false;
        setPossibleItemTypes();
        items = new ArrayList<>();
    }

    public void start() {
        initialize();
        introduce();
    }

    public abstract void conclude();

    public void fail() {
        display.announceFail("You failed this level. Try again!");
        start();
    }

    /*
    public void fail(Class<? extends Level> levelClass) {
        display.announceFail("You failed this level. Try again!");
        try {
            Level currentLevel = levelClass.getDeclaredConstructor(Game.class).newInstance(game);
            game.setLevel(currentLevel);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
     */

    public void finish() {
        LevelSummaryController levelSummaryController = new LevelSummaryController(this);
        game.setScene("/alexis/isep/harrypotter/GUI/LevelSummary.fxml",param -> levelSummaryController);
        game.addDialogPane(1);
        if (!levelCompleted) {
            conclude();
            display.announceSuccess("Congratulations, you have completed the level " + number + "!");
            askForUpgrade();
            display.setOnFinish((e) -> {
                display.hide();
                levelSummaryController.enableContinueButton();
            });
            levelCompleted = true;
        }
    }

    public void wishGoodLuck() {display.displayInfo("Good luck!"); }

    public void askForUpgrade() {
        display.displayInfo("You can now upgrade one of your attributes. \nChoose wisely.");
    }

    public void upgradeAndNextLevel(String upgradeChoice) {
        upgrade(upgradeChoice);
        display.setOnFinish((e) -> {
            game.nextLevel();
        });
    }

    public void upgrade(String upgradeChoice) {
        switch (upgradeChoice) {
            case "Hitpoints":
                game.getPlayer().upgradeHP(HP_UPGRADE);
                break;
            case "Attack Damage":
                game.getPlayer().upgradeDamage(SPELL_DAMAGE_UPGRADE);
                break;
            case "Damage Resistance":
                game.getPlayer().upgradeResistance(DAMAGE_RESISTANCE_UPGRADE);
                break;
            case "Accuracy":
                game.getPlayer().upgradeAccuracy(ACCURACY_UPGRADE);
                break;
        }
    }

    public List<ItemType> getPossibleItemTypes() {
        return possibleItemTypes;
    }

    public void setPossibleItemTypes() {
        possibleItemTypes = new ArrayList<>(Arrays.asList(ItemType.values()));
        if (outdoors) { possibleItemTypes.removeIf(it -> (it.getWhere() != 1)); }
        else { possibleItemTypes.removeIf(it -> (it.getWhere() != 0)); }
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getNumber() {
        return number;
    }

    public boolean isOutdoors() {
        return outdoors;
    }

    public void startBattle(AbstractEnemy enemy, EventHandler<ActionEvent> onBattleFinishEventHandler, Battle battle) {
        if (battle == null) {
            battle = new Battle(game, this, player, enemy, onBattleFinishEventHandler);
        }
        BattleController battleController = new BattleController(battle);
        battle.setBattleController(battleController);
        game.setScene("/alexis/isep/harrypotter/GUI/Battle.fxml",param -> battleController);
        game.addDialogPane(0.63);
        battle.start();
    }

    //If the battle is already specific, no need for the other arguments
    public void startBattle(Battle battle) {
        startBattle(null, null, battle);
    }

    public void startBattle(AbstractEnemy abstractEnemy,  EventHandler<ActionEvent> onBattleFinishEventHandler) {
        startBattle(abstractEnemy, onBattleFinishEventHandler, null);
    }

    //If no event handler is specified, then the battle is the last battle of the level and the finish function will be called
    public void startBattle(AbstractEnemy enemy) {
        startBattle(enemy,(e) -> this.finish());
    }

    public Image getImage() {
        return (new Image(getClass().getResourceAsStream("/alexis/isep/harrypotter/images/scene/battles/Background" + getNumber() + ".png")));
    }

    public Wizard getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }

    public LevelController getLevelController() {
        return levelController;
    }

    public void showLevelScene() {
        levelController = new LevelController(this,game,player);
        game.setScene("/alexis/isep/harrypotter/GUI/Level.fxml",param -> levelController);
        game.addDialogPane(1);
    }

    public void showMainElements(AnchorPane anchorPane) {
        game.showElement("PotionInventory",param->new PotionInventoryController(player,false),420,165,anchorPane);
    }
}
