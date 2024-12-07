package Model.Accounts;

public enum Subscriptions {
    _30_DAYS(5 , 30) ,
    _60_DAYS(9 , 60) ,
    _180_DAYS(14 , 180);

    private final int price;
    private final int days;


    Subscriptions(int price , int days){
        this.price = price;
        this.days = days;
    }

    public int getPrice(){
        return this.price;
    }

    public int getDays(){
        return this.days;
    }

}
