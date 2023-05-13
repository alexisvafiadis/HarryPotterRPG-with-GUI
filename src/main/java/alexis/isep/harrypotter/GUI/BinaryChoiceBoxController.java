package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Core.Game.Level;
import alexis.isep.harrypotter.Core.Game.Levels.Level6;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class BinaryChoiceBoxController {
    private Level level;
    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;

    public BinaryChoiceBoxController(Level level) {
        this.level = level;
    }

    @FXML
    void handleActionButtonEvent(ActionEvent event) {
        if (!(event.getSource() instanceof Button)) {
            return;
        }
        boolean choice = (event.getSource() == yesButton);
        if (level instanceof Level6) {
            ((Level6) level).chooseSideAndFinishIntroduction(choice);
        }
    }
}
