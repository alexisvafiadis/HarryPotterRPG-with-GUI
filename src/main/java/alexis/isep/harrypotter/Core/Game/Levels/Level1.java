package alexis.isep.harrypotter.Core.Game.Levels;

import alexis.isep.harrypotter.Core.Characters.Enemies.Troll;
import alexis.isep.harrypotter.Core.Game.Level;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.Spells.Stupefy;
import alexis.isep.harrypotter.Core.Magic.Spells.WingardiumLeviosa;

public class Level1 extends Level {

    public Level1(Game game) {
        super(game, "The Philosopher’s Stone", "Toilets of the Dungeon", 1, false);
    }

    @Override
    public void start() {
        player.spawn();
        super.start();
        display.setOnFinish(event -> {
            System.out.println("The onFinish event has been fired");
            Troll troll = new Troll(game);
            troll.spawn();
            startBattle(troll);});
    }

    @Override
    public void conclude() {
        display.congratulate("You made great use of your spells.");
        display.announceReward("You have earned the right to learn an offensive spell that may come in handy in the future.");
        (new Stupefy(game, player)).teach(player);
    }

    @Override
    public void introduce() {
        showLevelScene();
        display.displayInfo("For this level, you unfortunately run into the Troll, and you have to defeat it to survive.");
        display.displayInfo("In order to do that, you have to use the spell called Wingardium Leviosa to lift and throw items on the Troll's head");
        (new WingardiumLeviosa(game, player)).teach(player);
        wishGoodLuck();
    }

}
