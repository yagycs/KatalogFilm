package com.adeeva.katalogfilm.ui.favoritemovie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.adeeva.katalogfilm.data.FilmRepository;
import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;

public class FavoriteMovieViewModel extends ViewModel {

    private FilmRepository filmRepository;

    public FavoriteMovieViewModel(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public LiveData<PagedList<FilmEntity>> getFavorites() {
        return filmRepository.getFavoritedFilms();
    }

    void setFavoriteMovie(FilmEntity filmEntity) {
        final boolean newState = !filmEntity.isFavorited();
        filmRepository.setFilmFavorite(filmEntity, newState);
    }
}
