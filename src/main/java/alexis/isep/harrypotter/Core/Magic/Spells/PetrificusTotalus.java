package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.ActiveEffect;
import alexis.isep.harrypotter.Core.Magic.EffectType;
import alexis.isep.harrypotter.Core.Magic.SimpleSpell;

public class PetrificusTotalus extends SimpleSpell {
    private final int EFFECT_DURATION = 2;

    public PetrificusTotalus(Game game, Character wizard) {
        super(game, wizard, "Petrificus Totalus", 5, 1, 0.52, 0.27);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            target.giveEffect(EffectType.STUN, new ActiveEffect(EFFECT_DURATION, 1));
            displayCastMessage(" froze " + target.getName() + "'s body completely, becoming unmovable");
        }
    }

    public void displayInstructions() {
        inputParser.waitForConfirmation("Aim your wand at the target and focus on their movement.\n" +
                "Visualize them becoming rigid, and use a commanding tone when casting the spell\n" +
                "A bright blue light will shoot from your wand and hit the target, causing them to become completely paralyzed and unable to move.\n" +
                "Understood?");
    }
}
