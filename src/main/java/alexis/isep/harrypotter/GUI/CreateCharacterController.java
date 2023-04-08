package alexis.isep.harrypotter.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CreateCharacterController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}