package alexis.isep.harrypotter.Core.Magic;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.Game;
import javafx.scene.paint.Color;

public abstract class SimpleSpell extends Spell {
    //spell that just needs a Character target as an argument

    public SimpleSpell(Game game, Character wizard, String name, Color color, int range, float cooldown, double learningExponent, double defaultMasteryScore) {
        super(game, wizard, name, color, range, cooldown, learningExponent, defaultMasteryScore);
    }

    public abstract void cast(Character target);
}