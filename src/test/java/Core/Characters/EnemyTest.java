package Core.Characters;

import Core.Characters.Characteristics.House;
import Core.Characters.Enemies.Basilisk;
import Core.Characters.Enemies.Dementor;
import Core.Characters.Enemies.Troll;
import Core.Game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemyTest {
    Wizard wizard;
    List<AbstractEnemy> enemies;
    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        wizard = game.getPlayer();
        wizard.setDefaultAttributes();
        wizard.spawn();
        enemies = new ArrayList<>();
        enemies.add(new Troll(game));
        enemies.add(new Basilisk(game));
        enemies.add(new Dementor(game));
    }

    @Test
    void doesAttackWork() {
        for (AbstractEnemy enemy : enemies) {
            enemy.spawn();
            double previousHP = wizard.getHP();
            enemy.act();
            assertTrue(wizard.getHP() < previousHP, "The enemy " + enemy.getName() + " doesn't deal damage.");
        }
    }
}
