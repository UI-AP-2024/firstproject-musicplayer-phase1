package model;

public enum PremiumPkg {
    ONE_MONTH(5),TWO_MONTH(9),SIX_MONTH(14);
    private int count;
    PremiumPkg(int count) {
        this.count = count;
    }
}
