package com.adeeva.katalogfilm.ui.movie;

import androidx.lifecycle.ViewModel;

import com.adeeva.katalogfilm.data.FilmEntity;
import com.adeeva.katalogfilm.utils.DataDummy;

import java.util.List;

public class MovieViewModel extends ViewModel {

    public List<FilmEntity> getMovies(){
        return DataDummy.generateDummyMovie();
    }
}
