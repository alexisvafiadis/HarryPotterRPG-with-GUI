package alexis.isep.harrypotter.Core.Levels;

import alexis.isep.harrypotter.Core.Characters.Enemies.*;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Levels.Essentials.Battle;

public class Level7 extends Level{

    public Level7(Game game) {
        super(game, "The Deathly Hallows","Great Hall", 7, false);
    }

    @Override
    public void start() {
        player.spawn();
        super.start();
        if (player.isAgainstDeathEaters()) {
            BellatrixLestrange bellatrix = new BellatrixLestrange(game);
            bellatrix.spawn();
/*            new Battle(game,this,player,bellatrix);
            Voldemort voldemort = new Voldemort(game);
            voldemort.spawn();
            new Battle(game,this,player,voldemort);
        }
        else {
            HarryPotter harry = new HarryPotter(game);
            harry.spawn();
            new Battle(game, this, player, harry);
            RonWeasley ron = new RonWeasley(game);
            ron.spawn();
            new Battle(game, this, player, ron);
            HermioneGranger hermione = new HermioneGranger(game);
            hermione.spawn();
            new Battle(game, this, player, hermione);*/
        }
        finish();
    }

    @Override
    public void conclude() {
        if (player.isAgainstDeathEaters()) {
            display.congratulate("The battle was fierce, but you emerged victorious against the evil Bellatrix Lestrange and the feared Lord Voldemort.");
            display.displayInfo(" The wizarding world can now breathe a sigh of relief, thanks to your bravery and determination.");
            display.displayInfo("You are hailed as a hero, and your name will be remembered for generations to come.");
        }
        else {
            Voldemort voldemort = new Voldemort(game);
            display.congratulate("The battle was fierce, but you emerged victorious against Harry Potter, Hermione Granger and Ron Weasley.");
            display.displayQuote(voldemort,"Well done, you have proven your worth, " + player.getName() + ".");
            display.displayQuote(voldemort, "We can now rule over the wizarding world together.");
            display.displayInfo("The battle is over, and the wizarding world has been changed forever.");
            display.displayInfo("You have achieved what you set out to do, and your name will be remembered for centuries to come as one of the greatest Death Eaters in history.");
        }
    }

    @Override
    public void introduce() {
        showLevelScene();
        if (player.isAgainstDeathEaters()) {
            display.displayInfo("After successfully fighting the Death Eaters alongside the other students, you have finally reached the final battle.");
            display.displayInfo("Bellatrix Lestrange and Voldemort are waiting for you, ready to put an end to your rebellion once and for all.");
            display.displayInfo("Most of the other students being either dead or heavily weakened, the fate of Hogwarts and the entire wizarding world rests on your shoulders.");
        }
        else {
            display.displayInfo("You have made a fateful choice to join the ranks of the Death Eaters and fight against your former friends and allies.");
            display.displayInfo("Harry Potter, Hermione Granger, and Ron Weasley have now become your enemies, and they will not go easy on you.");
            display.displayInfo("They are powerful wizards and will stop at nothing to defend Hogwarts and the wizarding world against the dark forces.");
            display.displayInfo("You have to beat them to prove that you can become co-leader of the Death Eaters alongside Voldemort.");
        }
        display.displayInfo("You must use all the skills you've learned and spells you've mastered to defeat these powerful adversaries.");
        wishGoodLuck();
    }


}
