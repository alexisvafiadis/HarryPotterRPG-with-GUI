package alexis.isep.harrypotter.Core.Game;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.*;
import alexis.isep.harrypotter.Console.InputParser;
import alexis.isep.harrypotter.Core.Characters.AbstractEnemy;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Items.Item;
import alexis.isep.harrypotter.Core.Items.ItemType;
import alexis.isep.harrypotter.Core.Items.Weapon;
import alexis.isep.harrypotter.Core.Game.Levels.Level2;
import alexis.isep.harrypotter.Core.Game.Levels.Level3;
import alexis.isep.harrypotter.Core.Magic.*;
import alexis.isep.harrypotter.Core.Magic.Spells.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Battle {
    protected Game game;
    protected Display display;
    protected InputParser inputParser;

    protected Level level;
    protected Wizard player;
    protected AbstractEnemy enemy;
    protected BattleController battleController;
    protected int roundNumber;
    protected HashMap<Integer, String> spellInputs;
    protected EventHandler<ActionEvent> onFinishedEventHandler;

    public Battle(Game game, Level level, Wizard player, AbstractEnemy enemy, EventHandler<ActionEvent> onFinishedEventHandler) {
        this.game = game;
        this.display = game.getDisplay();
        this.inputParser = game.getInputParser();
        this.level = level;
        this.player = player;
        this.enemy = enemy;
        this.onFinishedEventHandler = onFinishedEventHandler;
        player.setBattle(this);
        enemy.setBattle(this);
        if (game.isInDebugMode()) {
            enemy.setHP(1);
        }
        roundNumber = 1;
        spellInputs = getSpellInputs();
    }

    public void start() {
        displayBattleStartMessage();
        askPlayerForAction();
    }

    public boolean getBattleContinueCondition() {
        return (!getBattleLossCondition() && !getBattleWinCondition());
    }

    public boolean getBattleLossCondition() {
        return (!player.isAlive());
    }
    public boolean getBattleWinCondition() {
        return (!enemy.isAlive());
    }

    public void enemyAction() {
        game.closeSubWindows();
        if (enemy.isAlive()) {
            display.setOnFinish((e) -> {
                if (roundNumber % enemy.getAttackDelay() == 0) {
                    enemy.act();
                } else {
                    display.displayInfo(enemy.getName() + " is preparing for its next attack.");
                    finishRound();
                }
            });
        }
        else {
            actionOnBattleEnd();
        }
    }

    public void finishRound() {
        System.out.println("Round " + roundNumber + " finished.");
        display.setOnFinish((e) -> {
        enemy.finishRound();
        player.finishRound();
        roundNumber += 1;
        if (!getBattleContinueCondition()) {
            actionOnBattleEnd();
        }
        else {
            askPlayerForAction();
        }
        });
    }

    public void actionOnBattleEnd() {
        if (getBattleLossCondition()) {
            actionIfLoss();
        } else {
            if (getBattleWinCondition()) {
                actionIfWin();
            }
            else {
                display.displayError("There has been an error, the battle is supposed to end but nobody seems to have won.");
                askPlayerForAction();
            }
        }
    }

    public void actionIfWin() {
        displayBattleWinMessage();
        finish();
        display.setOnFinish(onFinishedEventHandler);
    }

    public void actionIfLoss() {
        finish();
        display.setOnFinish((e) -> level.fail());
    }
    public void askPlayerForAction() {
        display.displayInfo("What do you want to do? Choose an action.");
        display.setOnFinish((finish) -> {
            battleController.disableAllActionButtons(false);
        });
    }

    public void finish() {
        player.setBattle(null);
        enemy.setBattle(null);
    }

    public void goBackAndAskPlayerForAction() {
        game.closeSubWindows();
        askPlayerForAction();
    }
    public void askForSpell() {
        display.displayInfo("Pick a spell to use.");
        SpellCollectionControl controller = new SpellCollectionControl(player);
        controller.setBattle(this);
        display.setOnFinish((finish) ->
            game.showElement("SpellCollection", param -> controller,3.1));
    }
    public void playerAction(String choice) {
        switch (choice) {
            case "Look around":
                playerLookAround();
                break;
            case "Cast a spell":
                if (player.hasEffect(EffectType.DISARM)) {
                    display.announceFail("You cannot cast a spell because you are disarmed");
                    askPlayerForAction();
                } else {
                    askForSpell();
                }
                break;
            case "Hide":
                playerHide();
                break;
            case "Use a potion":
                askPlayerForPotion();
                break;
            case "Attack":
                player.attack(enemy);
                break;
            default:
                display.displayError("There seems to be a problem with the action you selected.");
                askPlayerForAction();
                break;
        }
    }

    public void askPlayerForPotion() {
        if (player.hasAnyPotion()) {
            display.displayInfo("Choose a potion that you want to consume");
            PotionInventoryController potionInventoryController = new PotionInventoryController(player);
            display.setOnFinish((finish) ->
                    game.showElement("PotionInventory", param -> potionInventoryController,3));
        }
        else {
            warnAndGoBack("You don't have any potion.");
        }
    }

    public void playerLookAround() {
        double random = Math.random();
        if (random < 0.5) {
            PotionType potionType =  generatePotionType();
            display.announceDiscovery("Good job, you have found a " + potionType.toString() + "!");
            player.addPotion(new Potion(game, player, potionType));
        }
        else if (random < 0.9) {
            ItemType randomItemType = generateItemType();
            display.announceDiscovery("You have found an item. It looks like a " + randomItemType.toString());
            level.addItem(new Item(randomItemType));
        }
        else {
            display.announceFail("Unfortunately, you haven't found anything.");
        }
        enemyAction();
    }

    public void playerHide() {
        player.giveEffect(EffectType.HIDE, new ActiveEffect(1, 0.75));
        enemyAction();
    }
    public void playerCastSpell(String spellChoice) {
        Spell spell = player.getKnownSpells().get(spellChoice);
        if (spellChoice.equals("Accio")) {
            if (level instanceof Level2) {
                ((Accio) spell).cast(Weapon.BASILISK_FANG);
            }
            else {
                warnUselessSpellAndGoBack();
            }
        }
        else if (spellChoice.equals("Expecto Patronum")) {
            if (level instanceof Level3) {
                if (((Expectopatronum) spell).cast()) {
                    ((Level3) level).setExpectoPatronumUsed();
                    enemy.die();
                }
            }
            else {
                warnUselessSpellAndGoBack();
            }
        }
        else if (spell instanceof ItemSpell) {
            List<Item> items = level.getItems();
            if (items.isEmpty()) {
                warnAndGoBack("You haven't found any item yet.");
            }
            else {
                game.closeSubWindows();
                display.displayInfo("Pick an item to use ");
                display.setOnFinish((e) -> game.showElement("ItemInventory",param -> new ItemInventoryController(this,spellChoice),2.3));
            }
        }
        else if (player.getKnownSpells().get(spellChoice) instanceof SimpleSpell) {
            if (player.getEffectProbability(EffectType.UNDER_CONTROL)) {
                ((SimpleSpell) spell).cast(player);
            }
            else {
                ((SimpleSpell) spell).cast(enemy);
            }
        }
        else {
            warnUselessSpellAndGoBack();
        }
    }

    public void playerCastItemSpell(String spellChoice, int itemIndex) {
        List<Item> items = level.getItems();
        Item item = items.get(itemIndex);
        Spell spell = player.getKnownSpells().get(spellChoice);
        if (spell instanceof WingardiumLeviosa) {
            if (((WingardiumLeviosa) spell).cast(item, enemy)) {
                items.remove(item);
            }
        }
        else if (spell instanceof Engorgio) {
            ((Engorgio) spell).cast(item);
        }
        else if (spell instanceof Reducto) {
            if (((Reducto) spell).cast(item, enemy)) {
                items.remove(item);
            }
        }
    }
    public HashMap<Integer, String> getSpellInputs() {
        HashMap<Integer, String> spellInputs = new HashMap<>();
        int i = 1;
        LinkedHashMap<String, Spell> playerKnownSpells = player.getKnownSpells();
        for (String spellName : playerKnownSpells.keySet()) {
            spellInputs.put(i, spellName);
            i++;
        }
        return spellInputs;
    }

    public HashMap<Integer, String> getItemInputs() {
        List<Item> items = level.getItems();
        HashMap<Integer, String> itemInputs = new HashMap<>();
        for (int itemIndex = 1; itemIndex < items.size() + 1 ; itemIndex++) {
            itemInputs.put(itemIndex, items.get(itemIndex - 1).getItemType().toString());
        }
        return itemInputs;
    }

    public ItemType generateItemType() {
        // I used weighted randomness instead of generating a random number from 0 to 1 and using a lot of ifs so that if I add an item I don't need to
        // change the other numbers
        // Inspired from https://stackoverflow.com/questions/6737283/weighted-randomness-in-java

        double totalWeight = 0.0;
        List<ItemType> possibleItemTypes = level.getPossibleItemTypes();

        for (ItemType pt : level.getPossibleItemTypes()) {
            totalWeight += pt.getWeight();
        }

        int idx = 0;
        for (double r = Math.random() * totalWeight; idx < possibleItemTypes.size() - 1; ++idx) {
            r -= possibleItemTypes.get(idx).getWeight();
            if (r <= 0.0) break;
        }
        return possibleItemTypes.get(idx);
    }

    public PotionType generatePotionType() {
        PotionType[] potionTypes = PotionType.values();

        double totalWeight = 0.0;
        for (PotionType pt : potionTypes) {
            totalWeight += pt.getWeight();
        }

        int idx = 0;
        for (double r = Math.random() * totalWeight; idx < potionTypes.length - 1; ++idx) {
            r -= potionTypes[idx].getWeight();
            if (r <= 0.0) break;
        }
        return potionTypes[idx];
    }

    public void displayBattleStartMessage() {
        if (enemy.getCustomBattleStartMessage() != null) {
            display.displayQuote(enemy, enemy.getCustomBattleStartMessage());
        }
        display.displayInfo("You have started a battle against " + enemy.getName());
    }

    public void displayBattleWinMessage() {
        display.announceSuccess("Well done, you have killed " + enemy.getName());
    }

    public void warnAndGoBack(String message) {
        display.announceFail(message);
        goBackAndAskPlayerForAction();
    }

    public void handleCharacterAction(Character character, boolean fromPlayer) {
        if (fromPlayer) {
            enemyAction();
        }
        else {
            finishRound();
        }
    }

    public void warnUselessSpellAndGoBack() {
        warnAndGoBack("Sorry, this spell is useless here.");
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public Wizard getPlayer() {
        return player;
    }

    public AbstractEnemy getEnemy() {
        return enemy;
    }

    public Game getGame() {
        return game;
    }
    public Display getDisplay() {
        return display;
    }

    public InputParser getInputParser() {
        return inputParser;
    }

    public Level getLevel() {
        return level;
    }

    public void setBattleController(BattleController battleController) {
        this.battleController = battleController;
    }

    public BattleController getBattleController() {
        return battleController;
    }
}
