package alexis.isep.harrypotter.Core.Magic;

public enum PotionType {
    STRENGHTENING_SOLUTION(EffectType.STRENGTH, 2, 4, 1,"This potion is used to strengthen and fortify objects or people. Useful to increase your physical strength in battle."),
    WIGGENWELD_POTION(EffectType.HEAL, 50, 0, 2," This healing potion can be used to heal minor injuries. Useful for survival in battle."),
    FELIX_FELICIS_POTION(EffectType.LUCK, 1.5,3,1,"This unique potion grants the drinker temporary good luck. Useful to gain an advantage over opponents in a combat situation."),
    ESSENCE_OF_DITTANY(EffectType.HEAL, 75,0,1,"This powerful healing potion can help to quickly heal wounds and burns and cure poisons. Useful for survival in battle."),
    PEPPER_UP_POTION(EffectType.VIVACITY, 1.25,5,1,"This potion cures fatigue and increases alertness. Useful to stay sharp in a battle situation."),
    ENDURUS_POTION(EffectType.RESISTANCE, 1.4,4,1,"This potion enhances defence by covering the drinker with a durable rocky skin. Useful at the start of a battle."),

    //PERUVIAN_INSTANT_DARKNESS_POWDER ; creates a cloud of darkness, useful to escape, hide or surprise
    //SWELLING_SOLUTION : rapid swelling , reduce movement
    //AMORTENTIA : love potion
    //POLYJUICE : appearance of somebody else
    ;

    private EffectType effect;
    private int duration;
    private double effectValue;
    private String description;
    private double weight;

    PotionType(EffectType effect, double effectValue, int duration, double weight, String description) {
        this.description = description;
        this.effect = effect;
        this.effectValue = effectValue;
        this.duration = duration;
        this.weight = weight;
    }

    @Override
    public String toString() { return name().toLowerCase().replace("_", " "); }

    public EffectType getEffect() {
        return effect;
    }

    public int getDuration() {
        return duration;
    }

    public double getEffectValue() {
        return effectValue;
    }

    public String getDescription() {
        return description;
    }

    public double getWeight() {
        return weight;
    }
}
