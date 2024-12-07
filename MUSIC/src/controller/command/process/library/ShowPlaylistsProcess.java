package controller.command.process.library;

import controller.FreeListenerController;
import controller.ListenerControllerImp;
import controller.PremiumListenerController;
import controller.command.process.Process;
import controller.command.process.signup.LoginProcess;
import model.exception.ProcessException;
import model.model.user.Listener;
import model.validation.Validation;

import java.util.stream.Collectors;

public class ShowPlaylistsProcess implements Process {

    private final FreeListenerController freeListenerController;
    private final PremiumListenerController premiumListenerController;

    public ShowPlaylistsProcess() {
        freeListenerController = new ListenerControllerImp();
        premiumListenerController = new ListenerControllerImp();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {

        if (commands.length != 1) {
            throw new ProcessException(this, "Invalid param");
        }

        Validation.checkLogin(this);


        Listener listener = null;
        try {
            listener = freeListenerController.fetchFreeListener(LoginProcess.user.getUsername());
        } catch (Exception e) {
            try {
                listener = premiumListenerController.fetchPremiumListener(LoginProcess.user.getUsername());
            } catch (Exception ignored) {
            }
        }

        if (listener == null) {
            throw new ProcessException(this, "Fatal Error");
        }

        if (listener.getPlayLists() == null || listener.getPlayLists().isEmpty()) {
            return "Play list is empty";
        }

        return listener.getPlayLists().stream().map(item -> item.toString().replaceAll(",", "\n")).collect(Collectors.joining(","));
    }
}
