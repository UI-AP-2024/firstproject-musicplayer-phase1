package controller.command.process;

import controller.ListenerController;
import controller.ListenerControllerImp;
import controller.command.process.signup.LoginProcess;
import model.enums.Genre;
import model.exception.ProcessException;
import model.validation.Validation;

import java.util.ArrayList;
import java.util.List;

public class FavouriteGenresProcess implements Process {

    private final ListenerController listenerController;

    public FavouriteGenresProcess() {
        listenerController = new ListenerControllerImp();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {

        if (commands.length != 2) {
            throw new ProcessException(this, "Invalid param");
        }

        Validation.checkLogin(this);

        final String[] split = getValue(commands[1]).split(",");
        final List<Genre> genres = new ArrayList<>();
        for (final String item : split) {
            genres.add(Genre.valueOf(item));
        }

        try {
            listenerController.updateFavouriteGenres(LoginProcess.user.getUsername(), genres);
            return "Successfully updated favourite genres";
        } catch (Exception e) {
            throw new ProcessException(this, e.getMessage());
        }

    }
}
