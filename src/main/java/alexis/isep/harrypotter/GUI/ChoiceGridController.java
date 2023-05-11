package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Core.Levels.Level4;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ChoiceGridController {
    private Level4 level;

    @FXML
    private Button button1;

    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private GridPane gridPane;

    public ChoiceGridController(Level4 level) {
        this.level = level;
    }

    @FXML
    void handleActionButtonEvent(ActionEvent event) {
        if (!(event.getSource() instanceof Button)) {return ;}
        disableAllActionButtons();
        level.chooseAction(((Button)event.getSource()).getText());
    }
    public void disableAllActionButtons() {
        button1.setDisable(true);
        button2.setDisable(true);
        button3.setDisable(true);
        button4.setDisable(true);
    }

    public void enableAllActionButtons() {
        button1.setDisable(false);
        button2.setDisable(false);
        button3.setDisable(false);
        button4.setDisable(false);
    }
}
