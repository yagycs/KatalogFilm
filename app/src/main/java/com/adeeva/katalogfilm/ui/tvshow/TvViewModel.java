package com.adeeva.katalogfilm.ui.tvshow;

import androidx.lifecycle.ViewModel;

import com.adeeva.katalogfilm.data.FilmEntity;
import com.adeeva.katalogfilm.utils.DataDummy;

import java.util.List;

public class TvViewModel extends ViewModel {

    public List<FilmEntity> getTvs(){
        return DataDummy.generateDummyTv();
    }
}
