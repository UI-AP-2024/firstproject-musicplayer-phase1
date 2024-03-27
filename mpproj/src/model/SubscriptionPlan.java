package model;

public enum SubscriptionPlan {
    Onemonth(5),TwoMonth(9),sixMonth(14);
    private final int price;
    SubscriptionPlan(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
