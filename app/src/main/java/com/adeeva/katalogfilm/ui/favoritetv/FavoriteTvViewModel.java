package com.adeeva.katalogfilm.ui.favoritetv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.adeeva.katalogfilm.data.FilmRepository;
import com.adeeva.katalogfilm.data.source.local.entity.TvEntity;

public class FavoriteTvViewModel extends ViewModel {

    private FilmRepository filmRepository;

    public FavoriteTvViewModel(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public LiveData<PagedList<TvEntity>> getFavoritesTv() {
        return filmRepository.getFavoritedTvs();
    }

    void setFavoriteTv(TvEntity tvEntity) {
        final boolean newState = !tvEntity.isFavorited();
        filmRepository.setTvFavorite(tvEntity, newState);
    }
}
