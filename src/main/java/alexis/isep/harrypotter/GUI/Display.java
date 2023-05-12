package alexis.isep.harrypotter.GUI;

import alexis.isep.harrypotter.Console.ConsoleColors;
import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.DialogController;
import alexis.isep.harrypotter.GUI.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Display {
    private Game game;
    Timeline timeline;
    private int charIndex;
    private int messageIndex;

    private List<Color> colors;
    private List<String> messages;
    private String currentMessage;
    private int DEFAULT_WRITING_DELAY = 40;
    private final int FAST_WRITING_DELAY = 15;
    private final int VERY_FAST_WRITING_DELAY = 2;
    @FXML
    private Label textLabel;
    @FXML
    private AnchorPane dialogPane;

    public Display(Game game) {
        this.game = game;
        messages = new ArrayList<>();
        colors = new ArrayList<>();
    }

    public void adaptTextSize(double resizeCoeff) {
        double leftAnchor = 0.16 / resizeCoeff;
        double topAnchor = 0.3 / resizeCoeff;
        AnchorPane.setLeftAnchor(textLabel, dialogPane.getPrefWidth() * leftAnchor);
        AnchorPane.setTopAnchor(textLabel, dialogPane.getPrefHeight() * topAnchor);
        AnchorPane.setBottomAnchor(textLabel, dialogPane.getPrefHeight() * topAnchor);
        AnchorPane.setRightAnchor(textLabel, dialogPane.getPrefWidth() * leftAnchor);
        textLabel.setMaxWidth(500);
        textLabel.setMaxHeight(500);
        textLabel.setWrapText(true);
    }

    public void slowPrint(String output, Color color, boolean nextLine) {
        charIndex = 0;
        messages.add(output);
        colors.add(color);
    }

    public void setOnFinish(EventHandler<ActionEvent> onFinishEventHandler) {
        if (messages.size() == 0) {
            onFinishEventHandler.handle(null);
            return;
        }
        timeline = new Timeline();
        messageIndex = 0;
        charIndex = 0;
        currentMessage = messages.get(messageIndex);
        textLabel.setTextFill(colors.get(messageIndex));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(VERY_FAST_WRITING_DELAY), event -> {
            if (charIndex < currentMessage.length()) {
                String substring = currentMessage.substring(0, charIndex + 1);
                setText(substring);
                charIndex++;
            } else {
                if (messageIndex >= messages.size() - 1) {
                    System.out.println("Time line has been stopped at cycle count " + timeline.getCycleCount());
                    messages.clear();
                    colors.clear();
                }
                else {
                    charIndex = 0;
                    messageIndex++;
                    currentMessage = messages.get(messageIndex);
                    textLabel.setTextFill(colors.get(messageIndex));
                }
            }
        }));
        timeline.setOnFinished(onFinishEventHandler);
        int cycleCount = 0;
        for (String message : messages) {
            cycleCount += message.length();
        }
        cycleCount += messages.size();
        timeline.setCycleCount(cycleCount);
        System.out.println("The timeline has been started and the event has been added");
        System.out.println("Content of getOnFinished : " + timeline.getStatus().toString());
        System.out.println("Cycle count set : " + cycleCount);
        timeline.play();
    }

    public void slowPrint(String output, Color color) {slowPrint(output, color, true); }

    public void announceReward(String announcement) {
        slowPrint(announcement, Color.YELLOW);
    }

    public void announceDiscovery(String finding) { slowPrint(finding, Color.CYAN); }

    public void announceFail(String fail) { slowPrint(fail, Color.RED); }

    public void announceSuccess(String success) { slowPrint(success, Color.GREEN); }

    public void displayInfo(String information) { slowPrint(information, Color.BLACK); }

    public void displayRequest(String request) { slowPrint(request, Color.BLUE); }

    //Later : Add image of character in front of text in JavaFX
    public void displayQuote(Character character, String quote) { slowPrint(character.getName()+ " : " + quote, Color.BLACK) ;}

    public void congratulate(String congratulations) { slowPrint(congratulations, Color.LIGHTYELLOW); }

    public void displayError(String error) {slowPrint(error, Color.ORANGERED); }

    public void askForNumberToStringInput(String request, HashMap<Integer, String> validInputs, String linkword) {
        displayRequest(request);
        displayInfo("Please enter : ");
        for (int key : validInputs.keySet()) {
            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + key + " " + ConsoleColors.RESET + linkword + " " + ConsoleColors.WHITE_BOLD_BRIGHT + validInputs.get(key));
        }
    }

    public void setText(String text) {
        textLabel.setText(text);
    }

    public void hide() {
        dialogPane.setVisible(false);
    }
    public void displayHP(Character character, boolean own) {
        Color color;
        String message;
        if (own) {
            color = Color.RED;
            message = "Your HP : " + Math.round(character.getHP());
        }
        else {
            color = Color.GREEN;
            message = character.getName() + "'s HP : " + Math.round(character.getHP());
        }
        slowPrint(message, color);
    }
}
