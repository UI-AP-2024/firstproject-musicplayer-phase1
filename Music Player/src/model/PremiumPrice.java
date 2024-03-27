package model;

public enum PremiumPrice
{
    ONEMONTH(5),TWOMONTH(9),THREEMONTH(14);

    private int price;
    private PremiumPrice(int price)
    {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
