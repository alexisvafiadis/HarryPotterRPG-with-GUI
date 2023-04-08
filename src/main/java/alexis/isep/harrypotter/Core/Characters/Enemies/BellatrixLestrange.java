package alexis.isep.harrypotter.Core.Characters.Enemies;

import alexis.isep.harrypotter.Core.Characters.EnemyWizard;
import alexis.isep.harrypotter.Core.Game.Game;
import alexis.isep.harrypotter.Core.Magic.Spells.AvadaKedavra;
import alexis.isep.harrypotter.Core.Magic.Spells.Crucio;
import alexis.isep.harrypotter.Core.Magic.Spells.Protego;
import alexis.isep.harrypotter.Core.Magic.Spells.Sectumsempra;

public class BellatrixLestrange extends EnemyWizard {
    public BellatrixLestrange(Game game) {
        super(game, 200, 5, 0.7, 'V', 1, 2, 4.4);
        addSpell(new AvadaKedavra(game, this), 5);
        addSpell(new Sectumsempra(game, this), 4);
        addSpell(new Protego(game, this), 1);
        addSpell(new Crucio(game, this), 2);
        setCustomBattleStartMessage("I've been waiting for this moment for a long time");
    }

    @Override
    public String getName() {
        return "Bellatrix Lestrange";
    }

}
