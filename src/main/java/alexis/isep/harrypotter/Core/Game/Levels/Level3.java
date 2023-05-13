package alexis.isep.harrypotter.Core.Game.Levels;

import alexis.isep.harrypotter.Core.Characters.Enemies.Dementor;
import alexis.isep.harrypotter.Core.Game.Level;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.Spells.Expectopatronum;
import alexis.isep.harrypotter.Core.Magic.Spells.Lumos;

public class Level3 extends Level {
    private boolean expectoPatronumUsed;

    public Level3(Game game) {
        super(game, "The Prisoner of Azkaban","the Great Lake", 3, true);
        display = game.getDisplay();
        inputParser = game.getInputParser();
    }

    @Override
    public void start() {
        player.spawn();
        super.start();
        expectoPatronumUsed = false;
        display.setOnFinish((e) -> dementorBattleUntilExpectoPatronum());
    }

    public void dementorBattleUntilExpectoPatronum() {
        Dementor dementor = new Dementor(game);
        dementor.spawn();

        startBattle(dementor, (e) -> {
            if (expectoPatronumUsed) {
                finish();
            } else {
                dementorBattleUntilExpectoPatronum(); // Recursive call to start the next battle
            }
        });
    }

    @Override
    public void conclude() {
        display.congratulate("Good job, you scared the Dementors away thanks to your Patronus!");
        display.displayInfo("Now let me teach you a spell that you'll probably need soon : Lumos.");
        (new Lumos(game, player)).teach(player);
    }

    @Override
    public void introduce() {
        showLevelScene();
        display.displayInfo("For this level, you have to scare the Dementors away.");
        display.displayInfo("In order to do that, you have to use the spell Expecto Patronum to summon your Patronus.");
        (new Expectopatronum(game, player)).teach(player);
        display.displayInfo("It is a very difficult spell to cast, so it may take some time to use it successfully.");
        wishGoodLuck();
    }

    public void setExpectoPatronumUsed() {
        this.expectoPatronumUsed = true;
        display.displayInfo("You feel a rush of warmth and positive energy as your Patronus takes shape, driving away the dark creatures and filling the air with light");
    }


}
