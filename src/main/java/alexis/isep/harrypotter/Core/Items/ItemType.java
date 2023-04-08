package alexis.isep.harrypotter.Core.Items;

public enum ItemType {
    //INSIDE
    GLASS(0, 1.6, 2),
    BOTTLE(0, 2, 2),
    BOOK(0, 0.4, 4),
    KNIFE(0, 2.75, 1),
    FORK(0, 2.25, 1),
    VASE(0, 1.8, 4),
    PAINTING(0, 0.75, 3),
    LAMP(0, 1.5, 2),
    TABLE(0, 1.5, 2),
    CHAIR(0, 1.3, 3),

    //OUTSIDE
    SMALL_STICK(1, 0.9, 2),
    MEDIUM_STICK(1, 1.3, 2),
    BIG_STICK(1, 1.7, 3),
    SMALL_ROCK(1, 1.7, 1),
    MEDIUM_ROCK(1, 2.4, 2),
    BIG_ROCK(1,3.5, 3),
    NETTLE(1,1.25, 4),

    //SPECIFIC SITUATIONS (the higher the weight, the harder it is to summon)
    PORTKEY(2,0,5),
    ;

    private int where;
    private int weight;
    private double damageMultiplier;

    ItemType(int where, double damageMultiplier, int weight) {
        this.where = where;
        this.weight = weight;
        this.damageMultiplier = damageMultiplier;
    }

    public int getWhere() {
        return where;
    }

    public int getWeight() {
        return weight;
    }

    public double getDamageMultiplier() {
        return damageMultiplier;
    }
    public void enlarge(double coefficient) { damageMultiplier = damageMultiplier * coefficient; }

    @Override
    public String toString() {
        return name().toLowerCase().replace("_", " ");
    }
}
