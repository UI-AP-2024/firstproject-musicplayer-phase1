package controller;

import model.dto.AudioPopularDto;
import model.model.report.Report;
import model.model.user.Admin;
import model.validation.Validation;

import java.util.List;

public class AdminController extends Controller {

    public boolean validation(final String username, final String password) throws Exception {
        Validation.usernameValidation(username);
        return Admin.getAdmin().getUsername().equals(username) && Admin.getAdmin().getPassword().equals(password);
    }


    public AudioPopularDto fetchAudioPopular() {
        return new AudioPopularDto(database.fetchMusicPopular(), database.fetchPodcastPopular());
    }


    public AudioPopularDto fetchAudioInfo() {
        return new AudioPopularDto(database.listMusic(), database.listPodcast());
    }


    public List<Report> fetchReports() {
        return database.listReport();
    }

    public Admin fetchAccountInfo() {
        return Admin.getAdmin();
    }
}
