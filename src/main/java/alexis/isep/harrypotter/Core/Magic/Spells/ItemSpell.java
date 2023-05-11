package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.Spell;
import javafx.scene.paint.Color;

public abstract class ItemSpell extends Spell {
    private String stringForItem;

    public ItemSpell(Game game, Character wizard, String name, Color color, int range, float cooldown, double learningExponent, double defaultMasteryScore, String stringForItem) {
        super(game, wizard, name, color, range, cooldown, learningExponent, defaultMasteryScore);
        this.stringForItem = stringForItem;
    }

    public String getStringForItem() {
        return stringForItem;
    }
}
