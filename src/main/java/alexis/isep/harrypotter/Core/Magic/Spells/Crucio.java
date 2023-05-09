package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.ActiveEffect;
import alexis.isep.harrypotter.Core.Magic.EffectType;
import alexis.isep.harrypotter.Core.Magic.SimpleSpell;

public class Crucio extends SimpleSpell {
    private final double DAMAGE_AT_THE_END = 40;
    private final int EFFECT_DURATION = 3;

    public Crucio(Game game, Character wizard) {
        super(game, wizard, "Crucio", 5, 1, 0.22, 0.19);
        setForbidden(true);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            showSuccessfulCast("a burst of bright blue light emerges, striking " + target.getName());
            target.giveEffect(EffectType.EXCRUCIATING_PAIN, new ActiveEffect(EFFECT_DURATION, calculateDamage(DAMAGE_AT_THE_END)));
        }
    }

    @Override
    public void displayInstructions() {

    }
}
