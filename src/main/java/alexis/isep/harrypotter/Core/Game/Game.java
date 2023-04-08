package alexis.isep.harrypotter.Core.Game;

import alexis.isep.harrypotter.Console.Display;
import alexis.isep.harrypotter.Console.InputParser;
import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Levels.*;
import alexis.isep.harrypotter.Core.Magic.Spells.*;

import java.util.Scanner;

public class Game {
    private Display display;
    private InputParser inputParser;
    private Wizard player;
    private Level currentLevel;
    private final boolean DEBUG_MODE = false;

    public Game() {
        display = new Display(this);
        inputParser = new InputParser(this, new Scanner(System.in));
        player = new Wizard(this);
    }

    public void start() {
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
