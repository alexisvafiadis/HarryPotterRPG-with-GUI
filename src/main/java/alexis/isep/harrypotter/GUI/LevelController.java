package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Levels.Level;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class LevelController {
    private Level level;
    private Game game;
    private Wizard player;
    @FXML
    private AnchorPane infoAnchorPane;
    @FXML
    private ImageView background;

    @FXML
    private ImageView playerImage;
    @FXML
    private ImageView petImage;

    @FXML
    private Label levelText;
    @FXML
    private Label placeText;

    @FXML
    private Label playerInfoText;

    @FXML
    private Label healthText;
    @FXML
    private Label potionInventoryText;

    public LevelController(Level level, Game game, Wizard player) {
        this.level = level;
        this.game = game;
        this.player = player;
    }

    @FXML
    public void initialize() {
        petImage.setImage(player.getPetImage());
        background.setImage(level.getImage());
        playerImage.setImage(player.getImage());
        levelText.setText("Level " + level.getNumber());
        placeText.setText(level.getPlace());
        healthText.setText("Health : " + player.getHP());
        playerInfoText.setText(player.getName() + " (" + player.getHouse() + ")");
        healthText.setText("Health : " + (int) Math.ceil(player.getHP()) + "/" + (int) Math.ceil(player.getMaxHP()));
        addBorder(infoAnchorPane);
        level.showMainElements(infoAnchorPane);
    }

    public void addBorder(AnchorPane anchorPane) {
        BorderWidths borderWidths = new BorderWidths(4);
        Border border = new Border(new BorderStroke(
                Color.web("#362419"),
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                borderWidths));
        anchorPane.setBorder(border);
    }

    public void hideInventoryText() {
        potionInventoryText.setVisible(false);
    }

    public AnchorPane getInfoAnchorPane() {
        return infoAnchorPane;
    }
}
