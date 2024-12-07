package controller.command.process.artist;

import controller.ArtistController;
import controller.command.process.Process;
import controller.command.process.signup.LoginProcess;
import model.exception.ProcessException;
import model.model.user.Artist;
import model.validation.Validation;

import java.util.stream.Collectors;

public class FollowersProcess implements Process {

    private final ArtistController artistController;

    public FollowersProcess() {
        artistController = new ArtistController();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {

        if (commands.length != 1) {
            throw new ProcessException(this, "Invalid param");
        }

        Validation.checkLogin(this);

        try {
            final Artist artist = artistController.fetchAccountInfo(LoginProcess.user.getUsername());
            return artist.getFollowers().stream().map(item -> item.toString().replaceAll(",", "\n")).collect(Collectors.joining(","));
        } catch (Exception e) {
            throw new ProcessException(this, e.getMessage());
        }


    }
}
