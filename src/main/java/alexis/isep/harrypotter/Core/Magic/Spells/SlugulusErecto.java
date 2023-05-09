package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.ActiveEffect;
import alexis.isep.harrypotter.Core.Magic.EffectType;
import alexis.isep.harrypotter.Core.Magic.SimpleSpell;

public class SlugulusErecto extends SimpleSpell {
    private final int EFFECT_DURATION = 3;

    public SlugulusErecto(Game game, Character wizard) {
        super(game, wizard, "Slugulus Erecto", 5, 1, 0.43, 0.25);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            target.giveEffect(EffectType.SLUG_VOMITING, new ActiveEffect(EFFECT_DURATION, 0.72));
            showSuccessfulCast(null);
        }
    }

    @Override
    public void displayInstructions() {
        inputParser.waitForConfirmation("To begin casting the Slug-vomiting Charm, visualize your target being overcome with the uncontrollable urge to vomit slugs.\n" +
                "Firmly grasp your wand and prepare to perform a smooth, circular motion while keeping your focus on the target.\n" +
                "As you execute the circular motion with your wand, confidently and clearly enunciate the incantation Slugulus Eructo.\n" +
                "The effectiveness of the Slug-vomiting Charm relies on the caster's ability to concentrate on their target and deliver the incantation with precision and confidence."+
                "Understood?");
    }
}
