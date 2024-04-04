package controller.command.process.admin;

import controller.AdminController;
import controller.command.process.Process;
import controller.command.process.signup.LoginProcess;
import model.exception.ProcessException;
import model.model.user.Admin;
import model.validation.Validation;

import java.util.stream.Collectors;

public class ReportsProcess implements Process {

    private final AdminController adminController;

    public ReportsProcess() {
        adminController = new AdminController();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {

        if (commands.length != 1) {
            throw new ProcessException(this, "Invalid param");
        }

        Validation.checkLogin(this);
        if (!LoginProcess.user.getUsername().equals(Admin.getAdmin().getUsername())) {
            throw new ProcessException(this, "Not logged in");
        }

        return adminController.fetchReports().stream().map(item -> item.toString().replaceAll(",", "\n")).collect(Collectors.joining(","));
    }
}
