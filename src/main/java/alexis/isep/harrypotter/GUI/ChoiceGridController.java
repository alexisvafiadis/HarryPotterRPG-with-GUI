package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Core.Game.Levels.Level4;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ChoiceGridController {
    private Level4 level;
    private int step;

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
    void initialize() {
        disableAllActionButtons();
        button1.setText("Move");
        button2.setText("Cast Lumos");
        button3.setText("Cast Accio");
        button4.setText("");
    }

    public void askForAction() {
        step = 1;
        button1.setText("Move");
        button2.setText("Cast Lumos");
        button3.setText("Cast Accio");
        button4.setText("");
        enableAllActionButtons(false);
    }

    public void askForDirections() {
        step = 2;
        button1.setText("Forwards");
        button2.setText("Backwards");
        button3.setText("Left");
        button4.setText("Right");
        enableAllActionButtons(true);
    }

    @FXML
    void handleActionButtonEvent(ActionEvent event) {
        if (!(event.getSource() instanceof Button)) {return ;}
        disableAllActionButtons();
        if (step == 1) {
            level.chooseAction(((Button) event.getSource()).getText());
        }
        else if (step == 2) {
            level.chooseDirection(((Button) event.getSource()).getText());
        }
    }
    public void disableAllActionButtons() {
        button1.setDisable(true);
        button2.setDisable(true);
        button3.setDisable(true);
        button4.setDisable(true);
    }

    public void enableAllActionButtons(boolean fourthButtonEnabled) {
        button1.setDisable(false);
        button2.setDisable(false);
        button3.setDisable(false);
        if (fourthButtonEnabled) button4.setDisable(false);
    }
}
