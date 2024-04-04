package model.model.user;

// شنونده پرمیوم
public class PremiumListener extends Listener {
    private short numberOfRemainingDaysSubscription; // تعداد روزهای باقی مانده اشتراک

    public short getNumberOfRemainingDaysSubscription() {
        return numberOfRemainingDaysSubscription;
    }

    public void setNumberOfRemainingDaysSubscription(short numberOfRemainingDaysSubscription) {
        this.numberOfRemainingDaysSubscription = numberOfRemainingDaysSubscription;
    }

    @Override
    public String toString() {
        return "PremiumListener{" +
                "numberOfRemainingDaysSubscription=" + numberOfRemainingDaysSubscription +
                "} " + super.toString();
    }
}
