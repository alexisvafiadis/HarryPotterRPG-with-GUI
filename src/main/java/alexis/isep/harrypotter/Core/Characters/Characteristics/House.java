package alexis.isep.harrypotter.Core.Characters.Characteristics;

public enum House {
    GRYFFINDOR("bravery",  "GryffindorWizard.png",0.75, 1, 1, 1),
    HUFFLEPUFF("loyalty","HufflepuffWizard.png",1,1,1,1.5),
    RAVENCLAW("intelligence","RavenclawWizard.png",1,1,1.5,1),
    SLYTHERIN("ambition","SlytherinWizard.png",1,1.3,1,1);

    private final String value;
    private final double DAMAGE_VULNERABILITY;
    private final double SPELL_DAMAGE_MULTIPLIER;
    private final double ACCURACY_MULTIPLIER;
    private final double POTION_EFFICIENCY_MULTIPLIER;

    private final String IMAGE_NAME;

    House(String value, String IMAGE_NAME,  double DAMAGE_VULNERABILITY, double SPELL_DAMAGE_MULTIPLIER, double ACCURACY_MULTIPLIER, double POTION_EFFICIENCY_MULTIPLIER) {
        this.value = value;
        this.DAMAGE_VULNERABILITY = DAMAGE_VULNERABILITY;
        this.SPELL_DAMAGE_MULTIPLIER = SPELL_DAMAGE_MULTIPLIER;
        this.ACCURACY_MULTIPLIER = ACCURACY_MULTIPLIER;
        this.POTION_EFFICIENCY_MULTIPLIER = POTION_EFFICIENCY_MULTIPLIER;
        this.IMAGE_NAME = IMAGE_NAME;
    }

    public String toString() {
        return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
    }

    public String getValue() {
        return value;
    }

    public double getDAMAGE_VULNERABILITY() {
        return DAMAGE_VULNERABILITY;
    }

    public double getSPELL_DAMAGE_MULTIPLIER() {
        return SPELL_DAMAGE_MULTIPLIER;
    }

    public double getACCURACY_MULTIPLIER() {
        return ACCURACY_MULTIPLIER;
    }

    public double getPOTION_EFFICIENCY_MULTIPLIER() {
        return POTION_EFFICIENCY_MULTIPLIER;
    }

    public String getIMAGE_NAME() {
        return IMAGE_NAME;
    }

}