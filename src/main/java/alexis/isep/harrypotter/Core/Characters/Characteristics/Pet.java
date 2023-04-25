package alexis.isep.harrypotter.Core.Characters.Characteristics;

public enum Pet {
    OWL, RAT, CAT, TOAD;
    public String toString() {
        return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
    }

    }
