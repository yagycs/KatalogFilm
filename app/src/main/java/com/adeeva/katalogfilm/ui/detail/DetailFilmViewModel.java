package com.adeeva.katalogfilm.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;
import com.adeeva.katalogfilm.data.FilmRepository;
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

    public String getMovie(){
        return movieId.getValue();
    }

    public String getTv(){
        return tvId.getValue();
    }

    public void setSelectedMovie(String movieId) {
        this.movieId.setValue(movieId);
    }

    public void setSelectedTv(String tvId) {
        this.tvId.setValue(tvId);
    }

    void setFavorite(){

    }
}
