package Core.Characters;

import Core.Characters.Characteristics.House;
import Core.Characters.Enemies.*;
import Core.Characters.EnemyWizard;
import Core.Characters.Wizard;
import Core.Game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnemyWizardTest {
    List<EnemyWizard> enemyWizards;
    Wizard player;
    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        player = game.getPlayer();
        player.setDefaultAttributes();
        player.spawn();
        enemyWizards = new ArrayList<>();
        enemyWizards.add(new DeathEater(game, "Test", 100, 1, 1, 5, ""));
        enemyWizards.add(new Student(game, "Test", 100, 1, 1, 5, ""));
        enemyWizards.add(new Voldemort(game));
        enemyWizards.add(new BellatrixLestrange(game));
        enemyWizards.add(new HarryPotter(game));
        enemyWizards.add(new HermioneGranger(game));
        enemyWizards.add(new RonWeasley(game));
        for (EnemyWizard enemyWizard : enemyWizards) {
            enemyWizard.spawn();
        }
    }

    @Test
    void amplifySpellDamage() {
        for (EnemyWizard enemyWizard : enemyWizards) {
            double damage = player.amplifySpellDamage(200.0);
            assertTrue(damage >= 200, "The amplify spell damage function does not work for " + enemyWizard.getName());
        }
    }

    @Test
    void checkSpellWeight() {
        for (EnemyWizard enemyWizard : enemyWizards) {
            assertTrue(enemyWizard.getTotalSpellWeight() > 0, "The enemy wizard " + enemyWizard.getName() + "'s spell weight is equal to 0");
        }
    }

    @Test
    void checkGenerateSpell() {
        for (EnemyWizard enemyWizard : enemyWizards) {
            assertNotNull(enemyWizard.generateSpell(), "The enemy wizard " + enemyWizard.getName() + "'s generate spell function does not work.");
        }
    }

    @Test
    void canDamageWithSpells() {
        for (EnemyWizard enemyWizard : enemyWizards) {
            boolean canDealDamage = false;
            double previousHP = player.getHP();
            //Checking a lot of times because of possible failures due to accuracy etc
            for (int i = 0 ; i < 1000 ; i++) {
                enemyWizard.act();
                player.finishRound();
                if (player.getHP() < previousHP || !player.isAlive()) {
                    canDealDamage = true;
                    break;
                }
            }
            assertTrue(canDealDamage, "The enemy wizard " + enemyWizard.getName() + " doesn't deal damage.");
        }
    }

}