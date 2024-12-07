package controller.command.process.admin;

import controller.command.process.Process;
import controller.command.process.signup.LoginProcess;
import model.database.Database;
import model.exception.ProcessException;
import model.model.user.Admin;
import model.validation.Validation;

public class ArtistProcess implements Process {

    private final Database database;

    public ArtistProcess() {
        database = Database.getDatabase();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {

        if (commands.length != 2) {
            throw new ProcessException(this, "Invalid param");
        }

        Validation.checkLogin(this);
        if (!LoginProcess.user.getUsername().equals(Admin.getAdmin().getUsername())) {
            throw new ProcessException(this, "Not logged in");
        }

        final String artistUsername = getValue(commands[1]);

        return database.fetchArtistByUsername(artistUsername).toString();
    }
}
