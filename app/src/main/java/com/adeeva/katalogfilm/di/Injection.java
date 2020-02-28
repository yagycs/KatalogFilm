package com.adeeva.katalogfilm.di;

import android.content.Context;

import com.adeeva.katalogfilm.data.FilmRepository;
import com.adeeva.katalogfilm.data.source.local.LocalDataSource;
import com.adeeva.katalogfilm.data.source.local.room.FilmDatabase;
import com.adeeva.katalogfilm.data.source.remote.RemoteDataSource;
import com.adeeva.katalogfilm.utils.AppExecutors;
import com.adeeva.katalogfilm.utils.JsonHelper;

public class Injection {

    public static FilmRepository provideRepository(Context context) {

        FilmDatabase database = FilmDatabase.getInstance(context);

        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(new JsonHelper(context));
        LocalDataSource localDataSource = LocalDataSource.getInstance(database.filmDao());
        AppExecutors appExecutors = new AppExecutors();

        return FilmRepository.getInstance(remoteDataSource, localDataSource, appExecutors);
    }
}
