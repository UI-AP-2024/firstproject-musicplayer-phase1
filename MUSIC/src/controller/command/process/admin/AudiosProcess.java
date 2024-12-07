package controller.command.process.admin;

import controller.AdminController;
import controller.command.process.Process;
import controller.command.process.signup.LoginProcess;
import model.dto.AudioPopularDto;
import model.exception.ProcessException;
import model.model.user.Admin;
import model.validation.Validation;

import java.util.stream.Collectors;

public class AudiosProcess implements Process {

    private final AdminController adminController;

    public AudiosProcess() {
        adminController = new AdminController();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {

        if (commands.length != 1) {
            throw new ProcessException(this, "Invalid param");
        }

        Validation.checkLogin(this);
        if (!LoginProcess.user.getUsername().equals(Admin.getAdmin().getUsername())) {
            throw new ProcessException(this, "Not logged in");
        }

        try {
            final AudioPopularDto audioPopularDto = adminController.fetchAudioInfo();
            final String music = audioPopularDto.getMusic().stream().map(item -> item.toString().replaceAll(",", "\n")).collect(Collectors.joining(","));
            final String podcast = audioPopularDto.getPodcasts().stream().map(item -> item.toString().replaceAll(",", "\n")).collect(Collectors.joining(","));
            return "Music: " + music + "\n" + "Podcast: " + podcast;
        } catch (Exception e) {
            throw new ProcessException(this, e.getMessage());
        }

    }
}
