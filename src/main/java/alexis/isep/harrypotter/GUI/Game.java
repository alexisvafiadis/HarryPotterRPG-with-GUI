package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Console.InputParser;
import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Game.SortingHat;
import alexis.isep.harrypotter.Core.Game.WizardMaker;
import alexis.isep.harrypotter.Core.Levels.*;
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
    private Display display;
    private InputParser inputParser;
    private Wizard player;
    private Level currentLevel;
    private Stage stage;
    private final boolean DEBUG_MODE = false;
    private List<Class<?>> levels = new ArrayList<>();


    @Override
    public void start(Stage stage) throws IOException {
        display = new Display(this);
        inputParser = new InputParser(this, new Scanner(System.in));
        player = new Wizard(this);
        player.setDefaultAttributes();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/alexis/isep/harrypotter/GUI/CreateCharacter.fxml"));

        fxmlLoader.setControllerFactory(param -> new CreateCharacterController(this));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/alexis/isep/harrypotter/images/scene/Icon.png")));
        stage.setTitle(GAME_TITLE);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        this.stage = stage;
    }

    public static void main(String[] args) {
        launch();
    }

    public void start() {
        display = new Display(this);
        inputParser = new InputParser(this, new Scanner(System.in));
        player = new Wizard(this);
        introduce(player);
        mainGame();
    }

    public void mainGame() {
        if (isInDebugMode()) {
            teachAllSpells();
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
        setLevel(currentLevel.getNumber());
    }

    public void introduce(Wizard wizard) {
        WizardMaker wizardmaker = new WizardMaker(this);
        SortingHat sortingHat = new SortingHat();
        sortingHat.askHouse(this, wizard);
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

    public Display getDisplay() {
        return display;
    }

    public InputParser getInputParser() {
        return inputParser;
    }

    public boolean isInDebugMode() {
        return DEBUG_MODE;
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
        display.congratulate("You have finished the game, congratulations!");
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
        fxmlLoader.setControllerFactory(callback);
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException("Error loading FXML file: " + name, e);
        }
        stage.setScene(scene);
        stage.show();
    }

    public void addDialogPane(boolean shortened) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/alexis/isep/harrypotter/GUI/DialogPane.fxml"));
        fxmlLoader.setControllerFactory(param -> display);
        try {
            AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
            if (shortened) {
                anchorPane.setMinWidth(anchorPane.getMinWidth() * 0.65);
                anchorPane.setMinHeight(anchorPane.getMinHeight() * 0.7);
                anchorPane.setMaxWidth(anchorPane.getMinWidth() * 0.65);
                anchorPane.setMaxHeight(anchorPane.getMinHeight() * 0.7);
                anchorPane.setPrefWidth(anchorPane.getMinWidth() * 0.65);
                anchorPane.setPrefHeight(anchorPane.getMinHeight() * 0.7);
            }
            ((AnchorPane) stage.getScene().getRoot()).getChildren().add(anchorPane);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addDialogPane() { addDialogPane(false);}

    public void showElement(String document, Callback<Class<?>, Object> callback, double centerCoeff) {
        FXMLLoader fxmlLoader = loadFXML(document);
        fxmlLoader.setControllerFactory(callback);
        try {
            AnchorPane root = ((AnchorPane) stage.getScene().getRoot());
            AnchorPane anchorPane = fxmlLoader.load();
            anchorPane.setLayoutY((root.getHeight() - anchorPane.getHeight()) / centerCoeff);
            anchorPane.setLayoutX((root.getWidth() - anchorPane.getWidth()) / centerCoeff);
            root.getChildren().add(anchorPane);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void closeSubWindows() {
        String[] arrayWindowsToClose = new String[] {"PotionInventory", "SpellCollection","ItemInventory"};
        List<String> listWindowsToClose = Arrays.asList(arrayWindowsToClose);
        ((AnchorPane) stage.getScene().getRoot()).getChildren().removeIf((node) -> node != null && listWindowsToClose.contains(node.getId()));
        //((AnchorPane) stage.getScene().getRoot()).getChildren().remove(((AnchorPane) stage.getScene().getRoot()).getChildren().size() - 1);
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

    public Wizard getPlayer() {
        return player;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
    public Stage getStage() {
        return stage;
    }
}
