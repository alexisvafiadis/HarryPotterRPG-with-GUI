package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.ActiveEffect;
import alexis.isep.harrypotter.Core.Magic.EffectType;
import alexis.isep.harrypotter.Core.Magic.SimpleSpell;

public class Rictumsempra extends SimpleSpell {
    private final int EFFECT_DURATION = 3;

    public Rictumsempra(Game game, Character wizard) {
        super(game, wizard, "Rictumsempra", 5, 1, 0.41, 0.25);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            target.giveEffect(EffectType.LAUGH, new ActiveEffect(EFFECT_DURATION, 0.6));
            showSuccessfulCast(target.getName() + "'s contorted into a grimace of pain and mirth");
        }
    }

    @Override
    public void displayInstructions() {
        inputParser.waitForConfirmation("First, make sure you have your wand at the ready and choose your target carefully.\n" +
                "The Rictumsempra spell is a jinx that causes your target to experience a tickling sensation, so it's a harmless spell that wouldn't be useful in a dangerous situation.\n" +
                "Point your wand at your target and clearly enunciate Rictumsempra while flicking your wand in a zig-zag motion.\n" +
                "If successful, your target should start to experience an uncontrollable tickling sensation, causing them to double over with laughter.\n" +
                "Understood?");
    }
}
