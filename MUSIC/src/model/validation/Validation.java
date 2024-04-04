package model.validation;

import controller.command.process.Process;
import controller.command.process.signup.LoginProcess;
import model.exception.ProcessException;

public class Validation {

    private Validation() {

    }

    public static void usernameValidation(final String username) throws Exception {
        isEmpty(username, "Username");
        if (!username.matches("^[a-zA-Z0-9._-]{3,}$")) {
            throw new Exception("Invalid username, Username: " + username);
        }
    }

    public static void phoneValidation(final String phone) throws Exception {
        isEmpty(phone, "Phone");
        if (!phone.matches("^(?:(?:\\\\+?|00)(98)|(0))?((?:90|91|92|93|99)[0-9]{8})$")) {
            throw new Exception("Invalid phone, Phone: " + phone);
        }
    }

    public static void emailValidation(final String email) throws Exception {
        isEmpty(email, "Email");
        if (!email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
            throw new Exception("Invalid email, Email: " + email);
        }
    }

    public static void isEmpty(final String val, final String name) throws Exception {
        if (val == null || val.isEmpty()) {
            throw new Exception(name + " is null");
        }
    }

    public static void checkLogin(final Process process) throws ProcessException {
        if (LoginProcess.user == null) {
            throw new ProcessException(process, "Not logged in");
        }
    }
}
