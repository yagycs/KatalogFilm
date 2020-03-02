package com.adeeva.katalogfilm.ui.favoritemovie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.adeeva.katalogfilm.data.FilmRepository;
import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;

import java.util.List;

public class FavoriteMovieViewModel extends ViewModel {

    private FilmRepository filmRepository;

    public FavoriteMovieViewModel(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    LiveData<List<FilmEntity>> getFavorites(){
        return filmRepository.getFavoritedFilms();
    }
}
