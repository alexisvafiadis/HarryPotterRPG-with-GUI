package alexis.isep.harrypotter.Core.Game.Battles;

import alexis.isep.harrypotter.Core.Characters.AbstractEnemy;
import alexis.isep.harrypotter.Core.Characters.Enemies.DoloresUmbridge;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Game.Battle;
import alexis.isep.harrypotter.Core.Game.Level;
import alexis.isep.harrypotter.GUI.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DoloresUmbridgeBattle extends Battle {
    final int NB_OF_ROUNDS_BEFORE_WIN = 15;

    public DoloresUmbridgeBattle(Game game, Level level, Wizard player, AbstractEnemy enemy, EventHandler<ActionEvent> e) {
        super(game, level, player, enemy,e);
        System.out.println(((DoloresUmbridge) enemy).isDistracted());
    }

    @Override
    public boolean getBattleLossCondition() {
        System.out.println(((DoloresUmbridge) enemy).isDistracted());
        return (super.getBattleLossCondition() || !enemy.isAlive() || !(((DoloresUmbridge) enemy).isDistracted()));
    }

    @Override
    public boolean getBattleWinCondition() {
        return (roundNumber > NB_OF_ROUNDS_BEFORE_WIN);
    }

    @Override
    public void displayBattleStartMessage() {
        display.displayInfo("Choose the right spells!");
    }

    @Override
    public void displayBattleWinMessage() {

    }

    @Override
    public void actionIfLoss() {
        finish();
        display.setOnFinish(onFinishedEventHandler);
    }
}
