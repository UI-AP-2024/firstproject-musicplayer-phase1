package controller.command.process.admin;

import controller.command.process.Process;
import controller.command.process.signup.LoginProcess;
import model.database.Database;
import model.exception.ProcessException;
import model.model.user.Admin;
import model.validation.Validation;

import java.util.stream.Collectors;

public class ArtistsProcess implements Process {

    private final Database database;

    public ArtistsProcess() {
        database = Database.getDatabase();
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

        return database.getArtists().stream().map(item -> item.toString().replaceAll(",", "\n")).collect(Collectors.joining(","));
    }
}
