package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.Core.Game.Game;
import alexis.isep.harrypotter.Core.Magic.Spell;

public abstract class ItemSpell extends Spell {
    private String stringForItem;

    public ItemSpell(Game game, Character wizard, String name, int range, float cooldown, double learningExponent, double defaultMasteryScore, String stringForItem) {
        super(game, wizard, name, range, cooldown, learningExponent, defaultMasteryScore);
        this.stringForItem = stringForItem;
    }

    public String getStringForItem() {
        return stringForItem;
    }
}
