package com.adeeva.katalogfilm.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.adeeva.katalogfilm.data.FilmEntity;
import com.adeeva.katalogfilm.data.source.FilmRepository;

import java.util.List;

public class TvViewModel extends ViewModel {

    private FilmRepository filmRepository;

    public TvViewModel(FilmRepository mFilmRepository) {
        this.filmRepository = mFilmRepository;
    }

    public LiveData<List<FilmEntity>> getTvs() {
        return filmRepository.getAllTvs();
    }
}
