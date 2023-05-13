package alexis.isep.harrypotter.Core.Game.Levels;

import alexis.isep.harrypotter.Core.Characters.Characteristics.House;
import alexis.isep.harrypotter.Core.Characters.Enemies.DeathEater;
import alexis.isep.harrypotter.Core.Characters.Enemies.Student;
import alexis.isep.harrypotter.Core.Game.BattleListLevel;
import alexis.isep.harrypotter.GUI.BinaryChoiceBoxController;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.Spells.Sectumsempra;

public class Level6 extends BattleListLevel {
    public Level6(Game game) {
        super(game, "The Half-blood Prince","Astronomy Tower", 6, true,70);
    }

    @Override
    public void start() {
        player.spawn();
        super.start();
    }

    public void startBattles() {
        if (player.isAgainstDeathEaters())  {
            enemies.add(new DeathEater(game, "Alecto Carrow", 110, 1, 1.6, 4.2, "I'll make sure you regret ever crossing the Dark Lord."));
            enemies.add(new DeathEater(game, "Lucius Malfoy", 125, 0.9, 1.85, 3.3, "My loyalty lies with the Dark Lord."));
            enemies.add(new DeathEater(game, "Corban Yaxley", 190, 0.7, 1, 4.7, "I serve the Dark Lord and nothing else. You will not break through my defense."));
            enemies.add(new DeathEater(game, "Antonin Dolohov", 130, 0.85, 2.15, 3.8, "I'll take pleasure in taking you down!"));

        }
        else {
            enemies.add(new Student(game, "Cho Chang", 90, 1, 1, 4.4, "I won't let you hurt my friends or my school."));
            enemies.add(new Student(game, "Neville Longbottom", 140,1.05,1.05,2.9, "I'm not the same boy you used to know. I've learned a few things since then."));
            enemies.add(new Student(game, "Ginny Weasley", 100, 0.85, 1.2, 3.4, "I may be small, but I can pack a punch."));
            enemies.add(new Student(game, "Cedric Diggory", 155, 0.8, 1.45, 4, "Let's make this a fair fight, shall we?"));
        }
        display.setOnFinish((e) -> fightNextOpponentIfAny());
    }

    @Override
    public void conclude() {
        if (player.isAgainstDeathEaters()) {
            display.congratulate("Good job, by winning those intense battles, you have helped Hogwarts defeat the Death Eaters and ensure the safety of Hogwarts.");
            display.displayInfo("You have proved yourself of worthy defender of Hogwarts.");
            display.displayInfo("However, with Voldemort still alive somewhere, the hardest task still has to be done. You should prepare yourself.");
        }
        else {
            display.displayInfo("As you stand among the fallen bodies of your former classmates, you feel the rush of power and victory, at the expense of your school, friends and principles.");
            display.congratulate("You have proven your worth to the Death Eaters and solidified your place among them by helping them win an important battle.");
            display.displayInfo("However, with Harry Potter, Hermione Granger and Ron Weasley still alive somewhere in Hogwarts, an extremely difficult fight is upcoming.");
        }
    }

    @Override
    public void introduce() {
        showLevelScene();
        display.displayInfo("In this level, Hogwarts is under attack by the Death Eaters, a group of dark wizards and witches who are loyal to the evil Lord Voldemort.");
        display.displayInfo("As a student of Hogwarts, your duty is to defend your school and your fellow students from the impending attack.");
        if (player.getHouse().equals(House.SLYTHERIN)) {
            display.displayInfo("As you are a Slytherin, you have the option to join the ranks of the Death Eaters and fight against your fellow students and former allies.");
            display.displayInfo("But remember, every decision you make in this level will have consequences. Choose your side wisely, as your actions will impact the rest of the game");
            display.displayInfo("So, do you wish to stay on the side of your fellow students? Take the right decision...         ");
            display.setOnFinish((e) -> {
                game.showElement("BinaryChoiceBox",param-> new BinaryChoiceBoxController(this),3.2);
            });
        }
        else {
            display.displayInfo("Therefore, you will fight against the Death Eaters.");
            chooseSideAndFinishIntroduction(true);
        }
    }

    public void chooseSideAndFinishIntroduction(boolean againstDeathEaters) {
        if (player.getHouse().equals(House.SLYTHERIN)) {
            game.closeSubWindowById("BinaryChoiceBox");
        }
        player.setAgainstDeathEaters(againstDeathEaters);
        String enemies;
        if (againstDeathEaters) {  enemies = "death eaters"; }
        else { enemies = "students"; }
        display.displayInfo("As you navigate through the level, you will encounter " + enemies + " with different abilities, strengths and weaknesses");
        display.displayInfo("Each encounter will be more difficult than the last. You will need to be strategic and use your spells wisely to ensure your survival.");
        display.displayInfo("This will be a challenging battle. The Sectumsempra spell will be essential to attack " + enemies + " head-on");
        (new Sectumsempra(game, player)).teach(player);
        player.setAgainstDeathEaters(againstDeathEaters);
        wishGoodLuck();
        display.setOnFinish((e) -> startBattles());
    }


}
