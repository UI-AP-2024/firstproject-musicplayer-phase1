package model;

import java.util.ArrayList;

public enum Genre
{
    ROCK,POP,JAZZ,HIPHOP,COUNTRY,TRUECRIME,SOCIETY,INTERVIEW,HISTORY;

    private ArrayList<Genre> favoriteGenres = new ArrayList<Genre>(4);

    public ArrayList<Genre> getFavoriteGenres() {
        return favoriteGenres;
    }

    public void setFavoriteGenres(ArrayList<Genre> favoriteGenres) {
        this.favoriteGenres = favoriteGenres;
    }
    public void genres(Genre first,Genre second,Genre third,Genre forth)
    {
        favoriteGenres.add(first);
        favoriteGenres.add(second);
        favoriteGenres.add(third);
        favoriteGenres.add(forth);
    }
}
