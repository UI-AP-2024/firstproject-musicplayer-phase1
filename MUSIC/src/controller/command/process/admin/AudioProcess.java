package controller.command.process.admin;

import controller.command.process.Process;
import controller.command.process.signup.LoginProcess;
import model.database.Database;
import model.exception.ProcessException;
import model.model.audio.Audio;
import model.model.user.Admin;
import model.validation.Validation;

public class AudioProcess implements Process {

    private final Database database;

    public AudioProcess() {
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

        final int audioId;
        try {
            audioId = Integer.parseInt(getValue(commands[1]));
        } catch (Exception e) {
            throw new ProcessException(this, "Invalid param");
        }


        final Audio audio = database.fetchAudioById(audioId);
        if (audio == null) {
            throw new ProcessException(this, "Not found audio by id: " + audioId);
        }

        return audio.toString();

    }
}
