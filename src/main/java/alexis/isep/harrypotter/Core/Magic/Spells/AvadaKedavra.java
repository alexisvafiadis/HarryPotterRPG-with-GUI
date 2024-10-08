package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.SimpleSpell;
import javafx.scene.paint.Color;

public class AvadaKedavra extends SimpleSpell {
    private final double DEFAULT_DAMAGE = 200;

    public AvadaKedavra(Game game, Character wizard) {
        super(game, wizard, "Avada Kedavra", Color.GREEN, 5, 1, 0.2, 0.09);
        setForbidden(true);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            target.damage(calculateDamage(DEFAULT_DAMAGE));
            showSuccessfulCast("emitted an incredibly powerful flash of light, striking " + target.getName());
        }
    }

    @Override
    public void displayInstructions() {

    }
}
