package com.adeeva.katalogfilm.ui.detail;

import androidx.lifecycle.ViewModel;

import com.adeeva.katalogfilm.data.FilmEntity;
import com.adeeva.katalogfilm.data.source.FilmRepository;
import com.adeeva.katalogfilm.utils.DataDummy;

import java.util.ArrayList;
import java.util.List;

public class DetailFilmViewModel extends ViewModel {

    private String movieId;
    private String tvId;

    private FilmRepository filmRepository;

    public DetailFilmViewModel(FilmRepository mFilmRepository) {
        this.filmRepository = mFilmRepository;
    }

    public void setSelectedMovie(String movieId) {
        this.movieId = movieId;
    }

    public void setSelectedTv(String tvId) {
        this.tvId = tvId;
    }

    public FilmEntity getMovie(){
        return filmRepository.getMoviesWithDetail(movieId);
    }

    public FilmEntity getTv() {
        return filmRepository.getTvsWithDetail(tvId);
    }
}
