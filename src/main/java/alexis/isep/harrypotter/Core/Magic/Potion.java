package alexis.isep.harrypotter.Core.Magic;

import alexis.isep.harrypotter.GUI.Display;
import alexis.isep.harrypotter.Console.InputParser;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.GUI.Game;

public class Potion {
    Game game;
    Display display;
    InputParser inputParser;
    Wizard owner;
    PotionType potionType;

    public Potion(Game game, Wizard wizard, PotionType potionType) {
        this.game = game;
        display = game.getDisplay();
        inputParser = game.getInputParser();
        owner = wizard;
        this.potionType = potionType;
    }

    public void use() {
        double potionValue = potionType.getEffectValue() * owner.getHouse().getPOTION_EFFICIENCY_MULTIPLIER();
        String message = "You have consumed your " + potionType.toString();
        if (potionType.getEffect().equals(EffectType.HEAL)) {
            owner.heal(potionValue);
            message = message + " and got healed by " + potionValue + " HP";
        }
        else {
            owner.giveEffect(potionType.getEffect(), new ActiveEffect(potionType.getDuration(), potionValue));
        }
        display.announceSuccess(message);
    }

    public PotionType getPotionType() {
        return potionType;
    }
}
