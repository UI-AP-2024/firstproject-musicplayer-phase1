package Model;

public enum PremiumSubscription {
    DAYS30(5),
    DAYS60(9),
    DAYS180(14);
    private int subPrize;
    private PremiumSubscription(int subPrize){
        this.subPrize = subPrize;
    }
}
