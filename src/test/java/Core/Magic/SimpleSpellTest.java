package Core.Magic;

import Core.Characters.AbstractEnemy;
import Core.Characters.Characteristics.House;
import Core.Characters.Enemies.Troll;
import Core.Characters.Wizard;
import Core.Game.Game;
import Core.Items.Item;
import Core.Levels.Essentials.Battle;
import Core.Levels.Level;
import Core.Levels.Level1;
import Core.Magic.Spells.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleSpellTest {
    Wizard wizard;
    AbstractEnemy enemy;
    Game game;
    Confundus confondus; //offensive effect spell
    Sectumsempra sectumsempra; //damage spell
    Protego protego; //defensive effect spell
    Battle battle;

    @BeforeEach
    void setUp() {
        game = new Game();
        wizard = game.getPlayer();
        wizard.setDefaultAttributes();
        wizard.upgradeAccuracy(10);
        wizard.spawn();
        enemy = new Troll(game);
        enemy.spawn();
        confondus = new Confundus(game, wizard);
        confondus.setNbOfUses(1000);
        sectumsempra = new Sectumsempra(game, wizard);
        sectumsempra.setNbOfUses(1000);
        protego = new Protego(game, wizard);
        protego.setNbOfUses(1000);
    }

    @Test
    void doesSpellWork() {
        confondus.cast(enemy);
        assertTrue(enemy.hasEffect(EffectType.CONFUSION), "Offensive effect spells do not work");
        double previousHP = enemy.getHP();
        sectumsempra.cast(enemy);
        assertTrue((enemy.getHP() < previousHP) || !enemy.isAlive());
        protego.cast(enemy);
        assertTrue(enemy.hasEffect(EffectType.SHIELD), "Defensive effect spells do not work");
    }

    @Test
    void calculateDamage() {
        assertNotNull(confondus.calculateDamage(10));
        assertNotNull(sectumsempra.calculateDamage(10));
        assertNotNull(protego.calculateDamage(10));
    }

    @Test
    void getMasteryScore() {
        assertTrue(confondus.getMasteryScore() != 0);
        assertTrue(sectumsempra.getMasteryScore() != 0);
        assertTrue(protego.getMasteryScore() != 0);
    }
}
