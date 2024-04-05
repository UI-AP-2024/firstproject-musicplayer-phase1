package controller.command.process.signup;

import controller.command.process.Process;
import model.enums.ProcessName;
import model.exception.ProcessException;

public class SignupProcess implements Process {

    @Override
    public String exec(String[] commands) throws ProcessException {

        if (commands.length > 11) {
            throw new ProcessException(this, "Invalid param");
        }

        final String listenerType = commands[1];

        switch (getValue(listenerType)) {
            case "L":
                return ProcessName.SIGNUP_LISTENER.process.exec(commands);
            case "S":
                return ProcessName.SIGNUP_SINGER.process.exec(commands);
            case "P":
                return ProcessName.SIGNUP_PODCASTER.process.exec(commands);
            default:
                return "";
        }

    }

}
