package alexis.isep.harrypotter.Core.Items;

public enum WandCore {
    PHOENIX_FEATHER(1.3, 0.9),
    DRAGON_HEARTSTRING(1.4, 0.8),
    UNICORN_TAIL_HAIR(0.8, 1.4),
    VEELA_HAIR(1.2, 1.0),
    THESTRAL_TAIL_HAIR(1.1, 1.1),
    TROLL_WHISKER(1.5, 0.7),
    CORAL(1.0, 1.2),
    DITTANY_STALK(0.9, 1.3),
    ROUGAROU_HAIR(1.25, 0.85),
    HORNED_SERPENT_HORN(1.3, 0.9),
    SNALLYGASTER_HEARTSTRING(1.35, 0.85),
    JACKALOPE_ANTLER(0.95, 1.15),
    KNEAZLE_WHISKER(0.9, 1.2),
    KELPIE_HAIR(1.15, 0.95),
    BASILISK_HORN(1.5, 0.7),
    AFRICAN_MERMAID_HAIR(1.1, 1.1),
    FAIRY_WING(0.85, 1.25);

    private final double SPELL_DAMAGE_MULTIPLIER;
    private final double ACCURACY_MULTIPLIER;

    WandCore(double SPELL_DAMAGE_MULTIPLIER, double ACCURACY_MULTIPLIER) {
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
        return name().toLowerCase().replace("_", " ");
    }

}
