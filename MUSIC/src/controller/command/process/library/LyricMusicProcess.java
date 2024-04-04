package controller.command.process.library;

import controller.command.process.Process;
import model.database.Database;
import model.exception.ProcessException;
import model.model.audio.Music;
import model.validation.Validation;

public class LyricMusicProcess implements Process {

    private final Database database;

    public LyricMusicProcess() {
        database = Database.getDatabase();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {

        if (commands.length != 2) {
            throw new ProcessException(this, "Invalid param");
        }

        Validation.checkLogin(this);

        final int musicId;
        try {
            musicId = Integer.parseInt(getValue(commands[1]));
        } catch (Exception e) {
            throw new ProcessException(this, "Invalid param");
        }

        final Music music = database.fetchMusicById(musicId);
        if (music == null) {
            throw new ProcessException(this, "Not found music by id: " + musicId);
        }

        return music.getMusicText();

    }
}
