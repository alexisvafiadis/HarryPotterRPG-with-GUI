package alexis.isep.harrypotter.Core.Magic;

import alexis.isep.harrypotter.GUI.Display;
import alexis.isep.harrypotter.Console.InputParser;
import alexis.isep.harrypotter.Core.Characters.Character;
import alexis.isep.harrypotter.Core.Characters.EnemyWizard;
import alexis.isep.harrypotter.Core.Characters.Wizard;
import alexis.isep.harrypotter.GUI.Game;
import javafx.scene.paint.Color;

public abstract class Spell {
    //Global
    protected Game game;
    protected Display display;
    protected InputParser inputParser;

    //Spell-specific
    protected String name;
    private Color color;

    protected int range;
    protected float cooldown;
    private double learningExponent;
    private double defaultMasteryScore;
    private boolean isForbidden;

    //Instance-specific
    protected int nbOfUses;
    protected Character wizard;// character and not wizard because there is Wizard and EnemyWizard

    //CONST
    final private String CAST_MESSAGE_SUCCESS = "successfully cast";
    final private String CAST_MESSAGE_FAIL = "failed to cast";

    public Spell(Game game, Character wizard, String name, Color color, int range, float cooldown, double learningExponent, double defaultMasteryScore) {
        this.game = game;
        this.wizard = wizard;
        this.display = game.getDisplay();
        this.inputParser = game.getInputParser();
        this.name = name;
        this.color = color;
        //Range and cooldowns aren't used yet, but could be useful if the game becomes a bit more complex when making the graphic interface
        this.range = range;
        this.cooldown = cooldown;

        this.learningExponent = learningExponent;
        this.defaultMasteryScore = defaultMasteryScore;
        nbOfUses = 0;
    }

    //Function that returns a number between 0 and 1 signifying how much the owner has mastered the spell
    //I will then use it as an accuracy (a probability of successfully casting the spell)
    public double getMasteryScore() {
        if (nbOfUses < 2) {
            return defaultMasteryScore;
        }
        else {
            //Mathematical function that increases slower and slower with a maximum of 1 to imitate a learning curve
            return 1 - Math.exp(-1 * (Math.pow(nbOfUses, learningExponent)) / 2);
        }
    }

    //Calculates if the wizard successfully casts a default spell, depending on different factors
    //Doesn't display if the cast is successful because some spells may need extra requirements for a spell to be successful
    public boolean isCastSuccessful(Character target) {
        if (target != null && !wizard.canAttack(target)) {
            wizard.decideWhichRoundAction();
            return false;
        }
        if (wizard.hasEffect(EffectType.DISARM)) {
            displayCustomFailMessage(getName() + " failed to cast " + name + " because they are disarmed.");
            wizard.decideWhichRoundAction();
            return false;
        }
        double probability;
        boolean fromPlayer = (wizard instanceof Wizard);
        if (fromPlayer) {
            probability = getMasteryScore() * ((Wizard) wizard).getAccuracy();
        }
        else if (wizard instanceof EnemyWizard){
            probability = getDefaultMasteryScore() * ((EnemyWizard) wizard).getDefaultMasteryScoreMultiplier();
        }
        else {
            display.displayError("A non wizard character tried to cast a spell");
            return false;
        }
        if (wizard.hasEffect(EffectType.LUCK)) {
            probability = probability * wizard.getActiveEffect(EffectType.LUCK).getValue();
        }
        if (wizard.hasEffect(EffectType.VIVACITY)) {
            probability = probability * wizard.getActiveEffect(EffectType.VIVACITY).getValue();
        }
        if (target != null) {
            if (target.hasEffect(EffectType.LUCK)) {
                probability = probability / target.getActiveEffect(EffectType.LUCK).getValue();
            }
            if (target.hasEffect(EffectType.VIVACITY)) {
                probability = probability / target.getActiveEffect(EffectType.VIVACITY).getValue();
            }
            probability = probability * target.getVulnerabilityToMagic();
        }
        boolean castSuccessful = (Math.random() < probability) ;
        if (!castSuccessful) {
            displayCustomFailMessage(CAST_MESSAGE_FAIL + " " + getName());
            wizard.decideWhichRoundAction();
        }
        return castSuccessful;
    }

    public void displayCustomFailMessage(String message) {
        if (wizard instanceof Wizard) {
            display.announceFail(game.getMessageStartHave(wizard) + " " + message);
        }
        else {
            display.displayInfo(game.getMessageStartHave(wizard) + " " + message);
        }
    }

    public boolean isCastSuccessful() {
        return isCastSuccessful(null);
    }

    public void showSuccessfulCast(String specificCastMessage) {
        String castMessage = game.getMessageStartHave(wizard) + " " + CAST_MESSAGE_SUCCESS + " " + getName();
        if (specificCastMessage != null) {
            castMessage = castMessage + " and " + specificCastMessage;
        }
        final boolean fromPlayer = (wizard instanceof Wizard);
        if (fromPlayer) {
            display.announceSuccess(castMessage);
        }
        else {
            display.displayInfo(castMessage);
        }
        if (wizard.isInBattle()) {
            wizard.getBattle().getBattleController().playSpellAnimation(this, (e) -> {
                wizard.finishRoundAction(fromPlayer);
            }, fromPlayer);
        }
    }

    public void teach(Wizard player) {
        if (!player.knowsSpell(getName())) {
            displayInstructions();
            if (isForbidden()) {
                display.displayInfo("Be careful not to use this spell on innocent targets, as it is dangerous. You may get expelled if you do.");
            }
            player.learnSpell(this);
            display.announceReward("You have learned the spell " + getName() + "!");
        }
    }

    public abstract void displayInstructions();

    //Call that function when the player uses the spell so that they can get better at using it
    public void use() { nbOfUses += 1; };

    public String getName() {
        return name;
    }

    public int getRange() {
        return range;
    }

    public float getCooldown() {
        return cooldown;
    }

    public Character getWizard() {
        return wizard;
    }

    public int getNbOfUses() {
        return nbOfUses;
    }

    public void setNbOfUses(int nbOfUses) {
        this.nbOfUses = nbOfUses;
    }

    public double getDefaultMasteryScore() {
        return defaultMasteryScore;
    }

    public Game getGame() {
        return game;
    }

    public Display getDisplay() {
        return display;
    }

    public InputParser getInputParser() {
        return inputParser;
    }

    public double calculateDamage(double damage) {
        //character target defend
        if (wizard instanceof Wizard) {
            return ((Wizard) wizard).amplifySpellDamage(damage);
        }
        else {
            return ((EnemyWizard) wizard).amplifySpellDamage(damage);
        }
    }

    public boolean isForbidden() {
        return isForbidden;
    }

    public void setForbidden(boolean forbidden) {
        isForbidden = forbidden;
    }

    public Color getColor() {
        return color;
    }
}