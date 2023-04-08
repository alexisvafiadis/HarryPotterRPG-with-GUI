package alexis.isep.harrypotter.Core.Characters.Enemies;

import alexis.isep.harrypotter.Core.Characters.EnemyWizard;
import alexis.isep.harrypotter.Core.Game.Game;
import alexis.isep.harrypotter.Core.Magic.Spells.Expelliarmus;
import alexis.isep.harrypotter.Core.Magic.Spells.Sectumsempra;
import alexis.isep.harrypotter.Core.Magic.Spells.SlugulusErecto;
import alexis.isep.harrypotter.Core.Magic.Spells.Stupefy;

public class RonWeasley extends EnemyWizard {
    public RonWeasley(Game game) {
        super(game, 250, 5, 0.82, 'V', 1, 1.6, 3.6);
        addSpell(new Sectumsempra(game, this), 5);
        addSpell(new Stupefy(game, this), 2);
        addSpell(new Expelliarmus(game, this), 1);
        addSpell(new SlugulusErecto(game, this), 2);
        setCustomBattleStartMessage("Don't underestimate me just because I'm not the most talented wizard. I will fight until I cannot move a muscle.");
    }

    @Override
    public String getName() {
        return "Ron Weasley";
    }

}
