package controller.command.process.listener;

import controller.ListenerController;
import controller.ListenerControllerImp;
import controller.command.process.Process;
import controller.command.process.signup.LoginProcess;
import model.exception.ProcessException;
import model.validation.Validation;

public class FollowProcess implements Process {

    private final ListenerController listenerController;

    public FollowProcess() {
        listenerController = new ListenerControllerImp();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {

        if (commands.length != 2) {
            throw new ProcessException(this, "Invalid param");
        }

        Validation.checkLogin(this);

        final String artistUsername = getValue(commands[1]);

        try {
            listenerController.followArtist(LoginProcess.user.getUsername(), artistUsername);
            return "Successfully follow artist: " + artistUsername;
        } catch (Exception e) {
            throw new ProcessException(this, e.getMessage());
        }

    }
}
