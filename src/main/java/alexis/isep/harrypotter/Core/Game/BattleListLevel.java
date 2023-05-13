package alexis.isep.harrypotter.Core.Game;

import alexis.isep.harrypotter.Core.Characters.EnemyWizard;
import alexis.isep.harrypotter.GUI.Game;

import java.util.ArrayList;
import java.util.List;

public abstract class BattleListLevel extends Level {
    protected List<EnemyWizard> enemies;
    protected int currentBattleIndex;
    private int midLevelHealing;
    public BattleListLevel(Game game, String name, String place, int number, boolean outdoors, int midLevelHealing) {
        super(game, name, place, number, outdoors);
    }

    @Override
    public void initialize() {
        super.initialize();
        enemies = new ArrayList<>();
        currentBattleIndex = 0;
    }

    public void fightNextOpponentIfAny() {
        if (midLevelHealing > 0 && currentBattleIndex == (int) Math.ceil(enemies.size() / 2.0)) {
            display.displayInfo("As your next opponents are shocked by your abilities, you are able to rest for a little and regenerate health.");
            player.heal(midLevelHealing);
        }
        if (currentBattleIndex >= enemies.size()) {
            finish();
            return;
        }
        EnemyWizard enemy = enemies.get(currentBattleIndex);
        enemy.spawn();
        currentBattleIndex++;
        startBattle(enemy, (e) -> {
            fightNextOpponentIfAny();
        });
    }


}
