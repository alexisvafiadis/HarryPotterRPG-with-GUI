package alexis.isep.harrypotter.Core.Characters.Enemies;

import alexis.isep.harrypotter.Core.Characters.EnemyWizard;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Magic.Spells.*;

public class DeathEater extends EnemyWizard {
    private String name;

    public DeathEater(Game game, String name, double maxHP, double vulnerabilityToMagic, double spellDamageMultiplier, double defaultMasteryScoreMultiplier, String customBattleStartMessage) {
        super(game, maxHP, 8, vulnerabilityToMagic, 'S', 1, spellDamageMultiplier, defaultMasteryScoreMultiplier);
        this.name = name;
        setCustomBattleStartMessage(customBattleStartMessage);
        addSpell(new AvadaKedavra(game, this), 3);
        addSpell(new Sectumsempra(game, this), 4);
        addSpell(new Crucio(game, this), 1);
        addSpell(new Imperius(game, this), 1);
        addSpell(new Stupefy(game, this), 1);
        addSpell(new Expelliarmus(game, this), 1);
    }

    @Override
    public String getName() {
        return name;
    }

}
