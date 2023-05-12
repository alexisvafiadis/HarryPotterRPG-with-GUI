package alexis.isep.harrypotter.Core.Magic.Spells;

import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.Core.Levels.Level4;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Items.Item;
import alexis.isep.harrypotter.Core.Items.Weapon;
import alexis.isep.harrypotter.Core.Magic.Spell;
import javafx.scene.paint.Color;

public class Accio extends Spell {

    public Accio(Game game, Wizard wizard) {
        super(game, wizard, "Accio", Color.BLUE,5, 1, 0.7, 0.34);
    }

    public void cast(Weapon weapon) {
        use();
        if (isCastSuccessful()) {
            wizard.setWeapon(weapon);
            showSuccessfulCast("summoned a " + weapon.toString());
        }
    }

    public boolean cast(Item item) {
        use();
        if (!(Math.random() < 1 / (Math.pow(wizard.getMap().calculateDistance(wizard,item),4)))) {
            display.announceFail("Accio failed. You might be too far.");
            wizard.decideWhichRoundAction();
            return false;
        }
        boolean castSuccessful = isCastSuccessful();
        if (castSuccessful) {
            showSuccessfulCast("summoned a " + item.getItemType().toString());
        }
        return castSuccessful;
    }

    @Override
    public void displayInstructions() {
        inputParser.waitForConfirmation("First, have your wand at the ready. Then, focus your attention on the object you want to summon. Visualize the object in your mind and concentrate on it.\n" +
                "Finally, hold out your wand and say \"Accio\" followed by the name of the object you want to summon.\n");
        display.displayInfo("If the spell is successful, the object should fly towards you. Remember, the Accio spell only works on objects within your line of sight.");
    }
}
