package model.PremiumPackages;

public enum PremiumSubscriptionPackages {
    THIRTYDAYS(5),SIXTYDAYS(9),ONEHUNDREDEIGHTYDAYS(14);

    private final int value;
    private PremiumSubscriptionPackages(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
