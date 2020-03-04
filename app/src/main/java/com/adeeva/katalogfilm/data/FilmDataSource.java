package com.adeeva.katalogfilm.data;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;
import com.adeeva.katalogfilm.data.source.local.entity.TvEntity;
import com.adeeva.katalogfilm.vo.Resource;

import java.util.List;

// Kelas interface untuk menghubungkan repository local dan remote
public interface FilmDataSource {

    //LiveData<Resource<List<FilmEntity>>> getAllMovies();
    LiveData<Resource<PagedList<FilmEntity>>> getAllMovies();

    //LiveData<Resource<List<TvEntity>>> getAllTvs();
    LiveData<Resource<PagedList<TvEntity>>> getAllTvs();

    LiveData<Resource<FilmEntity>> getMoviesWithDetail(String movieId);

    LiveData<Resource<TvEntity>> getTvsWithDetail(String tvId);

    //LiveData<List<FilmEntity>> getFavoritedFilms();
    LiveData<PagedList<FilmEntity>> getFavoritedFilms();

    //LiveData<List<TvEntity>> getFavoritedTvs();
    LiveData<PagedList<TvEntity>> getFavoritedTvs();

    void setFilmFavorite(FilmEntity film, boolean state); //berfungsi untuk menambahkan film ke daftar favorite

    void setTvFavorite(TvEntity tv, boolean state); //berfungsi untuk menambahkan tv ke daftar favorite
}
