package alexis.isep.harrypotter.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DialogController {
    @FXML
    Label labelText;

    public void setText(String text) {
        labelText.setText(text);
    }
}
