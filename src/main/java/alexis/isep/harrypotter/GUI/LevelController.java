package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Levels.Level;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
public class LevelController {
    private Level level;
    private Game game;
    private Wizard player;
    @FXML
    private ImageView background;

    @FXML
    private ImageView playerImage;

    @FXML
    private Label levelText;
    @FXML
    private Label placeText;

    @FXML
    private Label playerInfoText;

    @FXML
    private Label healthText;

    @FXML
    public void initialize() {
        background.setImage(level.getImage());
        playerImage.setImage(player.getImage());
        levelText.setText("Level " + level.getNumber());
        placeText.setText(level.getPlace());
        healthText.setText("Health : " + player.getHP());
        playerInfoText.setText(player.getName() + " (" + player.getHouse() + ")");
        healthText.setText("Health : " + player.getHP() + "/" + player.getMaxHP());
    }

    public LevelController(Level level, Game game, Wizard player) {
        this.level = level;
        this.game = game;
        this.player = player;
    }
}
