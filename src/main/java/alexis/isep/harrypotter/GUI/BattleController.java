package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Core.Characters.AbstractEnemy;
import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Levels.Essentials.Battle;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BattleController {
    private final int ANIMATION_COUNT = 18;
    private Battle battle;
    private Wizard player;
    private AbstractEnemy enemy;
    private Timeline timeline;

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
    private ProgressBar playerHPBar;

    @FXML
    private ImageView playerImage;

    @FXML
    private Text enemyName;

    @FXML
    private Text enemyHP;

    @FXML
    private ProgressBar enemyHPBar;

    @FXML
    private ImageView enemyImage;

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

    public void disableAllActionButtons() {
        lookAroundButton.setDisable(true);
        castSpellButton.setDisable(true);
        situationalButton.setDisable(true);
        usePotionButton.setDisable(true);
    }

    public void finishRound() {
        lookAroundButton.setDisable(false);
        castSpellButton.setDisable(false);
        situationalButton.setDisable(false);
        usePotionButton.setDisable(false);
    }

    public void playSpellAnimation(EventHandler<ActionEvent> onFinishEventHandler, boolean fromPlayer) {
        ImageView attacker, target;
        if (fromPlayer) {
            attacker = playerImage;
            target = enemyImage;
        }
        else {
            attacker = enemyImage;
            target = playerImage;
        }
        Circle spell = new Circle(20, Color.YELLOW);

        // Create a TranslateTransition to move the spell from the player to the target
        TranslateTransition translate = new TranslateTransition(Duration.seconds(1), spell);
        translate.setToX(target.getX() - attacker.getX());
        translate.setToY(target.getY() - attacker.getY());

        // Create a RotateTransition to rotate the spell as it moves towards the target
        RotateTransition rotate = new RotateTransition(Duration.seconds(1), spell);
        rotate.setByAngle(360);

        // Add the transitions to a sequential timeline
        timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(spell.translateXProperty(), attacker.getX())),
                new KeyFrame(Duration.ZERO, new KeyValue(spell.translateYProperty(), attacker.getY())),
                new KeyFrame(Duration.ZERO, new KeyValue(spell.rotateProperty(), 0)),
                new KeyFrame(Duration.seconds(1), new KeyValue(spell.translateXProperty(), target.getX())),
                new KeyFrame(Duration.seconds(1), new KeyValue(spell.translateYProperty(), target.getY())),
                new KeyFrame(Duration.seconds(1), new KeyValue(spell.rotateProperty(), 360))
        );

        // Add the spell to the scene and play the timeline
        battlePane.getChildren().add(spell);
        timeline.setOnFinished(onFinishEventHandler);
        timeline.play();
    }
    public void playAttackAnimation(EventHandler<ActionEvent> onFinishEventHandler, boolean fromPlayer) {
        ImageView attackerImage, targetImage;
        ProgressBar attackerBar, targetBar;
        Text targetHPLabel;
        Character target;
        if (fromPlayer) {
            attackerImage = playerImage;
            targetImage = enemyImage;
            targetBar = enemyHPBar;
            targetHPLabel = enemyHP;
            target = player;
        }
        else {
            attackerImage = enemyImage;
            targetImage = playerImage;
            targetBar = playerHPBar;
            targetHPLabel = playerHP;
            target = enemy;
        }
        KeyValue targetHPBar = new KeyValue(targetBar.progressProperty(), targetBar.getProgress());
        KeyValue targetHP = new KeyValue(targetHPLabel.textProperty(), targetHPLabel.getText());

        KeyFrame framedot0s = new KeyFrame(new Duration(0), targetHP, targetHPBar);

        KeyValue attackerYValue = new KeyValue(attackerImage.yProperty(), attackerImage.getY());
        KeyValue attackerXValue = new KeyValue(attackerImage.xProperty(), attackerImage.getX());

        KeyFrame framedot1000s = new KeyFrame(new Duration(1000), attackerXValue, attackerYValue);

        attackerYValue = new KeyValue(attackerImage.yProperty(), attackerImage.getY() + ANIMATION_COUNT);
        attackerXValue = new KeyValue(attackerImage.xProperty(), attackerImage.getX() - ANIMATION_COUNT);

        KeyFrame framedot1250s = new KeyFrame(new Duration(1250), attackerXValue, attackerYValue);

        KeyValue targetYValue = new KeyValue(targetImage.yProperty(), targetImage.getY());
        KeyValue targetXValue = new KeyValue(targetImage.xProperty(), targetImage.getX());
        attackerXValue = new KeyValue(attackerImage.xProperty(), attackerImage.getX());
        attackerYValue = new KeyValue(attackerImage.yProperty(), attackerImage.getY());

        KeyFrame framedot1350s = new KeyFrame(new Duration(1350), targetYValue, targetXValue, attackerXValue, attackerYValue);

        targetXValue = new KeyValue(targetImage.xProperty(), targetImage.getX() + ANIMATION_COUNT);
        targetYValue = new KeyValue(targetImage.yProperty(), targetImage.getY() - ANIMATION_COUNT);

        KeyFrame framedot1550s = new KeyFrame(new Duration(1550), targetXValue, targetYValue, targetHPBar);

        int newHP = (int) Math.ceil(target.getHP());
        targetHP = new KeyValue(targetHPLabel.textProperty(), String.valueOf(newHP));
        targetHPBar = new KeyValue(targetBar.progressProperty(), newHP / target.getMaxHP());
        targetXValue = new KeyValue(targetImage.xProperty(), targetImage.getX());
        targetYValue = new KeyValue(targetImage.yProperty(), targetImage.getY());

        KeyFrame framedot1800s = new KeyFrame(new Duration(1800), targetHP, targetHPBar, targetXValue, targetYValue);
        timeline = new Timeline();
        timeline.getKeyFrames().addAll( framedot0s, framedot1000s, framedot1250s, framedot1350s, framedot1550s, framedot1800s
        );
        timeline.setOnFinished(onFinishEventHandler);
        timeline.play();
    }

    /*
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
    }

     */

}
