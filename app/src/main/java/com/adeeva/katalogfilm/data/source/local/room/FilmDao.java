package com.adeeva.katalogfilm.data.source.local.room;

import androidx.lifecycle.LiveData;
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
    LiveData<List<FilmEntity>> getFilms();

    @Query("SELECT * FROM tventities")
    LiveData<List<TvEntity>> getTvs();

    @Query("SELECT * FROM filmentities where favorited = 1")
    LiveData<List<FilmEntity>> getFavoritedFilm();

    @Query("SELECT * FROM tventities where favorited = 1")
    LiveData<List<TvEntity>> getFavoritedTv();

    @Transaction
    @Query("SELECT * FROM filmentities WHERE filmId = :filmId")
    LiveData<FilmEntity> getFilmWithDetail(String filmId);

    @Transaction
    @Query("SELECT * FROM tventities WHERE filmId = :filmId")
    LiveData<TvEntity> getTvWithDetail(String filmId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFilms(List<FilmEntity> films);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTvs(List<TvEntity> tvs);

    @Update
    void updateFilm(FilmEntity film);

    @Update
    void updateTv(TvEntity tv);

}
