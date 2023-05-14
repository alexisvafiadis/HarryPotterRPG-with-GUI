package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Console.InputParser;
import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Game.Level;
import alexis.isep.harrypotter.Core.Game.Levels.*;
import alexis.isep.harrypotter.Core.Game.WizardMaker;
import alexis.isep.harrypotter.Core.Magic.Spells.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

public class Game extends javafx.application.Application{
    private final String GAME_TITLE = "Harry Potter At Home";
    //SET DEBUG MODE TO TRUE TO GO FASTER AND TEST THE GAME AND FALSE TO PLAY NORMALLY
    private final boolean DEBUG_MODE = false;
    private Display display;
    private InputParser inputParser;
    private Wizard player;
    private Level currentLevel;
    private Stage stage;
    private final List<Class<?>> levels = new ArrayList<>();
    public static final String GAME_ROOT = "/alexis/isep/harrypotter/";
    public static final String SOUND_ROOT = "src/main/resources/alexis/isep/harrypotter/sounds/";

    @Override
    public void start(Stage stage) throws IOException {
        start();
        WizardMaker wizardmaker = new WizardMaker(this);
        CreateCharacterController createCharacterController = new CreateCharacterController(this,wizardmaker);
        wizardmaker.setCreateCharacterController(createCharacterController);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/alexis/isep/harrypotter/GUI/CreateCharacter.fxml"));
        fxmlLoader.setControllerFactory(param -> createCharacterController);
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/alexis/isep/harrypotter/images/scene/Icon.png")));
        stage.setTitle(GAME_TITLE);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        this.stage = stage;
        addDialogPane(0.5);
        wizardmaker.askForCharacterCreation();
    }

    public void start() {
        display = new Display(this);
        inputParser = new InputParser(this, new Scanner(System.in));
        player = new Wizard(this);
        player.setDefaultAttributes();
    }

    public static void main(String[] args) {
        launch();
    }

    public void mainGame() {
        if (isInDebugMode()) {
            teachAllSpells();
            player.upgradeAccuracy(100);
            player.upgradeHP(100);
            player.upgradeDamage(100);
        }
        levels.add(Level.class);
        levels.add(Level1.class);
        levels.add(Level2.class);
        levels.add(Level3.class);
        levels.add(Level4.class);
        levels.add(Level5.class);
        levels.add(Level6.class);
        levels.add(Level7.class);
        setLevel(1);
    }
    public void nextLevel() {
        if (currentLevel.getNumber() == levels.size() - 1) {
            finish();
            return;
        }
        setLevel(currentLevel.getNumber() + 1);
    }

    public void setLevel(int number) {
        try {
            currentLevel = (Level) levels.get(number).getConstructor(Game.class).newInstance(this);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        currentLevel.start();
    }

    public String getMessageStartHave(Character character) {
        String castMessageStart;
        if (character instanceof Wizard) {
            castMessageStart = "You have";
        }
        else {
            castMessageStart = character.getName() + " has";
        }
        return castMessageStart;
    }

    public String getMessageStartBe(Character character) {
        String castMessageStart;
        if (character instanceof Wizard) {
            castMessageStart = "You are";
        }
        else {
            castMessageStart = character.getName() + " is";
        }
        return castMessageStart;
    }

    public void finish() {
        addDialogPane(1);
        display.congratulate("You have finished the game, congratulations!!!");
        display.setOnFinish((e) -> System.out.println("Game finished"));
    }

    public void teachAllSpells() {
        player.learnSpell(new Accio(this, player));
        player.learnSpell(new WingardiumLeviosa(this, player));
        player.learnSpell(new Lumos(this, player));
        player.learnSpell(new Reducto(this, player));
        player.learnSpell(new Confundus(this, player));
        player.learnSpell(new Expelliarmus(this, player));
        player.learnSpell(new Protego(this, player));
        player.learnSpell(new Rictumsempra(this, player));
        player.learnSpell(new Sectumsempra(this, player));
        player.learnSpell(new Tarantallegra(this, player));
        player.learnSpell(new Stupefy(this, player));
        player.learnSpell(new SlugulusErecto(this, player));
    }
    public void setScene(String name, Callback<Class<?>, Object> callback) {
        URL fxmlURL = getClass().getResource(name);
        if (fxmlURL == null) {
            throw new RuntimeException("FXML file not found: " + name);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
        if (callback != null) {
            fxmlLoader.setControllerFactory(callback);
        }
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException("Error loading FXML file: " + name, e);
        }
        stage.setScene(scene);
        stage.show();
    }

    public void showElement(String document, Callback<Class<?>, Object> callback, double layoutX, double layoutY, AnchorPane root) {
        FXMLLoader fxmlLoader = loadFXML(document);
        fxmlLoader.setControllerFactory(callback);
        try {
            AnchorPane anchorPane = fxmlLoader.load();
            if (root == null) {
                root = ((AnchorPane) stage.getScene().getRoot());
                anchorPane.setLayoutY((root.getHeight() - anchorPane.getHeight()) / layoutY);
                anchorPane.setLayoutX((root.getWidth() - anchorPane.getWidth()) / layoutX);
            }
            else {
                anchorPane.setLayoutX(layoutX);
                anchorPane.setLayoutY(layoutY);
            }
            root.getChildren().add(anchorPane);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void showElement(String document, Callback<Class<?>, Object> callback, double centerCoeff) {
        showElement(document, callback, centerCoeff, centerCoeff,null);
    }

    public void closeSubWindows() {
        String[] arrayWindowsToClose = new String[] {"PotionInventory", "SpellCollection","ItemInventory","LevelMap"};
        List<String> listWindowsToClose = Arrays.asList(arrayWindowsToClose);
        ((AnchorPane) stage.getScene().getRoot()).getChildren().removeIf((node) -> node != null && listWindowsToClose.contains(node.getId()));
        //((AnchorPane) stage.getScene().getRoot()).getChildren().remove(((AnchorPane) stage.getScene().getRoot()).getChildren().size() - 1);
    }

    public void closeSubWindowById(String id) {
        ((AnchorPane) stage.getScene().getRoot()).getChildren().removeIf((node) -> node != null && node.getId().equals(id));
    }

    public FXMLLoader loadFXML(String name) {
        URL fxmlURL = getClass().getResource("/alexis/isep/harrypotter/GUI/" + name + ".fxml");
        if (fxmlURL == null) {
            throw new RuntimeException("FXML file not found: " + name);
        }
        else {
            return new FXMLLoader(fxmlURL);
        }
    }

    public void addDialogPane(double resizeCoeff) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/alexis/isep/harrypotter/GUI/DialogPane.fxml"));
        fxmlLoader.setControllerFactory(param -> display);
        try {
            AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
            boolean shortened = (resizeCoeff != 1);
            if (shortened) {
                anchorPane.setMinWidth(anchorPane.getMinWidth() * resizeCoeff);
                anchorPane.setMinHeight(anchorPane.getMinHeight() * resizeCoeff);
                anchorPane.setMaxWidth(anchorPane.getMinWidth() * resizeCoeff);
                anchorPane.setMaxHeight(anchorPane.getMinHeight() * resizeCoeff);
                anchorPane.setPrefWidth(anchorPane.getMinWidth() * resizeCoeff);
                anchorPane.setPrefHeight(anchorPane.getMinHeight() * resizeCoeff);
            }
            display.adaptTextSize(resizeCoeff);
            ((AnchorPane) stage.getScene().getRoot()).getChildren().add(anchorPane);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Wizard getPlayer() {
        return player;
    }

    public Display getDisplay() {
        return display;
    }

    public InputParser getInputParser() {
        return inputParser;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
    public Stage getStage() {
        return stage;
    }

    public boolean isInDebugMode() {
        return DEBUG_MODE;
    }

    public String getGameRoot() {
        return GAME_ROOT;
    }
}
