package alexis.isep.harrypotter.Core.Characters.Enemies;

import alexis.isep.harrypotter.Core.Characters.Boss;
import alexis.isep.harrypotter.Core.Game.Game;

public class Basilisk extends Boss {

    public Basilisk(Game game) {
        super(game, 90, 28, 1,null,'B',1,1);
        setCustomBattleStartMessage("Hiss... Sssss...");
    }

    public void act() {
        attack(getGame().getPlayer());
    }

    public String getName() {
        return "The Basilisk";
    }
}
