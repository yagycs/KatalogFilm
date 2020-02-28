package com.adeeva.katalogfilm.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.adeeva.katalogfilm.data.FilmRepository;
import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;
import com.adeeva.katalogfilm.data.source.local.entity.TvEntity;
import com.adeeva.katalogfilm.vo.Resource;

public class DetailFilmViewModel extends ViewModel {

    private MutableLiveData<String> movieId = new MutableLiveData<>();
    private MutableLiveData<String> tvId = new MutableLiveData<>();

    private FilmRepository filmRepository;

    public DetailFilmViewModel(FilmRepository mFilmRepository) {
        this.filmRepository = mFilmRepository;
    }

    public LiveData<Resource<FilmEntity>> filmDetail = Transformations.switchMap(movieId,
            mMovieId -> filmRepository.getMoviesWithDetail(mMovieId));

    public LiveData<Resource<TvEntity>> tvDetail = Transformations.switchMap(tvId,
            mTvId -> filmRepository.getTvsWithDetail(mTvId));

    public String getMovieId() {
        return movieId.getValue();
    }

    public String getTvId() {
        return tvId.getValue();
    }

    public void setSelectedMovie(String movieId) {
        this.movieId.setValue(movieId);
    }

    public void setSelectedTv(String tvId) {
        this.tvId.setValue(tvId);
    }

    void setFavorite() {
        Resource<FilmEntity> movieResource = filmDetail.getValue();
        Resource<TvEntity> tvResource = tvDetail.getValue();

        if (movieResource != null) {
            FilmEntity filmWithDetail = movieResource.data;

            if (filmWithDetail != null) {
                final boolean newState = !filmWithDetail.isFavorited();
                filmRepository.setFilmFavorite(filmWithDetail, newState);
            }

        } else if (tvResource != null) {
            TvEntity tvWithDetail = tvResource.data;

            if (tvWithDetail != null) {
                final boolean newState = !tvWithDetail.isFavorited();
                filmRepository.setTvFavorite(tvWithDetail, newState);
            }
        }
    }
}
