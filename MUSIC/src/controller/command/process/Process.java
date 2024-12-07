package controller.command.process;

import model.exception.ProcessException;

public interface Process {
    String exec(final String[] commands) throws ProcessException;

    default String getValue(final String command) throws ProcessException {
        if (!command.startsWith("-")) {
            throw new ProcessException(this, "Invalid param: " + command);
        }
        return command.substring(1);
    }

}
