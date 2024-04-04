package controller.command.process.artist;

import controller.SingerController;
import controller.command.process.Process;
import controller.command.process.signup.LoginProcess;
import model.exception.ProcessException;
import model.validation.Validation;

public class NewAlbumProcess implements Process {

    private final SingerController singerController;

    public NewAlbumProcess() {
        singerController = new SingerController();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {

        if (commands.length != 2) {
            throw new ProcessException(this, "Invalid param");
        }

        Validation.checkLogin(this);

        final String albumName = getValue(commands[1]);

        try {
            singerController.saveAlbum(LoginProcess.user.getUsername(), albumName);
            return "Successfully save album";
        } catch (Exception e) {
            throw new ProcessException(this, e.getMessage());
        }


    }
}
