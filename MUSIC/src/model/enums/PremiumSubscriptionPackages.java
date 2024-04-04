package model.enums;

public enum PremiumSubscriptionPackages {
    STANDARD((short) 30, (short) 5),
    PLUS((short) 60, (short) 9),
    BEST((short) 180, (short) 14)
    //
    ;

    public final short days;
    public final short price;

    PremiumSubscriptionPackages(short days, short price) {
        this.days = days;
        this.price = price;
    }
}
