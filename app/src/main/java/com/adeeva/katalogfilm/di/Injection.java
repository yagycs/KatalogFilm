package com.adeeva.katalogfilm.di;

import android.content.Context;

import com.adeeva.katalogfilm.data.FilmRepository;
import com.adeeva.katalogfilm.data.source.remote.RemoteDataSource;
import com.adeeva.katalogfilm.utils.JsonHelper;

public class Injection {

    public static FilmRepository provideRepository(Context context) {

        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(new JsonHelper(context));

        return FilmRepository.getInstance(remoteDataSource);
    }
}
