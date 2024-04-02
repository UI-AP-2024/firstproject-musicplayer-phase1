package controller;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.AccountUser.Listener.Listener;
import model.Audio.Audio;


public class AdminC {
    private UserRegistration userRegistration;

    public AdminC(UserRegistration userRegistration) {
        this.userRegistration = userRegistration;
    }

    public void login(String username, String password) {
        if (checkCredentials(username, password)) {
            showUserPanel(username);
        } else {
            System.out.println("Invalid username or password");
        }
    }

    private void showUserPanel(String username) {
    }

    private boolean checkCredentials(String username, String password) {
        List<Listener> users = new ArrayList<>(userRegistration.getUsers().values());
        for (Listener user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


        public void displayPopularAudios(List<Audio> likedAudios, Map<Audio, Integer> playCountByAudio) {
            List<Audio> popularAudios = likedAudios.stream()
                    .sorted(Comparator.comparingInt(playCountByAudio::get).reversed())
                    .limit(10)
                    .collect(Collectors.toList());

            System.out.println("Top 10 popular audios based on likes:");
            for (int i = 0; i < popularAudios.size(); i++) {
                System.out.println((i + 1) + ". " + popularAudios.get(i).getTitle());
            }
        }


    }
