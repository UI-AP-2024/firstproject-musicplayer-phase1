package controller.command.process.signup;

import controller.FreeListenerController;
import controller.ListenerControllerImp;
import controller.command.process.Process;
import model.enums.Genre;
import model.exception.ProcessException;
import model.model.user.FreeListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SignupListenerProcess implements Process {

    private final FreeListenerController freeListenerController;

    public SignupListenerProcess() {
        freeListenerController = new ListenerControllerImp();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {

        final FreeListener listener = new FreeListener();
        try {
            listener.setUsername(getValue(commands[2]));
            listener.setPassword(getValue(commands[3]));
            listener.setFirstname(getValue(commands[4]));
            listener.setLastname(getValue(commands[5]));
            listener.setEmail(getValue(commands[6]));
            listener.setPhoneNumber(getValue(commands[7]));
            listener.setDateOfBirth(LocalDate.parse(getValue(commands[8]), DateTimeFormatter.ofPattern("yyyyMMdd")));

            final String[] split = getValue(commands[9]).split(",");
            final List<Genre> genres = new ArrayList<>();
            for (final String item : split) {
                genres.add(Genre.valueOf(item));
            }

            listener.setFavoriteGenre(genres);

        } catch (Exception e) {
            if (e instanceof ProcessException) throw e;
            else {
                throw new ProcessException(this, "Invalid param: " + e.getMessage());
            }
        }

        try {
            freeListenerController.register(listener);
            return "Successfully added new listener";
        } catch (Exception e) {
            throw new ProcessException(this, e.getMessage());
        }
    }
}
