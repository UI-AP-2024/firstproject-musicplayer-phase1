package controller.command.process.library;

import controller.ListenerController;
import controller.ListenerControllerImp;
import controller.command.process.Process;
import controller.command.process.signup.LoginProcess;
import model.database.Database;
import model.exception.ProcessException;
import model.model.audio.Audio;
import model.validation.Validation;

public class LikeAudioProcess implements Process {

    private final ListenerController listenerController;
    private final Database database;

    public LikeAudioProcess() {
        listenerController = new ListenerControllerImp();
        database = Database.getDatabase();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {

        if (commands.length != 2) {
            throw new ProcessException(this, "Invalid param");
        }

        Validation.checkLogin(this);

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

        try {
            listenerController.likeAudio(LoginProcess.user.getUsername(), audioId);
            return "Successfully like audio: " + audioId;
        } catch (Exception e) {
            throw new ProcessException(this, e.getMessage());
        }

    }
}
