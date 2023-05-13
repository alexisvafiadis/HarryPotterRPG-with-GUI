package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Magic.Potion;
import alexis.isep.harrypotter.Core.Magic.PotionType;
import javafx.fxml.FXML;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class PotionInventoryController {
    @FXML
    private GridPane potionGridPane;
    private Wizard player;
    private boolean duringBattle;

    public PotionInventoryController(Wizard player) {
        this.player = player;
        duringBattle = true;
    }

    public PotionInventoryController(Wizard player, boolean duringBattle) {
        this.player = player;
        this.duringBattle = duringBattle;
    }

    @FXML
    public void initialize() {
        Map<PotionType, Integer> potionCounts = new HashMap<>();
        for (Potion potion : player.getPotions()) {
            PotionType potionType = potion.getPotionType();
            int count = potionCounts.getOrDefault(potionType, 0);
            potionCounts.put(potionType, count + 1);
        }

        int i = 0;
        for (PotionType potionType : potionCounts.keySet()) {
            StackPane potionStackPane = ((StackPane) potionGridPane.getChildren().get(i));
            ImageView potionImageView = ((ImageView) potionStackPane.getChildren().get(1));
            Tooltip t = new Tooltip(potionType.toString() + "\n" + potionType.getDescription() + "\n Count : " + potionCounts.get(potionType));
            Text text = new Text(t.getText());
            text.setWrappingWidth(350);
            t.setWrapText(true);
            t.setPrefWidth(text.getLayoutBounds().getWidth());
            t.setFont(javafx.scene.text.Font.font(14));
            Tooltip.install(potionStackPane, t);
            potionImageView.setImage(potionType.getImage());
            if (duringBattle) {
                potionImageView.setId(potionType.name());
                potionStackPane.setOnMouseClicked(this::potionChooseEventHandler);
            }
            i++;
        }
        for (i = potionCounts.size() ; i < 9 ; i++) {
            StackPane potionStackPane = ((StackPane) potionGridPane.getChildren().get(i));
            ((ImageView) potionStackPane.getChildren().get(1)).setImage(null);
        }
    }

    @FXML
    public void potionChooseEventHandler(MouseEvent mouseEvent) {
        String imageID = ((StackPane) mouseEvent.getSource()).getChildren().get(1).getId();
        System.out.println("ID of image :" + imageID);
        PotionType potionType;
        try {
            potionType = PotionType.valueOf(((StackPane) mouseEvent.getSource()).getChildren().get(1).getId());

        }
        catch(IllegalArgumentException e) {
            System.out.println("ID of image doesn't match any PotionType");
            return;
        }
        player.consumePotion(player.getPotion(potionType));
        player.getBattle().enemyAction();
    }
}
