package com.adeeva.katalogfilm.data.source;

import com.adeeva.katalogfilm.data.FilmEntity;

import java.util.List;

public interface FilmDataSource {

    List<FilmEntity> getAllMovies();

    List<FilmEntity> getAllTvs();


    FilmEntity getMoviesWithDetail(String movieId);

    FilmEntity getTvsWithDetail(String tvId);
}
