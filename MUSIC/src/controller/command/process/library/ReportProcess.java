package controller.command.process.library;

import controller.ListenerController;
import controller.ListenerControllerImp;
import controller.command.process.Process;
import controller.command.process.signup.LoginProcess;
import model.exception.ProcessException;
import model.validation.Validation;

public class ReportProcess implements Process {

    private final ListenerController listenerController;

    public ReportProcess() {
        listenerController = new ListenerControllerImp();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {

        if (commands.length != 3) {
            throw new ProcessException(this, "Invalid param");
        }

        Validation.checkLogin(this);

        final String artistUsername = getValue(commands[1]);
        final String description = getValue(commands[2]);

        try {
            listenerController.reportArtist(LoginProcess.user.getUsername(), artistUsername, description);
            return "Successfully report: " + artistUsername;
        } catch (Exception e) {
            throw new ProcessException(this, e.getMessage());
        }

    }
}
