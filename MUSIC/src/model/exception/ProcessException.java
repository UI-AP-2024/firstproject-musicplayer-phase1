package model.exception;

import controller.command.process.Process;

public class ProcessException extends Exception {

    public ProcessException(final Throwable cause) {
        super(cause);
    }

    public ProcessException(final Process process, final String message) {
        super(String.format("%s: %s", process.getClass().getSimpleName(), message));
    }
}
