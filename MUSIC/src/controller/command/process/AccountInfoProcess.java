package controller.command.process;

import controller.FreeListenerController;
import controller.ListenerControllerImp;
import controller.PremiumListenerController;
import controller.command.process.signup.LoginProcess;
import model.exception.ProcessException;
import model.model.user.FreeListener;
import model.model.user.PremiumListener;

public class AccountInfoProcess implements Process {

    private final FreeListenerController freeListenerController;
    private final PremiumListenerController premiumListenerController;

    public AccountInfoProcess() {
        freeListenerController = new ListenerControllerImp();
        premiumListenerController = new ListenerControllerImp();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {

        if (commands.length != 1) {
            throw new ProcessException(this, "Invalid param");
        }

        if (LoginProcess.user == null) {
            throw new ProcessException(this, "Not logged in");
        }

        try {
            final FreeListener listener = freeListenerController.fetchFreeListener(LoginProcess.user.getUsername());
            return listener.toString().replace(",", "\n");
        } catch (Exception e) {
            try {
                final PremiumListener listener = premiumListenerController.fetchPremiumListener(LoginProcess.user.getUsername());
                return listener.toString().replace(",", "\n");
            } catch (Exception ex) {
                throw new ProcessException(this, "Fatal Error");
            }
        }

    }
}
