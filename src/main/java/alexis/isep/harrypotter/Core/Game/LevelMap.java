package alexis.isep.harrypotter.Core.Game;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.Core.Items.Item;
import alexis.isep.harrypotter.Core.Game.Levels.Level4;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.GUI.LevelMapController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class LevelMap {
    private Level4 level4;
    private int width;
    private int height;
    private char[][] grid;
    private LevelMapController mapController;

    public LevelMap(Level4 level4, int width, int height) {
        this.level4 = level4;
        this.width = width;
        this.height = height;
        grid = new char[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = '.';
            }
        }
    }

    public void setTile(int x, int y, char tile) {
        if (isPositionPossible(x,y)) {
            grid[y][x] = tile;
        } else {
            throw new IllegalArgumentException("Coordinates out of bounds: (" + x + ", " + y + ")");
        }
    }

    public void clearTile(int x, int y) {
        grid[y][x] = '.';
    }

    public char getTile(int x, int y) {
        if (isPositionPossible(x,y)) {
            return grid[y][x];
        } else {
            throw new IllegalArgumentException("Coordinates out of bounds: (" + x + ", " + y + ")");
        }
    }

    public void generate() {
        Game game = level4.getGame();
        mapController = new LevelMapController(this, level4);
        game.showElement("LevelMap", param -> mapController,480,125,level4.getLevelController().getInfoAnchorPane());
        mapController.hide();
    }

    public void display(int LIGHT_DURATION) {
        mapController.createGrid(width,height,grid);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(LIGHT_DURATION), (e) -> {
            mapController.hide();
            level4.finishRound();
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public Image getImageTile(String characterName) {
        return new Image(getClass().getResource("/alexis/isep/harrypotter/images/characters/" + characterName + " Icon.png").toString(), true);
    }

    //Check if position is inside the map or already taken by another character/item
    public boolean isPositionAvailable(int x, int y) {
        return ((isPositionPossible(x,y)) && (getTile(x,y) == '.'));
    }

    public boolean isPositionPossible(int x, int y) {
        return ((x >= 0 && x < width && y >= 0 && y < height));
    }

    public double calculateDistance(Character character, Item item) {
        return Math.sqrt(Math.pow((item.getPositionX() - character.getPositionX()), 2) + Math.pow((item.getPositionY() - character.getPositionY()), 2));
    }

    public double calculateDistance(Character character1, Character character2) {
        return Math.sqrt(Math.pow((character1.getPositionX() - character2.getPositionX()), 2) + Math.pow((character1.getPositionY() - character2.getPositionY()), 2));
    }


}
