package alexis.isep.harrypotter.Core.Characters.Enemies;

import alexis.isep.harrypotter.Core.Characters.Boss;
import alexis.isep.harrypotter.Core.Game.Game;
import alexis.isep.harrypotter.Core.Items.Item;
import alexis.isep.harrypotter.Core.Items.Weapon;

public class Troll extends Boss {
    public Troll(Game game) {
        super(game, 110, 16, 1, Weapon.CLUB,'T',1,2);
        setCustomBattleStartMessage("Grrrrr... Stomp! Stomp!");
    }

    public void act() {
        attack(game.getPlayer());
    }

    public boolean getsHitBy(Item item) {
        return (item.getPositionX() == positionX && item.getPositionY() == positionY);
    }

    public String getName() {
        return "The Troll";
    }
}
