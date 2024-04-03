package Model;

public enum PremiumPack {
    ONEMONTH(5,30), TWOMONTH(9,60), SIXMONTH(14,180);
    private int cash;
    private int days;

    PremiumPack(int cash,int days) {
        this.cash = cash;
        this.days=days;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getCash() {
        return cash;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
