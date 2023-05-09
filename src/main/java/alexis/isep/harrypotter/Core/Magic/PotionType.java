package alexis.isep.harrypotter.Core.Magic;

import javafx.scene.image.Image;

public enum PotionType {
    STRENGTHENING_SOLUTION(EffectType.STRENGTH, 2, 4, 1,"This potion is used to strengthen and fortify objects or people. Useful to increase your physical strength in battle.","StrengtheningSolution1.png"),
    WIGGENWELD_POTION(EffectType.HEAL, 50, 0, 2," This healing potion can be used to heal minor injuries. Useful for survival in battle.","WiggenweldPotion1.png"),
    FELIX_FELICIS_POTION(EffectType.LUCK, 1.5,3,1,"This unique potion grants the drinker temporary good luck. Useful to gain an advantage over opponents in a combat situation.","FelixFelicisPotion2.png"),
    ESSENCE_OF_DITTANY(EffectType.HEAL, 75,0,1,"This powerful healing potion can help to quickly heal wounds and burns and cure poisons. Useful for survival in battle.","EssenceOfDittany1.png"),
    PEPPER_UP_POTION(EffectType.VIVACITY, 1.25,5,1,"This potion cures fatigue and increases alertness. Useful to stay sharp in a battle situation.","PepperUpPotion3.png"),
    ENDURUS_POTION(EffectType.RESISTANCE, 1.4,4,1,"This potion enhances defence by covering the drinker with a durable rocky skin. Useful at the start of a battle.","EndurusPotion2.png"),

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

    private String path;

    PotionType(EffectType effect, double effectValue, int duration, double weight, String description, String path) {
        this.description = description;
        this.effect = effect;
        this.effectValue = effectValue;
        this.duration = duration;
        this.weight = weight;
        this.path = path;
    }

    @Override
    public String toString() {
        String[] words = name().split("_");
        StringBuilder output = new StringBuilder();
        for (String word : words) {
            output.append(word.charAt(0)).append(word.substring(1).toLowerCase()).append(" ");
        }
        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }

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

    public String getPath() {
        return path;
    }

    public Image getImage() {
        return new Image(getClass().getResource("/alexis/isep/harrypotter/images/items/" + name() + ".png").toString(), true);
    }
}
