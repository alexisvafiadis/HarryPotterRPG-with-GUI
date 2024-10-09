# Harry Potter RPG Game (JavaFX)
<img src="https://www.pause-canap.com/media/wysiwyg/Saga-harry-potter.jpg" alt="Harry Potter Theme Image" width="500px">


## Introduction
**Harry Potter at Home** is an interactive role-playing game (RPG) inspired by the magical world of Harry Potter. The game is designed to challenge players through seven levels of increasing difficulty, each based on a significant event from the Harry Potter saga. The project demonstrates skills in game development, object-oriented programming, and graphical interface design using JavaFX.

The primary goal of the project is to build an immersive gameplay experience where the player assumes the role of a young wizard attending Hogwarts School of Witchcraft and Wizardry. As the player progresses, they learn new spells, battle iconic enemies, and use potions to heal or strengthen their abilities. 

## Repository Information
- **Master branch**: Contains the version of the game without sound effects.
- **Dev branch**: Includes music and sound effects, although some sound effects may occasionally bug.
- **Old game version**: [Click here](https://github.com/alexisvafiadis/HarryPotterRPG-with-Console) to access the outdated version of the game that uses the Console as the User Interface instead of a JavaFX application.

## Features & Functionality
- **Character Creation**: At the start of the game, players create their wizard, choose a wand, and are sorted into one of the four Hogwarts houses (Gryffindor, Hufflepuff, Ravenclaw, Slytherin), each house offering unique gameplay advantages.
- **Spell System**: Players can learn and cast a variety of spells such as *Expelliarmus*, *Expecto Patronum*, and *Wingardium Leviosa*.
- **Potion Inventory**: Players can collect and use potions during battles to regain health or improve their chances in combat.
- **Seven Levels of Adventure**: Each level is based on a key event from the Harry Potter series:
  - **Level 1**: Battle a Troll using *Wingardium Leviosa*.
  - **Level 2**: Defeat the Basilisk in the Chamber of Secrets.
  - **Level 3**: Face Dementors and use *Expecto Patronum*.
  - **Level 4**: Escape Voldemort and Peter Pettigrew using *Accio* to summon the Portkey.
  - **Level 5**: Distract Dolores Umbridge with fireworks.
  - **Level 6**: Battle Death Eaters invading Hogwarts.
  - **Level 7**: Final duel with Voldemort and Bellatrix Lestrange.

## Project Structure

The project is well-structured into distinct modules to ensure separation of concerns and maintainability. Below is an overview of the key folders and files that make up the core of the project.

### Root Directory
- **`mvnw`, `mvnw.cmd`**: Maven wrapper scripts to build the project on any system without needing a pre-installed Maven.
- **`pom.xml`**: The Maven project descriptor, listing dependencies and build configurations.
- **`structure.txt`**: Contains a basic structure of the repository (reference for documentation).

### `.mvn/`
- **`wrapper/`**: Contains the Maven wrapper JAR file and properties to automate Maven builds without requiring Maven to be installed.

### `libraries/`
- **`openjfx_javafx_media.xml`**: Configuration file for managing JavaFX media dependencies.

### `lib/`
- Contains JavaFX libraries required for running the graphical user interface (GUI), including base, graphics, and media modules.

### `src/`
This is where the main source code resides, organized into Java classes and FXML resources.

#### `src/main/java/`
The main code directory is structured as follows:
- **`module-info.java`**: Describes the module, its dependencies, and exports.
- **`alexis/isep/harrypotter/`**: Root package for all game-related classes and components.
  - **Console/**: Classes for command-line interface utilities.
    - **`ConsoleColors.java`**: Utility class for adding colored text to the console.
    - **`InputParser.java`**: Handles user input from the console.
  - **Core/**: Contains core game mechanics and logic.
    - **Characters/**
      - **`Wizard.java`**: Represents the player character.
      - **`EnemyWizard.java`**, **`Boss.java`**, **`Troll.java`**, etc.: Represent different enemy characters.
    - **Characteristics/**
      - **`House.java`**: Defines the Hogwarts houses and their unique traits.
      - **`Pet.java`**: Handles the player's pet selection (owl, cat, rat, or toad).
    - **Enemies/**: Classes for notable enemies such as **`Basilisk.java`**, **`BellatrixLestrange.java`**, **`Voldemort.java`**.
    - **Game/**
      - **`Battle.java`**: Implements the battle system logic between characters.
      - **`Level.java`**: Base class for handling each game level's flow and events.
      - **`WizardMaker.java`**: Manages the player's wizard creation process.
    - **Battles/**: Specific battle implementations, like **`DoloresUmbridgeBattle.java`**.
    - **Levels/**: Individual game levels (**`Level1.java`**, **`Level2.java`**, through **`Level7.java`**).
    - **Items/**: Manages game items like **`Wand.java`**, **`Weapon.java`**, and **`Potion.java`**.
    - **Magic/**: Spell and potion management.
      - **`Spell.java`**: Base class for all spells.
      - **Spells/**: Specific spell implementations like **`Expelliarmus.java`**, **`AvadaKedavra.java`**, **`WingardiumLeviosa.java`**.
  - **GUI/**: Manages the game's graphical interface using JavaFX.
    - **`Game.java`**: Main JavaFX application class responsible for launching the game.
    - **`CreateCharacterController.java`**, **`BattleController.java`**, etc.: Controllers for different game screens and actions.

#### `src/main/resources/`
This directory contains FXML files, images, and sound assets required by the JavaFX interface.
- **GUI/**: FXML files defining the layout for various game scenes.
  - **`Battle.fxml`**, **`CreateCharacter.fxml`**, **`Level.fxml`**: Define the visual elements for battles, character creation, and levels.
- **Images/**: Contains images for characters, scenes, items, and pets.
  - **`characters/`**: Contains images for game characters like **Harry Potter**, **Hermione Granger**, and **Voldemort**.
  - **`inventories/`**: Visual representations of items and potions.
  - **`pets/`**: Images for pets (owl, cat, rat, toad).
  - **`scene/`**: Backgrounds for different game levels and battle scenes.
- **Sounds/**: Stores sound effects and background music.
  - **`effects/`**: Sound effects for spells, attacks, and actions.
  - **`music/`**: Background music files for various levels and battles.

#### `src/test/`
This directory includes unit tests to ensure the correctness of game logic.
- **Core/**
  - **Characters/**: Unit tests for character behavior, such as **`WizardTest.java`** and **`EnemyTest.java`**.
  - **Items/**: Tests for item interactions, such as **`ItemTest.java`**.
  - **Magic/**: Tests for magic system functionality, like **`SimpleSpellTest.java`**.

### `target/`
This is where the compiled project files, including the `.class` files, are stored after Maven builds the project. It also includes resources needed to run the application.


https://youtu.be/6hojVzXIa3c
