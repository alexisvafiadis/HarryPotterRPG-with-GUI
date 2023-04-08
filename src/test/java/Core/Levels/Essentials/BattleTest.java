package Core.Levels.Essentials;
import alexis.isep.harrypotter.Core.Characters.AbstractEnemy;
import alexis.isep.harrypotter.Core.Characters.Enemies.Troll;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Game.Game;
import alexis.isep.harrypotter.Core.Levels.Essentials.Battle;
import alexis.isep.harrypotter.Core.Levels.Level1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {
    Wizard wizard;
    AbstractEnemy enemy;
    Game game;
    Battle battle;

    @BeforeEach
    void setUp() {
        game = new Game();
        wizard = game.getPlayer();
        wizard.setDefaultAttributes();
        wizard.spawn();
        enemy = new Troll(game);
        enemy.spawn();
        Level1 level = new Level1(game);
        level.initialize();
        battle = new Battle(game, level, wizard, enemy);
    }

    @Test
    void checkBattleContinueConditions() {
        game.getDisplay().displayInfo(String.valueOf(battle.getBattleContinueCondition()));
        assertTrue(battle.getBattleContinueCondition(), "The battle cannot start");
        wizard.damage(wizard.getMaxHP() + 100);
        assertFalse(battle.getBattleContinueCondition(), "The battle is not lost after the player is dead");
        assertTrue(battle.getBattleLossCondition(), "The battle is not lost after the player is dead");
        assertFalse(battle.getBattleWinCondition(), "The battle is not lost after the player is dead");
    }

    @Test
    void finishRound() {
        int previousRoundNumber = battle.getRoundNumber();
        battle.finishRound();
        assertTrue(battle.getRoundNumber() == previousRoundNumber + 1);
    }

    @Test
    void generateItemType() {
        assertNotNull(battle.generateItemType());
    }

    @Test
    void generatePotionType() {
        assertNotNull(battle.generatePotionType());
    }

    @Test
    void getSpellInputs() {
        assertNotNull(battle.getSpellInputs());
    }

    @Test
    void getItemInputs() {
        assertNotNull(battle.getItemInputs());
    }
}