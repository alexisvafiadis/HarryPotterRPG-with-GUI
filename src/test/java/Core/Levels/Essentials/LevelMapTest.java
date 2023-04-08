package Core.Levels.Essentials;

import Core.Characters.AbstractEnemy;
import Core.Characters.Characteristics.House;
import Core.Characters.Enemies.Troll;
import Core.Characters.Wizard;
import Core.Game.Game;
import Core.Levels.Level;
import Core.Levels.Level1;
import Core.Magic.Spells.WingardiumLeviosa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelMapTest {
    Game game;
    LevelMap map;

    @BeforeEach
    void setUp() {
        game = new Game();
        map = new LevelMap(10,10);
        Level level = new Level1(game);
        level.initialize();
    }

    @Test
    void getTile() {
        assertNotNull(map.getTile(1,1));
        assertThrows(IllegalArgumentException.class, () -> map.getTile(15,8));
        assertThrows(IllegalArgumentException.class, () -> map.getTile(5,23));
    }

    @Test
    void setTile() {
        assertThrows(IllegalArgumentException.class, () -> map.setTile(20,8,'T'));
        assertThrows(IllegalArgumentException.class, () -> map.setTile(7,25,'T'));
        map.setTile(2,2,'o');
        assertEquals(map.getTile(2,2), 'o');
    }

    @Test
    void clearTile() {
        map.setTile(2,2,'o');
        map.clearTile(2,2);
        assertEquals(map.getTile(2,2),'.');
    }

    @Test
    void isPositionAvailable() {
        assertEquals(map.isPositionAvailable(3,3), true);
        map.setTile(2,2,'d');
        assertEquals(map.isPositionAvailable(2,2), false);
    }

    @Test
    void isPositionPossible() {
        assertEquals(map.isPositionAvailable(12,12), false);
        assertEquals(map.isPositionAvailable(3,3), true);
    }
}