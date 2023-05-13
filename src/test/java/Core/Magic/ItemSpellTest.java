package Core.Magic;

import alexis.isep.harrypotter.Core.Characters.AbstractEnemy;
import alexis.isep.harrypotter.Core.Characters.Enemies.Troll;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Items.Item;
import alexis.isep.harrypotter.Core.Game.Battle;
import alexis.isep.harrypotter.Core.Game.Level;
import alexis.isep.harrypotter.Core.Game.Levels.Level1;
import alexis.isep.harrypotter.Core.Magic.Spells.WingardiumLeviosa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemSpellTest {
    Wizard wizard;
    AbstractEnemy enemy;
    Game game;
    WingardiumLeviosa wingardiumLeviosa;
    Battle battle;

    @BeforeEach
    void setUp() {
        game = new Game();
        wizard = game.getPlayer();
        wizard.setDefaultAttributes();
        wizard.spawn();
        enemy = new Troll(game);
        enemy.spawn();
        Level level = new Level1(game);
        level.initialize();
        battle = new Battle(game, level, wizard, enemy,(e) -> System.out.println("test"));
        wingardiumLeviosa = new WingardiumLeviosa(game, wizard);
        wingardiumLeviosa.setNbOfUses(1000);
    }

    @Test
    void canSpellWork() {
        double previousHP = enemy.getHP();
        boolean castSuccessful = false;
        //Checking a lot of times because of possible failures due to accuracy etc
        for (int i = 0 ; i < 100 ; i++) {
            if (wingardiumLeviosa.cast(new Item(battle.generateItemType()), enemy)) {
                castSuccessful = true;
                break;
            }
        }
        assertTrue(castSuccessful, "The wingardium leviosa spell cannot be cast.");
        assertTrue(enemy.getHP() < previousHP, "The wingardium leviosa spell doesn't deal any damage.");
    }
}
