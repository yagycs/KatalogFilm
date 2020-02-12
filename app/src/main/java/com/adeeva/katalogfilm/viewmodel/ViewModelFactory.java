package com.adeeva.katalogfilm.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.adeeva.katalogfilm.data.source.FilmRepository;
import com.adeeva.katalogfilm.di.Injection;
import com.adeeva.katalogfilm.ui.detail.DetailFilmViewModel;
import com.adeeva.katalogfilm.ui.movie.MovieViewModel;
import com.adeeva.katalogfilm.ui.tvshow.TvViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory INSTANCE;

    private final FilmRepository mFilmRepository;

    private ViewModelFactory(FilmRepository filmRepository) {
        mFilmRepository = filmRepository;
    }

    public static ViewModelFactory getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(context));
                }
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new MovieViewModel(mFilmRepository);
        } else if (modelClass.isAssignableFrom(TvViewModel.class)) {
            return (T) new TvViewModel(mFilmRepository);
        } else if (modelClass.isAssignableFrom(DetailFilmViewModel.class)) {
            return (T) new DetailFilmViewModel(mFilmRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
