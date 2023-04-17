package alexis.isep.harrypotter.Core.Characters;

import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Items.Weapon;

public abstract class AbstractEnemy extends Character {
    String customBattleStartMessage = "";
    int attackDelay;

    public AbstractEnemy(Game game, double maxHP, double physicalDamage, double vulnerabilityToMagic, Weapon weapon, char charTile, int moveStep, int attackDelay) {
        super(game, maxHP, physicalDamage, vulnerabilityToMagic, weapon, charTile, moveStep);
        this.attackDelay = attackDelay;
    }

    public abstract void act();

    public int getAttackDelay() {
        return attackDelay;
    }

    public String getCustomBattleStartMessage() {
        return customBattleStartMessage;
    }

    public void setCustomBattleStartMessage(String customBattleStartMessage) {
        this.customBattleStartMessage = customBattleStartMessage;
    }
}
