package model;
public enum PremiumPackages {
    THERTEENDAYS(5), SIXTEENDAYS(9), ONEHUNDREDANDEIGHTEENDAYS(14);

    private final int cash;

    PremiumPackages(int cash) {
        this.cash = cash;
    }

    public int getCash() {
        return this.cash;
    }
}