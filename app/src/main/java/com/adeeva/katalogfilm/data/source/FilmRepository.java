package com.adeeva.katalogfilm.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
    public LiveData<List<FilmEntity>> getAllMovies() {
        MutableLiveData<List<FilmEntity>> movieResults = new MutableLiveData<>();
        remoteDataSource.getAllMovies(movieResponses -> {
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
            movieResults.postValue(movieList);
        });

        return movieResults;
    }

    @Override
    public LiveData<List<FilmEntity>> getAllTvs() {
        MutableLiveData<List<FilmEntity>> tvResults = new MutableLiveData<>();
        remoteDataSource.getAllTvs(tvResponses -> {
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
            tvResults.postValue(tvList);
        });

        return tvResults;
    }

    @Override
    public LiveData<FilmEntity> getMoviesWithDetail(final String movieId) {
        MutableLiveData<FilmEntity> movieResult = new MutableLiveData<>();
        remoteDataSource.getAllMovies(movieResponses -> {
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
            movieResult.postValue(movie);
        });

        return movieResult;
    }

    @Override
    public LiveData<FilmEntity> getTvsWithDetail(final String tvId) {
        MutableLiveData<FilmEntity> tvResult = new MutableLiveData<>();
        remoteDataSource.getAllTvs(tvResponses -> {
            FilmEntity tv = null;
            for (FilmResponse response : tvResponses) {
                if (response.getId().equals(tvId)) {
                    tv = new FilmEntity(
                            response.getId(),
                            response.getTitle(),
                            response.getDescription(),
                            response.getReleaseDate(),
                            response.getImagePath()
                    );
                }
            }
            tvResult.postValue(tv);
        });

        return tvResult;
    }
}
