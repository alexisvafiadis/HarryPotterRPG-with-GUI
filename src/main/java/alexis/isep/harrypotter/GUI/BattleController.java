package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Core.Characters.AbstractEnemy;
import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.Core.Characters.EnemyWizard;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Game.Battle;
import alexis.isep.harrypotter.Core.Magic.Spell;
import alexis.isep.harrypotter.Core.Magic.Spells.ItemSpell;
import alexis.isep.harrypotter.Core.Magic.Spells.Protego;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class BattleController {
    private final int ANIMATION_COUNT = 18;
    private MediaPlayer backgroundMusicPlayer;
    private Battle battle;
    private Wizard player;
    private AbstractEnemy enemy;
    private Timeline timeline;
    private boolean oppFirst;

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
    private Text enemyWeapon;
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
        updatePlayerWeapon();
        updateEnemyWeapon();
        setHP();
        disableAllActionButtons(true);
        List<Integer> levelsWithWeapon = Arrays.asList(2, 3);
        if (levelsWithWeapon.contains(battle.getLevel().getNumber())) {
            situationalButton.setDisable(true);
            situationalButton.setText("Attack");
        }
        playBattleMusic();
    }

    public void playBattleMusic() {
        String musicFile;
        if (enemy instanceof EnemyWizard) {
            musicFile = "StandardBattle.mp3";
        }
        else {
            musicFile = "WizardBattle.mp3";
        }

        Media sound = new Media(new File(Game.SOUND_ROOT + "music/" + musicFile).toURI().toString());
        backgroundMusicPlayer = new MediaPlayer(sound);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusicPlayer.setAutoPlay(true);
    }

    public void playSoundEffect(String soundFile, boolean isMusic, Runnable runnable) {
        String prefix;
        if (isMusic) {
            prefix = "music/";
        }
        else {
            prefix = "effects/";
        }
        Media sound = new Media(new File(Game.SOUND_ROOT + prefix + soundFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        if (runnable != null) {
            mediaPlayer.setOnEndOfMedia(runnable);
        }
        mediaPlayer.setOnPlaying(() -> {
            if (backgroundMusicPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                backgroundMusicPlayer.pause(); // Adjust the background music volume
            }
        });
        mediaPlayer.setOnEndOfMedia(() -> {
            backgroundMusicPlayer.play();
            mediaPlayer.dispose();
        });
        mediaPlayer.setAutoPlay(true);
    }

    public void playSoundEffect(String soundFile, boolean isMusic) {
        playSoundEffect(soundFile, isMusic, null);
    }

    public void updatePlayerWeapon() {
        if (player.getWeapon() == null) {
            playerWeapon.setText("None");
        }
        else {
            playerWeapon.setText(player.getWeapon().toString());
        }
    }

    public void updateEnemyWeapon() {
        if (enemy.getWeapon() == null) {
            enemyWeapon.setText("None");
        }
        else {
            enemyWeapon.setText(enemy.getWeapon().toString());
        }
    }

    public void setHP() {
        playerHPBar.setProgress(player.getHP() / player.getMaxHP());
        playerHP.setText(String.valueOf(convertToInt(player.getHP())));
        enemyHPBar.setProgress(enemy.getHP() / enemy.getMaxHP());
        enemyHP.setText(String.valueOf(convertToInt(enemy.getHP())));
    }

    public void playUpdateHPAnimation(boolean isPlayer) {
        ProgressBar HPBar;
        Text HPLabel;
        Character character;
        if (isPlayer) {
            HPBar = playerHPBar;
            HPLabel = playerHP;
            character = player;
        }
        else {
            HPBar = enemyHPBar;
            HPLabel = enemyHP;
            character = battle.getEnemy();
        }
        int newHP = convertToInt(character.getHP());
        if (newHP == Integer.parseInt(HPLabel.getText())) {return ;}
        timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(HPBar.progressProperty(), HPBar.getProgress())),
                new KeyFrame(Duration.ZERO, new KeyValue(HPLabel.textProperty(), HPLabel.getText())),
                new KeyFrame(Duration.millis(1000), new KeyValue(HPLabel.textProperty(), String.valueOf(newHP))),
                new KeyFrame(Duration.millis(1200), new KeyValue(HPBar.progressProperty(), newHP / character.getMaxHP()))
        );
        timeline.play();
        playSoundEffect("Damage.mp3", false);
    }

    @FXML
    void actionButtonEvent(ActionEvent event) {
        if (!(event.getSource() instanceof Button)) {return ;}
        disableAllActionButtons(true);
        battle.playerAction(((Button)event.getSource()).getText());
    }

    public void disableAllActionButtons(boolean disable) {
        lookAroundButton.setDisable(disable);
        castSpellButton.setDisable(disable);
        situationalButton.setDisable(disable);
        usePotionButton.setDisable(disable);
    }

    public void playSpellAnimation(Spell spell, EventHandler<ActionEvent> onFinishEventHandler, boolean fromPlayer) {
        battle.getGame().closeSubWindows();
        if (spell instanceof ItemSpell) {
            onFinishEventHandler.handle(null);
            return;
        }
        ImageView attacker, target;
        int attackerXFactor, attackerYFactor;
        if (fromPlayer) {
            attacker = playerImage;
            target = enemyImage;
            attackerXFactor = 1;
            attackerYFactor = 4;
        }
        else {
            attacker = enemyImage;
            target = playerImage;
            attackerXFactor = 8;
            attackerYFactor = 8;
        }
        double toX, toY, rotateAngle;
        boolean offensive = !(spell instanceof Protego);
        if (offensive) {
            toX = getRealImageX(target);
            toY = getRealImageY(target);
            rotateAngle = 360*4;
        }
        else {
            toX = (getRealImageX(attacker) + getRealImageX(target))/2;
            toY = getRealImageY(attacker);
            rotateAngle = 0;
        }
        int spellRadius = (int) Math.ceil(8/Math.sqrt(spell.getDefaultMasteryScore()));
        SpellShape spellShape = new SpellShape(spellRadius,spell.getColor(), offensive);
        spellShape.toFront();

        // Create a TranslateTransition to move the spell from the player to the target
        TranslateTransition translate = new TranslateTransition(Duration.seconds(1.45), spellShape);
        translate.setFromX(getRealImageX(attacker,attackerXFactor));
        translate.setFromY(getRealImageY(attacker,attackerYFactor));
        translate.setToX(toX);
        translate.setToY(toY);

        // Create a RotateTransition to rotate the spell as it moves towards the target
        RotateTransition rotate = new RotateTransition(Duration.seconds(1.52), spellShape);
        rotate.setByAngle(rotateAngle);
        translate.setOnFinished(onFinishEventHandler);
        rotate.setOnFinished((e) -> {
            battlePane.getChildren().remove(spellShape);
            playUpdateHPAnimation(!fromPlayer);
        });

        // Add the spell to the scene and play the timeline
        battlePane.getChildren().add(spellShape);
        translate.play();
        rotate.play();
        playSoundEffect("Spell.wav", false);
    }

    public double getRealImageX(ImageView imageView, double factor) {
        Bounds bounds = imageView.localToScene(imageView.getBoundsInLocal());
        return bounds.getMinX() + bounds.getWidth() / factor;
    }

    public double getRealImageY(ImageView imageView, double factor) {
        Bounds bounds = imageView.localToScene(imageView.getBoundsInLocal());
        return bounds.getMinY() + bounds.getHeight() / factor;
    }

    public double getRealImageX(ImageView imageView) {
        return getRealImageX(imageView, 2);
    }
    public double getRealImageY(ImageView imageView) {
        return getRealImageY(imageView, 2);
    }

    public void playAttackAnimation(EventHandler<ActionEvent> onFinishEventHandler, boolean fromPlayer) {
        battle.getGame().closeSubWindows();
        ImageView attackerImage, targetImage;
        ProgressBar targetBar;
        Text targetHPLabel;
        Character target;
        if (fromPlayer) {
            attackerImage = playerImage;
            targetImage = enemyImage;
            targetBar = enemyHPBar;
            targetHPLabel = enemyHP;
            target = enemy;
        }
        else {
            attackerImage = enemyImage;
            targetImage = playerImage;
            targetBar = playerHPBar;
            targetHPLabel = playerHP;
            target = player;
        }
        KeyValue targetHPBar = new KeyValue(targetBar.progressProperty(), targetBar.getProgress());
        KeyValue targetHP = new KeyValue(targetHPLabel.textProperty(), targetHPLabel.getText());


        KeyValue attackerYValue = new KeyValue(attackerImage.yProperty(), attackerImage.getY());
        KeyValue attackerXValue = new KeyValue(attackerImage.xProperty(), attackerImage.getX());

        KeyFrame framedot0s = new KeyFrame(new Duration(0), targetHP, targetHPBar, attackerXValue, attackerYValue);

        attackerYValue = new KeyValue(attackerImage.yProperty(), attackerImage.getY() + ANIMATION_COUNT);
        attackerXValue = new KeyValue(attackerImage.xProperty(), attackerImage.getX() - ANIMATION_COUNT);

        KeyFrame framedot1250s = new KeyFrame(new Duration(250), attackerXValue, attackerYValue);

        KeyValue targetYValue = new KeyValue(targetImage.yProperty(), targetImage.getY());
        KeyValue targetXValue = new KeyValue(targetImage.xProperty(), targetImage.getX());
        attackerXValue = new KeyValue(attackerImage.xProperty(), attackerImage.getX());
        attackerYValue = new KeyValue(attackerImage.yProperty(), attackerImage.getY());

        KeyFrame framedot1350s = new KeyFrame(new Duration(350), targetYValue, targetXValue, attackerXValue, attackerYValue);

        targetXValue = new KeyValue(targetImage.xProperty(), targetImage.getX() + ANIMATION_COUNT);
        targetYValue = new KeyValue(targetImage.yProperty(), targetImage.getY() - ANIMATION_COUNT);

        KeyFrame framedot1550s = new KeyFrame(new Duration(550), targetXValue, targetYValue, targetHPBar);

        int newHP = (int) Math.ceil(target.getHP());
        KeyValue newtargetHP = new KeyValue(targetHPLabel.textProperty(), String.valueOf(newHP));
        KeyValue newtargetHPBar = new KeyValue(targetBar.progressProperty(), newHP / target.getMaxHP());
        targetXValue = new KeyValue(targetImage.xProperty(), targetImage.getX());
        targetYValue = new KeyValue(targetImage.yProperty(), targetImage.getY());
        System.out.println("New HP : " + newHP);

        KeyFrame framedot1800s = new KeyFrame(new Duration(800), newtargetHP, newtargetHPBar, targetXValue, targetYValue);
        timeline = new Timeline();
        timeline.getKeyFrames().addAll( framedot0s, framedot1250s, framedot1350s, framedot1550s, framedot1800s
        );
        timeline.setOnFinished(onFinishEventHandler);
        timeline.play();
        playSoundEffect("Attack.mp3", false);
    }

    public int convertToInt(double a) {
        return (int) Math.ceil(a);
    }

    public void stopMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
        }
    }
}
