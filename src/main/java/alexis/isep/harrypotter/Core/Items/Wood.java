package alexis.isep.harrypotter.Core.Items;

public enum Wood {

    ACACIA(1.1, 0.95),
    ALDER(1.05, 1.0),
    APPLE(0.9, 1.1),
    ASH(1.2, 0.9),
    ASPEN(1.0, 1.05),
    BEECH(0.95, 1.15),
    BLACKTHORN(1.25, 0.85),
    BLACK_WALNUT(1.15, 0.95),
    CEDAR(0.85, 1.2),
    CHERRY(1.05, 1.0),
    CHESTNUT(0.9, 1.1),
    CYPRESS(1.1, 0.95),
    DOGWOOD(1.0, 1.05),
    EBONY(1.3, 0.8),
    ENGLISH_OAK(1.1, 1.0),
    ELDER(1.2, 0.9),
    ELM(0.95, 1.1),
    FIR(1.0, 1.05),
    HAWTHORN(1.15, 0.95),
    HAZEL(0.9, 1.1),
    HOLLY(1.1, 1.0),
    HORNBEAM(1.2, 0.9),
    LARCH(1.0, 1.05),
    LAUREL(1.05, 1.0),
    MAPLE(0.95, 1.15),
    PEAR(0.9, 1.1),
    PINE(1.1, 0.95),
    POPLAR(1.0, 1.05),
    RED_OAK(1.15, 0.9),
    REDWOOD(1.1, 1.0),
    REED(0.85, 1.2),
    ROSEWOOD(1.25, 0.85),
    ROWAN(1.05, 1.0),
    SILVER_LIME(0.9, 1.1),
    SPRUCE(1.0, 1.05),
    SNAKEWOOD(1.3, 0.8),
    SUGAR_MAPLE(0.95, 1.15),
    SYCAMORE(1.1, 1.0),
    TAMARACK(1.0, 1.05),
    VINE(1.15, 0.95),
    WALNUT(1.1, 1.0),
    WILLOW(0.9, 1.1),
    YEW(1.25, 0.85);

    private final double SPELL_DAMAGE_MULTIPLIER;
    private final double ACCURACY_MULTIPLIER;

    Wood(double SPELL_DAMAGE_MULTIPLIER, double ACCURACY_MULTIPLIER) {
        this.SPELL_DAMAGE_MULTIPLIER = SPELL_DAMAGE_MULTIPLIER;
        this.ACCURACY_MULTIPLIER = ACCURACY_MULTIPLIER;
    }

    public double getSPELL_DAMAGE_MULTIPLIER() {
        return SPELL_DAMAGE_MULTIPLIER;
    }

    public double getACCURACY_MULTIPLIER() {
        return ACCURACY_MULTIPLIER;
    }

    @Override
    public String toString() {
        return name().toLowerCase().replace("_", " ") + " wood";
    }
}
