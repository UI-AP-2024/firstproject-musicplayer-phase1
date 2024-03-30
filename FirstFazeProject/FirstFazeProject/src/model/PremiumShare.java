package model;

public enum PremiumShare {
    ThirtyDay(5),SixtyDay(9),OneEightyDay(14);

    private int value ;
    PremiumShare(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
