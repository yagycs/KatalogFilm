package com.adeeva.katalogfilm.data.source.remote;

import android.os.Handler;

import com.adeeva.katalogfilm.data.source.remote.response.FilmResponse;
import com.adeeva.katalogfilm.utils.EspressoIdlingResource;
import com.adeeva.katalogfilm.utils.JsonHelper;

import java.util.List;

public class RemoteDataSource {

    private static RemoteDataSource INSTANCE;
    private JsonHelper jsonHelper;

    private Handler handler = new Handler();

    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private RemoteDataSource(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteDataSource getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(helper);
        }
        return INSTANCE;
    }

    public void getAllMovies(LoadFilmCallback callback) {
        EspressoIdlingResource.increment();
        handler.postDelayed(() -> {
            callback.onAllFilmReceived(jsonHelper.loadMovies());
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    public void getAllTvs(LoadFilmCallback callback) {
        EspressoIdlingResource.increment();
        handler.postDelayed(() -> {
            callback.onAllFilmReceived(jsonHelper.loadTvs());
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    public interface LoadFilmCallback {
        void onAllFilmReceived(List<FilmResponse> filmResponses);
    }
}
