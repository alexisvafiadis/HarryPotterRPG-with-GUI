package alexis.isep.harrypotter.Core.Characters.Enemies;

import alexis.isep.harrypotter.Core.Characters.EnemyWizard;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.Spells.*;

public class HarryPotter extends EnemyWizard {
    public HarryPotter(Game game) {
        super(game, 180, 5, 0.53, 'V', 1, 1.9, 4.2);
        addSpell(new Sectumsempra(game, this), 6);
        addSpell(new Stupefy(game, this), 1);
        addSpell(new Expelliarmus(game, this), 2);
        addSpell(new Protego(game, this), 2);
        addSpell(new Confundus(game, this), 1);
        setCustomBattleStartMessage("I'll never back down, no matter the odds. I will fight for what's right and stop you.");
    }

    @Override
    public String getName() {
        return "Harry Potter";
    }

}
