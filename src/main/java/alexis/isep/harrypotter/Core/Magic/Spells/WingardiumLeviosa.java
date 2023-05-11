package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Items.Item;
import javafx.scene.paint.Color;

public class WingardiumLeviosa extends ItemSpell {
    private final double DEFAULT_DAMAGE = 15;

    public WingardiumLeviosa(Game game, Wizard wizard) {
        super(game, wizard, "Wingardium Leviosa", Color.SKYBLUE, 5, 1, 0.75, 0.36,"to levitate");
    }

    public boolean cast(Item item, Character target) {
        use();
        if (isCastSuccessful(target)) {
            showSuccessfulCast("rose " + item.getItemType().toString() + " into the air, throwing it on " + target.getName() +"'s head");
            target.damage(calculateDamage(DEFAULT_DAMAGE * item.getItemType().getDamageMultiplier()));
            return true;
        }
        return false;
    }

    public void displayInstructions() {
        inputParser.waitForConfirmation("To cast this spell, hold your wand out in front of you and say the incantation clearly: Wingardium Leviosa.\n" +
        "Next, with your wand in your hand, make a swishing motion upward, as if you are lifting something up into the air.\n" + "Now that you have the spell incantation and wand movement down, you need to find an object to levitate.");
        display.displayInfo("Once you have found the object and cast the spell, it should be floating in the air. \nYou can move it around by pointing your wand in different directions." +
        "To release the object, simply stop concentrating on it and let it fall back to the ground. Some objects can deal a lot of damage!");
    }
}