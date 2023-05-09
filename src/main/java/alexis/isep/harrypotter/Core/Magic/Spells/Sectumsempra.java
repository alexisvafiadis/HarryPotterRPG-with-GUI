package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.SimpleSpell;

public class Sectumsempra extends SimpleSpell {
    private final double DEFAULT_DAMAGE = 40;

    public Sectumsempra(Game game, Character wizard) {
        super(game, wizard, "Sectumsempra", 5, 1, 0.26, 0.5);
        setForbidden(true);
    }

    public void cast(Character target) {
        use();
        if (isCastSuccessful(target)) {
            target.damage(calculateDamage(DEFAULT_DAMAGE));
            showSuccessfulCast("lacerated " + target.getName() + ", causing severe haemorrhaging");
        }
    }

    public void displayInstructions() {
        inputParser.waitForConfirmation("To use Sectumsempra, you must be confident and precise.\n" +
                "Aim your wand at the opponent you want to harm, and say the incantation while making a strong, quick slashing motion with your wand.\n" +
                "A bright red light will shoot from your wand and strike the target, causing deep and often bloody cuts.\n" +
                "Understood?");
    }
}
