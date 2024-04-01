package model.user;

public enum PremuimAcc {
    ONE_MONTH(5,30),TWO_MONTH(9,60),THREE_MONTH(14,180);

    public final int money;
    public final int days;
    PremuimAcc(final int money,final int days){
        this.money = money;
        this.days = days;
    }
    public int getMoney() {
        return money;
    }
    public int getDays() {
        return days;
    }
    


    
}
