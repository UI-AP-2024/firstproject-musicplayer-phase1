package model;
public enum PremiumPackagesModel {
    FIRST(5, 30), SECOND(9, 60), THIRD(14, 180);

    private final double cash;
    private final int days;


    PremiumPackagesModel(double cash, int days) {
        this.cash = cash;
        this.days = days;
    }

    public int getDays() {
        return this.days;
    }

    public double getCash() {
        return this.cash;
    }
}