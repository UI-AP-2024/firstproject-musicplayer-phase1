package controller.command.process.library;

import controller.ListenerController;
import controller.ListenerControllerImp;
import controller.command.process.Process;
import controller.command.process.signup.LoginProcess;
import model.exception.ProcessException;
import model.model.user.Artist;
import model.validation.Validation;

import java.util.List;
import java.util.stream.Collectors;

public class FollowingsProcess implements Process {

    private final ListenerController listenerController;

    public FollowingsProcess() {
        listenerController = new ListenerControllerImp();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {

        if (commands.length != 1) {
            throw new ProcessException(this, "Invalid param");
        }

        Validation.checkLogin(this);

        try {
            final List<Artist> artists = listenerController.fetchFollowings(LoginProcess.user.getUsername());
            return artists.stream().map(item -> item.toString().replaceAll(",", "\n")).collect(Collectors.joining(","));
        } catch (Exception e) {
            throw new ProcessException(this, e.getMessage());
        }

    }
}
