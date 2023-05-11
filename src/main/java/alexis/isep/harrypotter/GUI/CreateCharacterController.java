package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Game.WizardMaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CreateCharacterController {
    private Game game;
    private Wizard player;
    private WizardMaker wizardMaker;

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

    public CreateCharacterController(Game game, WizardMaker wizardmaker) {
        this.game = game;
        this.player = game.getPlayer();
        this.wizardMaker = wizardmaker;
    }

    @FXML
    void initialize() {
        createCharacterButton.setDisable(true);
        update();
        nameField.setDisable(true);
    }

    public void enableInput() {
        nameField.setDisable(false);
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
        if (event.getSource().equals(cancelButton)) {  }
        else {
            game.getPlayer().setName(nameField.getText());
            wizardMaker.finishIntroduction();
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
}