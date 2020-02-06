package com.adeeva.katalogfilm.ui.detail;

import androidx.lifecycle.ViewModel;

import com.adeeva.katalogfilm.data.FilmEntity;
import com.adeeva.katalogfilm.utils.DataDummy;

import java.util.ArrayList;

public class DetailFilmViewModel extends ViewModel {

    private String movieId;
    private String tvId;

    public void setSelectedMovie(String movieId) {
        this.movieId = movieId;
    }

    public void setSelectedTv(String tvId) {
        this.tvId = tvId;
    }

    public FilmEntity getMovie() {
        FilmEntity film = null;
        ArrayList<FilmEntity> filmEntities = DataDummy.generateDummyMovie();
        for (FilmEntity filmEntity : filmEntities) {
            if (filmEntity.getFilmId().equals(movieId)) {
                film = filmEntity;
            }
        }
        return film;
    }

    public FilmEntity getTv() {
        FilmEntity film = null;
        ArrayList<FilmEntity> filmEntities = DataDummy.generateDummyTv();
        for (FilmEntity filmEntity : filmEntities) {
            if (filmEntity.getFilmId().equals(tvId)) {
                film = filmEntity;
            }
        }
        return film;
    }
}
