package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Levels.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class LevelSummaryController {
    @FXML
    private Button continueButton;

    @FXML
    private Text statusText;
    private Level level;
    private Button chosenUpgradeButton;

    public LevelSummaryController(Level level) {
        this.level = level;
        chosenUpgradeButton = null;
    }

    @FXML
    public void initialize() {
        statusText.setText("You beat the level " + level.getNumber());
        continueButton.setOnAction(this::handleContinueEvent);
        continueButton.setDisable(true);
    }

    public void enableContinueButton() {
        continueButton.setDisable(false);
    }

    @FXML
    public void handleContinueEvent(ActionEvent e) {
        if (chosenUpgradeButton != null) {
            level.upgradeAndNextLevel(chosenUpgradeButton.getText());
        }
    }

    @FXML
    public void chooseUpgradeEvent(ActionEvent e) {
        if (!(e.getSource() instanceof Button)) {return;}
        if (chosenUpgradeButton != null) {
            chosenUpgradeButton.setDisable(false);
        }
        chosenUpgradeButton = (Button) e.getSource();
        chosenUpgradeButton.setDisable(true);
    }
}
