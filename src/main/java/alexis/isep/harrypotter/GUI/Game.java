package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Console.Display;
import alexis.isep.harrypotter.Console.InputParser;
import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Game.SortingHat;
import alexis.isep.harrypotter.Core.Game.WizardMaker;
import alexis.isep.harrypotter.Core.Levels.*;
import alexis.isep.harrypotter.Core.Magic.Spells.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.Scanner;

public class Game extends javafx.application.Application{
    private Display display;
    private InputParser inputParser;
    private Wizard player;
    private Level currentLevel;
    private final boolean DEBUG_MODE = false;

    private String gameTitle;

    private final boolean GRAPHIC_INTERFACE_MODE = true;

    @Override
    public void start(Stage stage) throws IOException {
        display = new Display(this);
        inputParser = new InputParser(this, new Scanner(System.in));
        player = new Wizard(this);
        player.setDefaultAttributes();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/alexis/isep/harrypotter/GUI/CreateCharacter.fxml"));

        fxmlLoader.setControllerFactory(param -> new CreateCharacterController(this));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/alexis/isep/harrypotter/images/scene/GameIcon.png")));
        stage.setTitle("Harry Potter At Home");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void start() {
        display = new Display(this);
        inputParser = new InputParser(this, new Scanner(System.in));
        player = new Wizard(this);
        introduce(player);
        if (isInDebugMode()) {
            teachAllSpells();
        }
        setLevel(new Level1(this));
        setLevel(new Level2(this));
        setLevel(new Level3(this));
        setLevel(new Level4(this));
        setLevel(new Level5(this));
        setLevel(new Level6(this));
        setLevel(new Level7(this));

        finish();
    }

    public void introduce(Wizard wizard) {
        WizardMaker wizardmaker = new WizardMaker(this);
        SortingHat sortingHat = new SortingHat();
        sortingHat.askHouse(this, wizard);
    }

    public Wizard getPlayer() {
        return player;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setLevel(Level level) {
        currentLevel = null;
        currentLevel = level;
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
}
