package com.adeeva.katalogfilm.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.adeeva.katalogfilm.data.FilmRepository;
import com.adeeva.katalogfilm.data.source.local.entity.TvEntity;
import com.adeeva.katalogfilm.vo.Resource;

public class TvViewModel extends ViewModel {

    private FilmRepository filmRepository;

    public TvViewModel(FilmRepository mFilmRepository) {
        this.filmRepository = mFilmRepository;
    }

    public LiveData<Resource<PagedList<TvEntity>>> getTvs() {
        return filmRepository.getAllTvs();
    }
}
