package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Core.Levels.Essentials.LevelMap;
import alexis.isep.harrypotter.Core.Levels.Level;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.util.Map;

public class LevelMapController {
    private LevelMap map;
    private Level level;
    @FXML
    private AnchorPane LevelMap;
    @FXML
    private GridPane mapGridPane;

    public LevelMapController(LevelMap map, Level level) {
        this.map = map;
        this.level = level;
    }
    public void createGrid(int numRows, int numCols, char[][] grid) {
        LevelMap.setVisible(true);
        // Clear existing content from the GridPane
        mapGridPane.getChildren().clear();
        mapGridPane.getColumnConstraints().clear();
        mapGridPane.getRowConstraints().clear();
        int imageWidth = (int) mapGridPane.getPrefWidth() / numCols;
        int imageHeight = (int) mapGridPane.getPrefHeight() / numRows;

        // Create column constraints with equal width
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0 / numCols);
            mapGridPane.getColumnConstraints().add(columnConstraints);
        }

        // Create row constraints with equal height
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0 / numRows);
            mapGridPane.getRowConstraints().add(rowConstraints);
        }

        // Create ImageView cells in each row and column with visible borders
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                ImageView imageView = new ImageView();
                imageView.setFitWidth(imageWidth);
                imageView.setFitHeight(imageHeight);
                imageView.setPreserveRatio(false);
                imageView.setImage(getImageTile(grid[col][row]));

                Rectangle border = new Rectangle();
                border.setFill(Color.TRANSPARENT);
                border.setStroke(Color.WHITE);
                border.setStrokeWidth(0.3);
                border.widthProperty().bind(imageView.fitWidthProperty());
                border.heightProperty().bind(imageView.fitHeightProperty());

                StackPane cellPane = new StackPane(border, imageView);
                mapGridPane.add(cellPane, numRows - row - 1, numCols - col - 1);
            }
        }
    }

    public Image getImageTile(char character) {
        if (character == '.') {
            return null;
        }
        return new Image(getClass().getResource(Game.GAME_ROOT + "images/icons/" + character + ".png").toString(), true);
    }

    public void hide() {
        LevelMap.setVisible(false);
    }
}
