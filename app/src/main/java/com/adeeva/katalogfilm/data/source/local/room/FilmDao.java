package com.adeeva.katalogfilm.data.source.local.room;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;
import com.adeeva.katalogfilm.data.source.local.entity.TvEntity;

import java.util.List;

@Dao
public interface FilmDao {

    @Query("SELECT * FROM filmentities")
        //LiveData<List<FilmEntity>> getFilms();
    DataSource.Factory<Integer, FilmEntity> getFilms();

    @Query("SELECT * FROM tventities")
        //LiveData<List<TvEntity>> getTvs();
    DataSource.Factory<Integer, TvEntity> getTvs();

    @Query("SELECT * FROM filmentities where favorited = 1")
        //LiveData<List<FilmEntity>> getFavoritedFilm();
    DataSource.Factory<Integer, FilmEntity> getFavoritedFilm();

    @Query("SELECT * FROM tventities where favorited = 1")
        //LiveData<List<TvEntity>> getFavoritedTv();
    DataSource.Factory<Integer, TvEntity> getFavoritedTv();

    @Transaction
    @Query("SELECT * FROM filmentities WHERE filmId = :filmId")
    LiveData<FilmEntity> getFilmWithDetail(String filmId);

    @Transaction
    @Query("SELECT * FROM tventities WHERE filmId = :tvId")
    LiveData<TvEntity> getTvWithDetail(String tvId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFilms(List<FilmEntity> films);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTvs(List<TvEntity> tvs);

    @Update
    void updateFilm(FilmEntity film);

    @Update
    void updateTv(TvEntity tv);

}
