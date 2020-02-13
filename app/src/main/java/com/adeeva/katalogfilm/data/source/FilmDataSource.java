package com.adeeva.katalogfilm.data.source;

import androidx.lifecycle.LiveData;

import com.adeeva.katalogfilm.data.FilmEntity;

import java.util.List;

public interface FilmDataSource {

    LiveData<List<FilmEntity>> getAllMovies();

    LiveData<List<FilmEntity>> getAllTvs();

    LiveData<FilmEntity> getMoviesWithDetail(String movieId);

    LiveData<FilmEntity> getTvsWithDetail(String tvId);
}
