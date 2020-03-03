package com.adeeva.katalogfilm.data.source.local;

import androidx.lifecycle.LiveData;

import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;
import com.adeeva.katalogfilm.data.source.local.entity.TvEntity;
import com.adeeva.katalogfilm.data.source.local.room.FilmDao;

import java.util.List;

// kelas yang menghubungkan FilmDao dengan LocalDataSource
public class LocalDataSource {

    private static LocalDataSource INSTANCE;
    private final FilmDao mFilmDao;

    private LocalDataSource(FilmDao mFilmDao) {
        this.mFilmDao = mFilmDao;
    }

    public static LocalDataSource getInstance(FilmDao filmDao){
        if (INSTANCE == null){
            INSTANCE = new LocalDataSource(filmDao);
        }
        return INSTANCE;
    }

    public LiveData<List<FilmEntity>> getAllFilms(){
        return mFilmDao.getFilms();
    }

    public LiveData<List<TvEntity>> getAllTvs(){
        return mFilmDao.getTvs();
    }

    public LiveData<List<FilmEntity>> getFavoritedFilms(){
        return mFilmDao.getFavoritedFilm();
    }

    public LiveData<List<TvEntity>> getFavoritedTvs(){
        return mFilmDao.getFavoritedTv();
    }

    public LiveData<FilmEntity> getFilmWithDetail(final String filmId){
        return mFilmDao.getFilmWithDetail(filmId);
    }

    public LiveData<TvEntity> getTvWithDetail(final String tvId){
        return mFilmDao.getTvWithDetail(tvId);
    }

    public void insertFilms(List<FilmEntity> films){
        mFilmDao.insertFilms(films);
    }

    public void insertTvs(List<TvEntity> tvs){
        mFilmDao.insertTvs(tvs);
    }

    public void setFilmFavorited(FilmEntity film, boolean newState){
        film.setFavorited(newState);
        mFilmDao.updateFilm(film);
    }

    public void setTvFavorited(TvEntity tv, boolean newState){
        tv.setFavorited(newState);
        mFilmDao.updateTv(tv);
    }
}
