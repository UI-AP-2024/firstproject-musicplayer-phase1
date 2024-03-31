package model.AccountUser.Listener.TypeOfListener;

public enum premiumSub {
        ThirtyDays(5),
        SixtyDays(9),
        OneHundredEightyDays(14);

    //*********************************************
    private final double price;
    //*********************************************

    premiumSub(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }
    }

