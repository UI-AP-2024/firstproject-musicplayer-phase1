package controller.command.process.listener;

import controller.ListenerController;
import controller.ListenerControllerImp;
import controller.command.process.Process;
import controller.command.process.signup.LoginProcess;
import model.exception.ProcessException;
import model.model.audio.Audio;
import model.validation.Validation;

import java.util.List;
import java.util.stream.Collectors;

public class GetSuggestionsProcess implements Process {

    private final ListenerController listenerController;

    public GetSuggestionsProcess() {
        listenerController = new ListenerControllerImp();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {

        if (commands.length != 1) {
            throw new ProcessException(this, "Invalid param");
        }

        Validation.checkLogin(this);

        try {
            final List<Audio> audio = listenerController.fetchAudioByGenre(LoginProcess.user.getUsername());
            if (audio == null || audio.isEmpty()) {
                return "Noting";
            }
            return audio.stream().map(item -> item.toString().replaceAll(",", "\n")).collect(Collectors.joining(","));
        } catch (Exception e) {
            throw new ProcessException(this, e.getMessage());
        }

    }
}
