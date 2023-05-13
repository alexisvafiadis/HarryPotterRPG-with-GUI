package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.ActiveEffect;
import alexis.isep.harrypotter.Core.Magic.EffectType;
import alexis.isep.harrypotter.Core.Magic.SimpleSpell;
import javafx.scene.paint.Color;

public class Tarantallegra extends SimpleSpell {
    private final int EFFECT_DURATION = 4;

    public Tarantallegra(Game game, Character wizard) {
        super(game, wizard, "Tarantallegra", Color.INDIGO,5, 1, 0.45, 0.26);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            showSuccessfulCast(null);
            target.giveEffect(EffectType.DANCING, new ActiveEffect(EFFECT_DURATION, 0.64));
        }
    }

    @Override
    public void displayInstructions() {
        inputParser.waitForConfirmation("To cast Tarantallegra, first focus your mind on the target and imagine their feet moving uncontrollably in a dance.\n" +
                "While concentrating on your target, hold your wand firmly and perform a swift, upward flicking motion.\n" +
                "As you flick your wand, confidently and clearly pronounce the incantation Tarantallegra.\n" +
                "If the mental image in your head is strong enough, your target should start dancing uncontrollably.");
    }
}
