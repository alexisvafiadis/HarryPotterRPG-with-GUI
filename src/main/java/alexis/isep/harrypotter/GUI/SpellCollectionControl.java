package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Core.Characters.Wizard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import alexis.isep.harrypotter.Core.Levels.Essentials.Battle;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class SpellCollectionControl {
    private Wizard player;
    @FXML
    private Button spell1Button;
    @FXML
    private Button spell2Button;
    @FXML
    private Button spell3Button;
    @FXML
    private Button spell4Button;
    @FXML
    private ImageView nextPage;
    @FXML
    private ImageView previousPage;
    @FXML
    private Label currentPageLabel;
    private int currentPageIndex;

    private Battle battle;


    public SpellCollectionControl(Wizard player) {
        this.player = player;
        battle = null;
    }

    @FXML
    void initialize() {
        currentPageIndex = 0;
        showPage(0);
        nextPage.setOnMouseClicked((event) -> changePage(true));
        previousPage.setOnMouseClicked((event) -> changePage(false));
    }

    @FXML
    public void spellChooseEvent(ActionEvent event) {
        if (!(event.getSource() instanceof Button)) {return ;}
        if (battle != null) {
            battle.playerCastSpell(((Button)event.getSource()).getText());
        }
    }

    public void changePage(boolean next) {
        if (next) {
            if (currentPageIndex + 1 == getLastPageNumber()) {return;}
            currentPageIndex += 1;
        }
        else {
            if (currentPageIndex == 0) { return;}
            currentPageIndex -= 1;
        }
        showPage(currentPageIndex);
    }

    public void showPage(int pageNumber) {
        String[] playerSpells = player.getKnownSpells().keySet().toArray(new String[player.getKnownSpells().keySet().size()]);
        if (pageNumber > getLastPageNumber())  {
            System.out.println("This page doesn't exist");
            return;
        }
        int startIndex = pageNumber * 4;
        currentPageLabel.setText(pageNumber + 1 + " / " + getLastPageNumber());
        for (int i = startIndex ; i < startIndex + 4 ; i++) {
            Button button = null;
            if (i == startIndex) {
                button = spell1Button;
            } else if (i == startIndex + 1) {
                button = spell2Button;
            } else if (i == startIndex + 2) {
                button = spell3Button;
            } else if (i == startIndex + 3) {
                button = spell4Button;
            }
            if (i < playerSpells.length) {
                button.setText(playerSpells[i]);
            } else {
                button.setText("Empty");
            }
        }
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public int getLastPageNumber() {
        return (player.getKnownSpells().keySet().size() + 3) / 4;
    }
}
