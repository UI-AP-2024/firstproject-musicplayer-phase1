package controller.command.process.signup;

import controller.UserController;
import controller.command.process.Process;
import model.exception.ProcessException;
import model.model.user.User;

public class LoginProcess implements Process {

    private final UserController userController;

    public static User user;

    public LoginProcess() {
        userController = new UserController();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {

        if (commands.length != 3) {
            throw new ProcessException(this, "Invalid param");
        }

        final String username = getValue(commands[1]);
        final String password = getValue(commands[2]);

        try {
            user = userController.validationLoginInfo(username, password);
            if (user == null) {
                throw new Exception("Invalid info");
            }
            return "Successfully login\n" + user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ProcessException(this, e.getMessage());
        }
    }
}
