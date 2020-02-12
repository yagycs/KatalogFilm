package com.adeeva.katalogfilm.ui.movie;

import androidx.lifecycle.ViewModel;

import com.adeeva.katalogfilm.data.FilmEntity;
import com.adeeva.katalogfilm.data.source.FilmRepository;
import com.adeeva.katalogfilm.utils.DataDummy;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private FilmRepository filmRepository;

    public MovieViewModel(FilmRepository mFilmRepository) {
        this.filmRepository = mFilmRepository;
    }

    public List<FilmEntity> getMovies(){
        return filmRepository.getAllMovies();
    }
}
