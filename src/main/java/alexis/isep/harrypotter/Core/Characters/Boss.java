package alexis.isep.harrypotter.Core.Characters;

import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Items.Weapon;

public abstract class Boss extends AbstractEnemy {

    public Boss(Game game, double maxHP, double physicalDamage, double vulnerabilityToMagic, Weapon weapon, char charTile, int moveStep, int attackDelay) {
        super(game, maxHP, physicalDamage, vulnerabilityToMagic, weapon, charTile, moveStep, attackDelay);
    }

}
