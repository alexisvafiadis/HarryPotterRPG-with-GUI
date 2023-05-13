package alexis.isep.harrypotter.Core.Game.Levels;

import alexis.isep.harrypotter.Core.Characters.Enemies.Basilisk;
import alexis.isep.harrypotter.Core.Game.Level;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Items.Weapon;
import alexis.isep.harrypotter.Core.Magic.Spells.Accio;
import alexis.isep.harrypotter.Core.Magic.Spells.PetrificusTotalus;

public class Level2 extends Level {

    public Level2(Game game) {
        super(game, "The Chamber of Secrets","the Chamber of Secrets", 2, false);
    }

    @Override
    public void start() {
        player.spawn();
        super.start();
        display.setOnFinish((e) -> {
            Basilisk basilisk = new Basilisk(game);
            basilisk.spawn();
            startBattle(basilisk);
        });
    }

    @Override
    public void conclude() {
        display.congratulate("Good job, that battle was impressive.");
        display.displayInfo("Considering the threats you've been facing, it would be a good idea to teach you another offensive spell : Petrificus Totalus.");
        (new PetrificusTotalus(game, player)).teach(player);
        if (!player.knowsSpell("Accio")) {
            display.displayInfo("Oh, I almost forgot. There's a spell you will need soon. It is called Accio");
            (new Accio(game, player)).teach(player);
        }
    }

    @Override
    public void introduce() {
        showLevelScene();
        display.displayInfo("For this level, you have to defeat the Basilisk. It is a very venomous snake that kills anyone that makes eye contact with it.");
        if (player.getHouse().toString().equals("Gryffindor")) {
            display.displayInfo("In order to do that, you have to use the Gryffindor sword. Here it is.");
            display.announceReward("You have been given the Gryffindor sword");
            player.setWeapon(Weapon.SWORD_OF_GRYFFINDOR);
        }
        else {
            display.displayInfo("In order to do that, you will probably need the Accio spell to summon a Basilisk's fang and use it against itself.");
            (new Accio(game, player)).teach(player);
        }
        wishGoodLuck();
    }

}
