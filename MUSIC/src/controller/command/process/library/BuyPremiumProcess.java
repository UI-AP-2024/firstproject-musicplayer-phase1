package controller.command.process.library;

import controller.FreeListenerController;
import controller.ListenerControllerImp;
import controller.command.process.Process;
import controller.command.process.signup.LoginProcess;
import model.enums.PremiumSubscriptionPackages;
import model.exception.ProcessException;
import model.validation.Validation;

public class BuyPremiumProcess implements Process {

    private final FreeListenerController freeListenerController;

    public BuyPremiumProcess() {
        freeListenerController = new ListenerControllerImp();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {

        if (commands.length != 2) {
            throw new ProcessException(this, "Invalid param");
        }

        Validation.checkLogin(this);

        final PremiumSubscriptionPackages premiumSubscriptionPackages;
        try {
            premiumSubscriptionPackages = PremiumSubscriptionPackages.valueOf(getValue(commands[1]));
        } catch (Exception e) {
            throw new ProcessException(this, e.getMessage());
        }

        try {
            freeListenerController.buyPremium(LoginProcess.user.getUsername(), premiumSubscriptionPackages);
            LoginProcess.user = null;
            return "Successfully buy premium: " + premiumSubscriptionPackages.days;
        } catch (Exception e) {
            throw new ProcessException(this, e.getMessage());
        }

    }
}
