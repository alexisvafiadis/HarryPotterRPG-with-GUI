package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.Core.Game.Game;
import alexis.isep.harrypotter.Core.Magic.ActiveEffect;
import alexis.isep.harrypotter.Core.Magic.EffectType;
import alexis.isep.harrypotter.Core.Magic.SimpleSpell;

public class Protego extends SimpleSpell {
    private final int EFFECT_DURATION = 2;

    public Protego(Game game, Character wizard) {
        super(game, wizard, "Protego", 5, 1, 0.63, 0.29);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful()) {
            target.giveEffect(EffectType.SHIELD, new ActiveEffect(EFFECT_DURATION, 0.9));
            displayCastMessage("created a shimmering, transparent shield that can block spells");
        }
    }

    public void displayInstructions() {
        inputParser.waitForYes("Point your wand in the direction of potential attacks and make a circular motion while saying \"Protego\"\n" +
                "A translucent shield will appear, which will protect you from the incoming attack.\n" +
                "Remember to move quickly and accurately, as the shield will only last for a short time.\n" +
                "Understood?");
    }
}
