package model.user;

public enum PremuimAcc {
    ONE_MONTH(5,30),TWO_MONTH(9,60),THREE_MONTH(14,180);

    int money;
    int days;
    PremuimAcc(int money,int days){
        this.money = money;
        this.days = days;
    }


    
}
