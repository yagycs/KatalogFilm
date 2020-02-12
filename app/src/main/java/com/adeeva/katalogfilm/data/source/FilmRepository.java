package com.adeeva.katalogfilm.data.source;

import androidx.annotation.NonNull;

import com.adeeva.katalogfilm.data.FilmEntity;
import com.adeeva.katalogfilm.data.source.remote.RemoteDataSource;
import com.adeeva.katalogfilm.data.source.remote.response.FilmResponse;

import java.util.ArrayList;
import java.util.List;

public class FilmRepository implements FilmDataSource {

    private volatile static FilmRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;

    private FilmRepository(@NonNull RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static FilmRepository getInstance(RemoteDataSource remoteData) {
        if (INSTANCE == null) {
            synchronized (FilmRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FilmRepository(remoteData);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public ArrayList<FilmEntity> getAllMovies() {
        List<FilmResponse> movieResponses = remoteDataSource.getAllMovies();
        ArrayList<FilmEntity> movieList = new ArrayList<>();
        for (FilmResponse response : movieResponses) {
            FilmEntity movie = new FilmEntity(
                    response.getId(),
                    response.getTitle(),
                    response.getDescription(),
                    response.getReleaseDate(),
                    response.getImagePath());

            movieList.add(movie);
        }
        return movieList;
    }

    @Override
    public ArrayList<FilmEntity> getAllTvs() {
        List<FilmResponse> tvResponses = remoteDataSource.getAllTvs();
        ArrayList<FilmEntity> tvList = new ArrayList<>();
        for (FilmResponse response : tvResponses) {
            FilmEntity tv = new FilmEntity(
                    response.getId(),
                    response.getTitle(),
                    response.getDescription(),
                    response.getReleaseDate(),
                    response.getImagePath());

            tvList.add(tv);
        }
        return tvList;
    }

    @Override
    public FilmEntity getMoviesWithDetail(final String movieId) {
        List<FilmResponse> movieResponses = remoteDataSource.getAllMovies();
        FilmEntity movie = null;
        for (FilmResponse response : movieResponses) {
            if (response.getId().equals(movieId)) {
                movie = new FilmEntity(
                        response.getId(),
                        response.getTitle(),
                        response.getDescription(),
                        response.getReleaseDate(),
                        response.getImagePath()
                );
            }
        }
        return movie;
    }

    @Override
    public FilmEntity getTvsWithDetail(final String tvId){
        List<FilmResponse> tvResponse = remoteDataSource.getAllTvs();
        FilmEntity tv = null;
        for (FilmResponse response : tvResponse){
            if (response.getId().equals(tvId)){
                tv = new FilmEntity(
                        response.getId(),
                        response.getTitle(),
                        response.getDescription(),
                        response.getReleaseDate(),
                        response.getImagePath()
                );
            }
        }
        return tv;
    }
}
