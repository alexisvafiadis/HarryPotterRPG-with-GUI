package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Core.Items.Item;
import alexis.isep.harrypotter.Core.Levels.Essentials.Battle;
import alexis.isep.harrypotter.Core.Levels.Level;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;
import java.util.List;

public class ItemInventoryController {
    @FXML
    private ChoiceBox itemChoiceBox;
    private Battle battle;
    private String spellChoice;
    private List<String> itemNameList;

    public ItemInventoryController(Battle battle, String spellChoice) {
        this.battle = battle;
        this.spellChoice = spellChoice;
    }

    @FXML
    public void initialize() {
        itemNameList = new ArrayList<>();
        for (Item item : battle.getLevel().getItems()) {
            itemNameList.add(item.getItemType().toString());
        }
        itemChoiceBox.setItems(FXCollections.observableList(itemNameList));
    }

    @FXML
    public void handleConfirm(ActionEvent e) {
        String itemChoice = itemChoiceBox.getValue().toString();
        System.out.println(itemChoice);
        int i = 0;
        for (Item item : battle.getLevel().getItems()) {
            if (item.getItemType().toString().equals(itemChoice)) {
                battle.playerCastItemSpell(spellChoice, i);
                return;
            }
            i++;
        }
    }
}
