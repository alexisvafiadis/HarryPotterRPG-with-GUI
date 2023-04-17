package alexis.isep.harrypotter.Core.Levels;

import alexis.isep.harrypotter.Core.Characters.Enemies.Troll;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.Spells.Stupefy;
import alexis.isep.harrypotter.Core.Magic.Spells.WingardiumLeviosa;

public class Level1 extends Level{

    public Level1(Game game) {
        super(game, "The Philosopher’s Stone", "Toilets of the Dungeon", 1, false);
    }

    @Override
    public void start() {
        player.spawn();
        super.start();
        Troll troll = new Troll(game);
        troll.spawn();
        startBattle(troll);
        finish();
    }

    @Override
    public void conclude() {
        display.congratulate("You made great use of your spells.");
        display.announceReward("You have earned the right to learn an offensive spell that may come in handy in the future.");
        (new Stupefy(game, player)).teach(player);
    }

    @Override
    public void introduce() {
        giveLevelInfo();
        display.displayInfo("In this level, you have to defeat the Troll.");
        display.displayInfo("In order to do that, you have to use the Wingardium Leviosa spell to lift and throw items on the Troll's head");
        (new WingardiumLeviosa(game, player)).teach(player);
        wishGoodLuck();
    }

}
