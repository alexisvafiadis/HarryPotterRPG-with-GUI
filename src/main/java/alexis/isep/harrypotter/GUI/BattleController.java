package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Core.Characters.AbstractEnemy;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Levels.Essentials.Battle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BattleController {
    private Battle battle;
    private Wizard player;
    private AbstractEnemy enemy;
    private List<KeyFrame> keyFrames;

    @FXML
    private AnchorPane battlePane;

    @FXML
    private ImageView background;

    @FXML
    private Text playerName;

    @FXML
    private Text playerHP;

    @FXML
    private Text playerWeapon;

    @FXML
    private Text playerType;

    @FXML
    private ProgressBar playerHPBar;

    @FXML
    private ImageView playerImage;

    @FXML
    private Text playerDamage;

    @FXML
    private Text enemyName;

    @FXML
    private Text enemyHP;

    @FXML
    private Text enemyLevel;

    @FXML
    private Text enemyType;

    @FXML
    private ProgressBar enemyHPBar;

    @FXML
    private ImageView enemyImage;

    @FXML
    private Text enemyDamage;

    @FXML
    private Button lookAroundButton;

    @FXML
    private Button castSpellButton;

    @FXML
    private Button situationalButton;

    @FXML
    private Button usePotionButton;

    private boolean oppFirst;

    public BattleController(Battle battle) {
        this.battle = battle;
        player = battle.getPlayer();
        enemy = battle.getEnemy();
    }

    @FXML
    void initialize() {
        Image image = battle.getLevel().getImage();
        background.setImage(image);
        playerName.setText(player.getName());
        playerImage.setImage(player.getImage());

        enemyName.setText(enemy.getName());
        enemyImage.setImage(enemy.getImage());
        updateWeapon();
        updateHP();

        List<Integer> levelsWithWeapon = Arrays.asList(2, 3);
        if (levelsWithWeapon.contains(battle.getLevel().getNumber())) {
            situationalButton.setDisable(true);
            situationalButton.setText("Attack");
        }
    }

    public void updateWeapon() {
        if (player.getWeapon() == null) {
            playerWeapon.setText("None");
        }
        else {
            playerWeapon.setText(player.getWeapon().toString());
        }
    }

    public void updateHP() {
        playerHPBar.setProgress(player.getHP() / player.getMaxHP());
        playerHP.setText(String.valueOf(player.getHP()));
        enemyHPBar.setProgress(enemy.getHP() / enemy.getMaxHP());
        enemyHP.setText(String.valueOf(enemy.getHP()));
    }

    @FXML
    void actionButtonEvent(ActionEvent event) {
        if (!(event.getSource() instanceof Button)) {return ;}
        disableAllActionButtons();
        battle.playerAction(((Button)event.getSource()).getText());
    }

    public void showRoundAttacks() {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(keyFrames);
        timeline.play();

        timeline.setOnFinished(e -> {
            if (!player.isAlive() || !enemy.isAlive()) {
                usePotionButton.fire();
            }
        });
    }

    public void disableAllActionButtons() {
        lookAroundButton.setDisable(true);
        castSpellButton.setDisable(true);
        situationalButton.setDisable(true);
        usePotionButton.setDisable(true);
    }

    public void nextRound() {
        keyFrames = new ArrayList<>();
        lookAroundButton.setDisable(false);
        castSpellButton.setDisable(false);
        situationalButton.setDisable(false);
        usePotionButton.setDisable(false);
    }

    public void playerAttackAnimation(int damage) {
        if (!player.isAlive()) return;
        KeyValue oppHPBar = new KeyValue(enemyHPBar.progressProperty(), enemyHPBar.getProgress());
        KeyValue oppHP = new KeyValue(enemyHP.textProperty(), enemyHP.getText());

        KeyFrame framedot0s = new KeyFrame(new Duration(0), oppHP, oppHPBar);

        KeyValue playerYValue = new KeyValue(playerImage.yProperty(), playerImage.getY());
        KeyValue playerXValue = new KeyValue(playerImage.xProperty(), playerImage.getX());

        KeyFrame framedot100s = new KeyFrame(new Duration(1000), playerXValue, playerYValue);

        playerXValue = new KeyValue(playerImage.xProperty(), playerImage.getX() + 10);
        playerYValue = new KeyValue(playerImage.yProperty(), playerImage.getY() - 10);

        KeyFrame framedot125s = new KeyFrame(new Duration(1250), playerXValue, playerYValue);

        KeyValue oppDmg = new KeyValue(playerDamage.textProperty(), String.valueOf(damage));
        KeyValue oppYValue = new KeyValue(enemyImage.yProperty(), enemyImage.getY());
        KeyValue oppXValue = new KeyValue(enemyImage.xProperty(), enemyImage.getX());
        playerXValue = new KeyValue(playerImage.xProperty(), playerImage.getX());
        playerYValue = new KeyValue(playerImage.yProperty(), playerImage.getY());

        KeyFrame framedot15s = new KeyFrame(new Duration(1350), oppDmg, oppXValue, oppYValue, playerXValue, playerYValue);

        oppXValue = new KeyValue(enemyImage.xProperty(), enemyImage.getX() + 10);
        oppYValue = new KeyValue(enemyImage.yProperty(), enemyImage.getY() - 10);

        KeyFrame framedot175s = new KeyFrame(new Duration(1550), oppXValue, oppYValue, oppHPBar, oppDmg);

        oppHP = new KeyValue(enemyHP.textProperty(), String.valueOf(enemy.getHP()));
        oppHPBar = new KeyValue(enemyHPBar.progressProperty(), enemy.getHP() / enemy.getMaxHP());
        oppXValue = new KeyValue(enemyImage.xProperty(), enemyImage.getX());
        oppYValue = new KeyValue(enemyImage.yProperty(), enemyImage.getY());
        oppDmg = new KeyValue(playerDamage.textProperty(), null);

        KeyFrame framedot200s = new KeyFrame(new Duration(1800), oppHPBar, oppXValue, oppYValue, oppDmg, oppHP);
        Collections.addAll(keyFrames,new KeyFrame[]{framedot0s, framedot15s, framedot100s, framedot125s, framedot175s, framedot200s});
    }

    public void enemyAttackAnimation(int damage) {
        int time = 1800;

        if (!enemy.isAlive()) return;
        // all the keyframe stuff
        KeyValue plyHPBar = new KeyValue(playerHPBar.progressProperty(), playerHPBar.getProgress());
        KeyValue plyHP = new KeyValue(playerHP.textProperty(), playerHP.getText());

        KeyFrame framedot0s = new KeyFrame(new Duration(time), plyHPBar, plyHP);

        KeyValue oppYValue = new KeyValue(enemyImage.yProperty(), enemyImage.getY());
        KeyValue oppXValue = new KeyValue(enemyImage.xProperty(), enemyImage.getX());

        KeyFrame framedot1000s = new KeyFrame(new Duration(time + 1000), oppXValue, oppYValue);

        oppXValue = new KeyValue(enemyImage.xProperty(), enemyImage.getX() - 10);
        oppYValue = new KeyValue(enemyImage.yProperty(), enemyImage.getY() - 10);

        KeyFrame framedot1250s = new KeyFrame(new Duration(time + 1250), oppXValue, oppYValue);

        KeyValue oppDmg = new KeyValue(enemyDamage.textProperty(), String.valueOf(damage));
        KeyValue playerYValue = new KeyValue(playerImage.yProperty(), playerImage.getY());
        KeyValue playerXValue = new KeyValue(playerImage.xProperty(), playerImage.getX());
        oppXValue = new KeyValue(enemyImage.xProperty(), enemyImage.getX());
        oppYValue = new KeyValue(enemyImage.yProperty(), enemyImage.getY());

        KeyFrame framedot1350s = new KeyFrame(new Duration(time + 1350), oppXValue, oppYValue, playerYValue, playerXValue, oppDmg);

        playerXValue = new KeyValue(playerImage.xProperty(), playerImage.getX() - 10);
        playerYValue = new KeyValue(playerImage.yProperty(), playerImage.getY() + 10);

        KeyFrame framedot1550s = new KeyFrame(new Duration(time + 1550), playerYValue, playerXValue, plyHPBar, oppDmg);

        plyHPBar = new KeyValue(playerHPBar.progressProperty(), player.getHP() / player.getMaxHP());
        playerXValue = new KeyValue(playerImage.xProperty(), playerImage.getX());
        playerYValue = new KeyValue(playerImage.yProperty(), playerImage.getY());
        oppDmg = new KeyValue(enemyDamage.textProperty(), null);
        plyHP = new KeyValue(playerHP.textProperty(), String.valueOf(player.getHP()));

        KeyFrame framedot1800s = new KeyFrame(new Duration(time + 1800), playerXValue, playerYValue, oppDmg, plyHPBar, plyHP);
        Collections.addAll(keyFrames,new KeyFrame[]{framedot0s, framedot1000s, framedot1250s, framedot1350s, framedot1550s, framedot1800s});
    }

}
