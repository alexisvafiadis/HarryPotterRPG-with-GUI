# Harry Potter RPG Game (JavaFX)
<img src="https://www.pause-canap.com/media/wysiwyg/Saga-harry-potter.jpg" alt="Harry Potter Theme Image" width="500px">

## Introduction
**Harry Potter at Home** is an interactive offline role-playing game (RPG) inspired by the magical world of Harry Potter made in Java. The game is designed to challenge the player through seven levels of increasing difficulty, each based on a significant event from the Harry Potter saga.

The primary goal of the project is to build an immersive gameplay experience where the player assumes the role of a young wizard attending Hogwarts School of Witchcraft and Wizardry. As the player progresses, they learn new spells, battle iconic enemies, and use potions to heal or strengthen their abilities. 

## Gameplay

### Demo Video

[Watch the full demo of the game](https://youtu.be/6hojVzXIa3c)

### Screenshots
Below are the screenshots showcasing various parts of the Harry Potter RPG game.

### 1. Wizard Creation

<img src="Gameplay%20Images/Home.png" alt="Wizard Creation" width="500px"/>

---

### 2. Combat

<img src="Gameplay%20Images/Combat.png" alt="Combat Level 1" width="500px"/>

---

### 3. Potion Choice

<img src="Gameplay%20Images/Potion%20Choice.png" alt="Potion Choice" width="150px"/>

---

### 4. Spell Choice

<img src="Gameplay%20Images/Spell%20Choice.png" alt="Spell Choice" width="150px"/>

---

### 5. Level End

<img src="Gameplay%20Images/Level%20End.png" alt="Level End" width="400px"/>

---

### 6. Level 2

<img src="Gameplay%20Images/Level2.png" alt="Level 2" width="500px"/>

---

### 8. Level 4

<img src="Gameplay%20Images/Level4.png" alt="Level 4" width="500px"/>

---

### 9. Level 5

<img src="Gameplay%20Images/Level5.png" alt="Level 5" width="500px"/>

---

### 10. Level 6 (Betray the School)

<img src="Gameplay%20Images/Level6.png" alt="Level 6 (Betray the School)" width="500px"/>

---

### 11. Level 6 (Stick with the School)

<img src="Gameplay Images/Level6 choice 2.png" alt="Level 6 Choice 2 (Stick with the School)" width="500px"/>

---

### 12. Level 7 (Betray the School)

<img src="Gameplay%20Images/Level7.png" alt="Level 7" width="500px"/>

---

### 13. Level 7 (Stick with the School)

<img src="Gameplay Images/Level7 choice 2.png" alt="Level 7 Choice 2" width="500px"/>

---

## Repository Information
- **Master branch**: Contains the version of the game without sound effects.
- **Dev branch**: Includes music and sound effects, although some sound effects may occasionally bug.
- **Old game version**: [Click here](https://github.com/alexisvafiadis/HarryPotterRPG-with-Console) to access the outdated version of the game that uses the Console as the User Interface instead of a JavaFX application.

## Features & Functionality

### 1. Character Creation
- **Wizard Creation**: At the start of the game, the player create their wizard, choosing a wand and being sorted into one of the four Hogwarts houses by the **Sorting Hat**:
  - **Gryffindor**: Wizards are more resistant to damage.
  - **Hufflepuff**: Potions are more effective.
  - **Ravenclaw**: Wizards are more precise when casting spells.
  - **Slytherin**: Wizards deal more damage with spells.

### 2. Spell System
- **Variety of Spells**: the player can learn and cast a broad range of spells, including:
  - *Expelliarmus* to disarm opponents.
  - *Expecto Patronum* to fend off Dementors.
  - *Wingardium Leviosa* to lift and throw objects at enemies.
  - *Sectumsempra* for slicing enemies.
  - *Accio* to summon objects.
  - *Protego* to summon a shield that wards off spells.
    
- **Learning and Mastery**: the player do not know all the spells from the start. They unlock new spells over time, either before or after each level. Each spell has its own difficulty level, and the player’s mastery increases the more they use the spell. The chance of failing a spell is high at first but decreases with practice.

### 3. Combat System
- **Turn-Based Combat**: The game uses a turn-based combat system where the player alternate between casting spells, using potions, looking around, and avoiding attacks.
- **Damage and Sound Effects**: Battles are brought to life with dynamic sound effects, animations for spells, and damage feedback.
- **Dodging Attacks**: At the start of the game, when the player's spell arsenal is limited, they can use the "Hide" option to increase the chance of dodging an attack, giving them time to develop their spells.
- **Music and Animations**: The game features battle music, spell animations, and sound effects for a more immersive experience.

### 4. Potion Inventory
- **Potions**: the player can collect potions during their adventure. Potions can heal, boost power, or improve performance in combat. Hufflepuff wizards have a particular advantage as their potions are more effective.

### 5. The Seven Levels
The game is divided into seven levels, each based on key events from the Harry Potter series:

- **Level 1: Troll Battle**: the player face a troll in the dungeons using *Wingardium Leviosa* to drop items on its head. Use *Engorgio* to enlarge the items for more damage.
- **Level 2: Chamber of Secrets**: Battle the Basilisk. Gryffindor wizards use the Sword of Gryffindor, while others must summon the Basilisk's fang using *Accio* to defeat the creature.
- **Level 3: Dementor Attack**: the player face Dementors at the lake in the Forbidden Forest, casting *Expecto Patronum* to drive them away.
- **Level 4: Escape from Voldemort**: In the Little Hangleton Cemetery, the player must escape Voldemort and Peter Pettigrew. Summon the Portkey using *Accio* and flee to safety.
- **Level 5: Distract Dolores Umbridge**: During an exam at Hogwarts, the player must distract Umbridge using various spells and eventually launch fireworks to cause chaos.
- **Level 6: Death Eaters Attack Hogwarts**: Defend Hogwarts from Death Eaters using combat spells like *Sectumsempra*. Slytherin wizards can choose to betray Hogwarts and join the Death Eaters, fighting against the other students.
  - **Choice to make**: Depending on the player's actions, they can either:
    - Join the Death Eaters and fight their fellow students.
    - Stay loyal to Hogwarts and battle the Death Eaters.
- **Level 7: Final Battle**: the player face Voldemort and Bellatrix Lestrange in a climactic duel at Hogwarts. Use *Expelliarmus* to disarm Voldemort and beware of *Avada Kedavra*. If the player betrayed their friends in Level 6, they will also face off against Harry Potter, Hermione Granger, and Ron Weasley in this final showdown.

### 7. Progression System
- **Health and Damage**: After winning battles, the player can choose to increase their health points or their damage points, enhancing their wizard's ability to survive tougher enemies.



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

---


## Used Technologies

### 1. **Java**
- The core of the application is developed using Java, for its object-oriented capabilities.

### 2. **JavaFX**
- JavaFX was used for building the graphical user interface (GUI) of the game.
  - **FXML files** define the layout of the various scenes such as character creation, battles, and levels.
  - **FXML Controllers** manage user interactions and dynamic behavior in the interface. For example:

### 3. **Scene Builder**
- **Scene Builder** was used to visually design JavaFX layouts through drag-and-drop creation of the user interface (UI).

### 4. **Lombok**
- **Lombok** was used to reduce boilerplate code in the project, especially in the creation of data models and entities.

### 5. **JUnit**
- **JUnit** was used for testing the core functionality of the game.
  - Unit tests ensure that game mechanics, items, spells, and battles function as expected.

### 6. **Maven**
- **Maven** was used for project management and dependency handling.

### 7. **Libraries**
- External libraries for JavaFX are stored in the `lib/` folder, ensuring that the game has the necessary resources to run on different platforms:
  - **`javafx-base-17-ea+2.jar`**: Core JavaFX base module.
  - **`javafx-graphics-17-ea+2.jar`**: JavaFX graphics module for rendering scenes.
  - **`javafx-media-17-ea+2.jar`**: JavaFX media module for handling audio files like background music and sound effects.
