package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.ActiveEffect;
import alexis.isep.harrypotter.Core.Magic.EffectType;
import alexis.isep.harrypotter.Core.Magic.SimpleSpell;
import javafx.scene.paint.Color;

public class Imperius extends SimpleSpell {
    private final int EFFECT_DURATION = 3;

    public Imperius(Game game, Character wizard) {
        super(game, wizard, "Imperius", Color.PURPLE,5, 1, 0.21, 0.12);
        setForbidden(true);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            showSuccessfulCast(target.getName() + "'s eyes glaze over");
            target.giveEffect(EffectType.UNDER_CONTROL, new ActiveEffect(EFFECT_DURATION, 0.7));
        }
    }

    @Override
    public void displayInstructions() {

    }
}
