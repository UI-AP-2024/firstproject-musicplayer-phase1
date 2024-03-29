package model;
public enum PremiumPackages {
    FIRST(5, 30), SECOND(9, 60), THIRD(14, 180);

    private final int cash;
    private final int days;


    PremiumPackages(int cash, int days) {
        this.cash = cash;
        this.days = days;
    }

    public int getDays() {
        return this.days;
    }

    public int getCash() {
        return this.cash;
    }
}