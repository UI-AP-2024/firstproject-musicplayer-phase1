package controller.command.process.library;

import controller.ListenerControllerImp;
import controller.PremiumListenerController;
import controller.command.process.Process;
import controller.command.process.signup.LoginProcess;
import model.enums.PremiumSubscriptionPackages;
import model.exception.ProcessException;
import model.validation.Validation;

public class RenewalPremiumProcess implements Process {

    private final PremiumListenerController premiumListenerController;

    public RenewalPremiumProcess() {
        premiumListenerController = new ListenerControllerImp();
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
            premiumListenerController.renewalPremium(LoginProcess.user.getUsername(), premiumSubscriptionPackages);
            LoginProcess.user = null;
            return "Successfully buy premium: " + premiumSubscriptionPackages.days;
        } catch (Exception e) {
            throw new ProcessException(this, e.getMessage());
        }
    }
}
