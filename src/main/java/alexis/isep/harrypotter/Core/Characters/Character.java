package alexis.isep.harrypotter.Core.Characters;

import alexis.isep.harrypotter.Core.Levels.Essentials.Battle;
import alexis.isep.harrypotter.Core.Levels.Level4;
import alexis.isep.harrypotter.GUI.Display;
import alexis.isep.harrypotter.Console.InputParser;
import alexis.isep.harrypotter.GUI.Game;
import alexis.isep.harrypotter.Core.Items.Weapon;
import alexis.isep.harrypotter.Core.Levels.Essentials.LevelMap;
import alexis.isep.harrypotter.Core.Magic.ActiveEffect;
import alexis.isep.harrypotter.Core.Magic.EffectCategory;
import alexis.isep.harrypotter.Core.Magic.EffectType;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class Character {
    //Global attributes
    protected Game game;
    protected Display display;
    protected InputParser inputParser;
    protected Battle battle;
    protected LevelMap map;

    //Specific attributes (need to set!)
    protected double maxHP;
    protected double physicalDamage;
    protected double vulnerabilityToMagic;//A coefficient that represents how likely a wizard can successfully cast a spell on the person
    protected Weapon weapon;
    protected Weapon currentWeapon;
    protected char charTile;
    protected Integer moveStep;

    //Live attributes
    protected Integer positionX;
    protected Integer positionY;
    protected double HP;
    protected boolean alive;
    protected Map<EffectType, ActiveEffect> activeEffects;

    public Character(Game game, double maxHP, double physicalDamage, double vulnerabilityToMagic, Weapon weapon, char charTile, int moveStep) {
        this.game = game;
        this.display = game.getDisplay();
        this.inputParser = game.getInputParser();
        this.maxHP = maxHP;
        this.physicalDamage = physicalDamage;
        this.vulnerabilityToMagic = vulnerabilityToMagic;
        this.weapon = weapon;
        this.charTile = charTile;
        this.moveStep = moveStep;
        this.battle = null;
    }

    public void spawn(int positionX, int positionY, LevelMap map) {
        spawn();
        this.map = map;
        moveTo(positionX, positionY);
    }

    public void spawn() {
        HP = maxHP;
        alive = true;
        activeEffects = new HashMap<>();
        currentWeapon = weapon;
    }

    public void attack(Character target) {
        final boolean fromPlayer = (this instanceof Wizard);
        if (canAttack(target)) {
            double damage = getPhysicalDamage();
            if (hasWeapon() && !hasEffect(EffectType.DISARM)) {
                damage += weapon.getAttackDamage();
            }
            final String information;
            if (fromPlayer) {
                damage = ((Wizard) this).amplifyDamage(damage);
                information = "You have damaged " + target.getName();
            }
            else {
                damage = ((Wizard) target).defendDamage(damage);
                information = "You have been attacked by " + getName();
            }
            target.damage(damage);
            battle.getBattleController().playAttackAnimation((e) -> {
                display.displayInfo(information);
                finishRoundAction(fromPlayer);
            }, fromPlayer
            );
        }
        else {
            finishRoundAction(fromPlayer);
        }
    }
    public void finishRoundAction(boolean isPlayer) {
        battle.handleCharacterAction(this,isPlayer);
    }
    public void finishRoundAction() {
        battle.handleCharacterAction(this,(this instanceof Wizard));
    }

    public void decideWhichRoundAction() {
        if (isInBattle()) {
            finishRoundAction();
        }
        else {
            if (game.getCurrentLevel() instanceof Level4) {
                ((Level4) game.getCurrentLevel()).finishRound();
            }
        }
    }

    public void die() {
        alive = false;
    }

    public void damage(double damage) {
        if (HP <= damage) { die(); HP = 0;}
        else { HP = HP - damage; }
    }

    public void speak(String quote) {
        display.displayQuote(this, quote);
    }

    public double getHP() {
        return HP;
    }

    public double getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(double maxHP) {
        this.maxHP = maxHP;
    }

    public double getPhysicalDamage() {
        return physicalDamage;
    }

    public void setPhysicalDamage(double physicalDamage) {
        this.physicalDamage = physicalDamage;
    }

    public double getVulnerabilityToMagic() {
        return vulnerabilityToMagic;
    }

    public void setVulnerabilityToMagic(double vulnerabilityToMagic) {
        this.vulnerabilityToMagic = vulnerabilityToMagic;
    }

    public void heal(double hp_restore) {
        if ((maxHP - HP) < hp_restore) {
            HP = maxHP;
        }
        else {
            HP = HP + hp_restore;
        }
        if (isInBattle()) {
            battle.getBattleController().playUpdateHPAnimation(this instanceof Wizard);
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public abstract String getName();
    public Image getImage() {
        return new Image(getClass().getResource("/alexis/isep/harrypotter/images/characters/" + getName() + ".png").toString(), true);
    }

    public boolean hasEffect(EffectType et) {return (activeEffects.containsKey(et));}

    public void giveEffect(EffectType effectType, ActiveEffect activeEffect) {
        if (hasEffect(effectType)) {
            display.displayInfo(game.getMessageStartBe(this) + " " +  effectType.getAlreadyAffectedMessage() +  ", so this was uneffective");
        }
        else {
            display.displayInfo(game.getMessageStartBe((this)) + " " + effectType.getStartMessage());
            activeEffects.put(effectType, activeEffect);
            if (effectType.equals(EffectType.DISARM)) {
                currentWeapon = null;
            }
        }
    }

    public void removeEffect(EffectType et) {
        actionBeforeEffectRemoval(et);
        activeEffects.remove(et);
    }

    public void actionBeforeEffectRemoval(EffectType et) {
        display.displayInfo(game.getMessageStartBe(this) + " " + et.getEndMessage());
        if (et.equals(EffectType.DISARM) && weapon != null) { currentWeapon = weapon; }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        this.currentWeapon = weapon;
    }

    public boolean hasWeapon() { return (currentWeapon != null); }

    public boolean canDoSomething() {
        for (EffectType effectType : activeEffects.keySet()) {
            if (effectType.getEffectCategory().equals(EffectCategory.INABILITY) && (getEffectProbability(effectType))) {
                display.displayInfo(getName() + " couldn't do anything because they are " + effectType.getConsequenceMessage());
                return false;
            }
        }
        return true;
    }

    public boolean canAttack(Character target) {
        if (canDoSomething()) {
            for (EffectType effectType : target.getActiveEffects().keySet()) {
                if (effectType.getEffectCategory().equals(EffectCategory.PROTECTION) && (getEffectProbability(effectType))) {
                        display.displayInfo(getName() + " couldn't attack " + target.getName() + " because they are " + effectType.getConsequenceMessage());
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    public boolean getEffectProbability(EffectType et) {
        return (activeEffects.containsKey(et) && Math.random() < activeEffects.get(et).getValue());
    }

    public void finishRound() {
        if (hasEffect(EffectType.EXCRUCIATING_PAIN)) {
            double damage = activeEffects.get(EffectType.EXCRUCIATING_PAIN).getValue() / activeEffects.get(EffectType.EXCRUCIATING_PAIN).getNbOfRoundsLeft();
            display.displayInfo(getName() + " " + EffectType.EXCRUCIATING_PAIN.getConsequenceMessage());
            damage(damage);
        }
        if (hasEffect(EffectType.BURN)) {
            double damage = activeEffects.get(EffectType.BURN).getValue();
            display.displayInfo(getName() + " " + EffectType.BURN.getConsequenceMessage());
            damage(damage);
        }
        Iterator<Map.Entry<EffectType, ActiveEffect>> it = activeEffects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<EffectType, ActiveEffect> entry = it.next();
            if (entry.getValue().getNbOfRoundsLeft() == 0) {
                actionBeforeEffectRemoval(entry.getKey());
                it.remove();
            }
        }
        for (EffectType effectType : activeEffects.keySet()) {
            activeEffects.get(effectType).reduceNbOfRounds();
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
        this.inputParser = game.getInputParser();
        this.display = game.getDisplay();
    }

    public LevelMap getMap() {
        return map;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public boolean moveForwards() { return moveTo(positionX, positionY + moveStep); }

    public boolean moveBackwards() {  return moveTo(positionX, positionY - moveStep); }

    public boolean moveRight() {  return moveTo(positionX - moveStep, positionY); }

    public boolean moveLeft() { return moveTo(positionX + moveStep, positionY); }

    public boolean moveTo(Integer positionX, Integer positionY) {
        if (this.positionX != null && this.positionY != null) {
            map.clearTile(this.positionX, this.positionY);
        }
        boolean isPositionAvailable = map.isPositionAvailable(positionX, positionY);
        if (isPositionAvailable) {
            this.positionX = positionX;
            this.positionY = positionY;
            map.setTile(positionX, positionY, charTile);
        }
        return isPositionAvailable;
    }

    public Map<EffectType, ActiveEffect> getActiveEffects() {
        return activeEffects;
    }

    public ActiveEffect getActiveEffect(EffectType effectType) {
        return activeEffects.get(effectType);
    }

    public void setBattle(Battle battle) { this.battle = battle; }

    public boolean isInBattle() { return (battle != null);}

    public Battle getBattle() {
        return battle;
    }

    public void setHP(double HP) {
        this.HP = HP;
    }
}
