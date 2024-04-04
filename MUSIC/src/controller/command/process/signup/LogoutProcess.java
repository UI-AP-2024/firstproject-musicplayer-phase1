package controller.command.process.signup;

import controller.command.process.Process;
import model.exception.ProcessException;

public class LogoutProcess implements Process {
    @Override
    public String exec(final String[] commands) throws ProcessException {
        if (commands.length != 1) {
            throw new ProcessException(this, "Invalid param");
        }
        LoginProcess.user = null;
        return "Successfully logout";
    }
}
