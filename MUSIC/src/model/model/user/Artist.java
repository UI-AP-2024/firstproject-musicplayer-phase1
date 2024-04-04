package model.model.user;

import java.util.List;

// آرتیست
public class Artist extends User {
    private float income;
    private List<User> followers;
    private String bio;

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "income=" + income +
                ", followers=" + followers +
                ", bio='" + bio + '\'' +
                "} " + super.toString();
    }
}
