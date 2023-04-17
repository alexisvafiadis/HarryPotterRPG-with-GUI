package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Core.Characters.Wizard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CreateCharacterController {
    Game game;
    Wizard player;

    @FXML
    private Label welcomeText;
    @FXML
    private AnchorPane createCharacterPane;

    @FXML
    private ImageView mainLayout;
    @FXML
    private Button createCharacterButton;
    @FXML
    private Button cancelButton;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField nameField;

    @FXML
    private Label houseLabel;

    @FXML
    private Button nextHouse;

    @FXML
    private Button previousHouse;

    @FXML
    private Label petLabel;

    @FXML
    private Button previousPet;

    @FXML
    private Button nextPet;

    @FXML
    void initialize() {
        createCharacterButton.setDisable(true);
        update();
    }

    @FXML
    void nameInputEvent() {
        if (nameField.getText().equals("") || nameField.getText().contains(" "))
            createCharacterButton.setDisable(true);
        else
            createCharacterButton.setDisable(false);
    }

    private int house = 1;
    @FXML
    void houseSelectEvent(ActionEvent event) {
        if (event.getSource().equals(previousHouse)) house--;
        else house++;
        if (house > 3) house = 0;
        else if (house < 0) house = 3;
        game.getPlayer().setHouse(house);
        update();
    }

    private int pet = 1;
    @FXML
    void petSelectEvent(ActionEvent event) {
        if (event.getSource().equals(previousPet)) pet--;
        else pet++;
        if (pet > 3) pet = 0;
        else if (pet < 0) pet = 3;
        game.getPlayer().setPet(pet);
        update();
    }

    @FXML
    void createButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource().equals(cancelButton)) {int player = 0; }
        else {
            game.getPlayer().setName(nameField.getText());
            //create a new game save
        }
        /*
        URL url = this.getClass().getClassLoader().getResource("fxml/LoadGame.fxml");
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        createCharacterPane.getChildren().setAll(pane);
         */
    }

    private void update() {
        imageView.setImage(player.getImage());
        houseLabel.setText(player.getHouse().toString());
        petLabel.setText(player.getPet().toString());
    }

    public CreateCharacterController(Game game) {
        this.game = game;
        this.player = game.getPlayer();
    }
}