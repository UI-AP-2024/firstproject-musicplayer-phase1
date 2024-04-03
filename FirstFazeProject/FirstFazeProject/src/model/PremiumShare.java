package model;

public enum PremiumShare {
    Day30(5,30),Day60(9,60),Day180(14,180);

    private int value ;
    private int days ;
    PremiumShare(int value, int days) {
        this.value = value;
        this.days = days;
    }

    public int getValue() {
        return value;
    }

    public int getDays() {
        return days;
    }
}
