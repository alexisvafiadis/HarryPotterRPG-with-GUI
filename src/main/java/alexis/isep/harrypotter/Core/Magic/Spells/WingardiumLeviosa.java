package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Game.Game;
import alexis.isep.harrypotter.Core.Items.Item;

public class WingardiumLeviosa extends ItemSpell {
    private final double DEFAULT_DAMAGE = 15;

    public WingardiumLeviosa(Game game, Wizard wizard) {
        super(game, wizard, "Wingardium Leviosa", 5, 1, 0.75, 0.36,"to levitate");
    }

    public boolean cast(Item item, Character target) {
        if (nbOfUses < 1) {
            tryForFirstTime();
        }
        use();
        if (isCastSuccessful(target)) {
            displayCastMessage("rose " + item.getItemType().toString() + " into the air, throwing it on " + target.getName() +"'s head");
            target.damage(calculateDamage(DEFAULT_DAMAGE * item.getItemType().getDamageMultiplier()));
            return true;
        }
        return false;
    }

    public void tryForFirstTime() {
        inputParser.waitForYes("Aim your wand at the object you want to levitate and concentrate on it. Visualize the object lifting up off the ground and floating in the air.\n" +
                "As you concentrate on the object, say the incantation Wingardium Leviosa and make the wand movement. If you did it correctly, the object should start to lift off the ground.\n" +
                "Once the object is floating in the air, you can move it around by pointing your wand in different directions. Try moving the object to the left, right, up, and down by changing the direction of your wand.\n" +
                "To release the object, simply stop concentrating on it and let it fall back to the ground.\n"
                + "Understood?");;
    }

    public void displayInstructions() {
        display.displayInfo("To cast the Wingardium Leviosa spell, hold your wand out in front of you and say the incantation clearly: Wingardium Leviosa.");
        display.displayInfo("Next, you need to make the wand movement for the spell. With your wand in your hand, make a swishing motion upward, as if you are lifting something up into the air.");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        display.displayInfo("Good. Now that you have the spell incantation and wand movement down, you need to find an object to levitate. Look around the room and find an object that you want to move with the spell.");
    }
}