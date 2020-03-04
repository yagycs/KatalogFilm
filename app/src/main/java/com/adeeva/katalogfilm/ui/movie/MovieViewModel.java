package com.adeeva.katalogfilm.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;
import com.adeeva.katalogfilm.data.FilmRepository;
import com.adeeva.katalogfilm.vo.Resource;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private FilmRepository filmRepository;

    public MovieViewModel(FilmRepository mFilmRepository) {
        this.filmRepository = mFilmRepository;
    }

    public LiveData<Resource<PagedList<FilmEntity>>> getMovies() {
        return filmRepository.getAllMovies();
    }
}
