package com.adeeva.katalogfilm.ui.favoritetv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.adeeva.katalogfilm.data.FilmRepository;
import com.adeeva.katalogfilm.data.source.local.entity.TvEntity;

import java.util.List;

public class FavoriteTvViewModel extends ViewModel {

    private FilmRepository filmRepository;

    public FavoriteTvViewModel(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    LiveData<List<TvEntity>> getFavoritesTv(){
        return filmRepository.getFavoritedTvs();
    }
}
