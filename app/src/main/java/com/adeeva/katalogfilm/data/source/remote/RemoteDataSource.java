package com.adeeva.katalogfilm.data.source.remote;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.adeeva.katalogfilm.data.source.remote.response.FilmResponse;
import com.adeeva.katalogfilm.utils.EspressoIdlingResource;
import com.adeeva.katalogfilm.utils.JsonHelper;

import java.util.List;

public class RemoteDataSource {

    private static RemoteDataSource INSTANCE;
    private JsonHelper jsonHelper;

    private Handler handler = new Handler(); // handler berfungsi untuk memberikan waktu delay sesuai kebutuhan
    // Catatan:
    // Penggunaan Handler untuk delay, seperti yang dilakukan di atas merupakah hal yang tidak disarankan.
    // Karena proyek yang kita buat hanyalah simulasi, di mana data yang diperoleh disimulasikan berasal dari server atau API.
    // Maka dari itu, penggunaan Handler pada proyek Academy digunakan untuk mensimulasikan proses asynchonous yang terjadi.

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

    public LiveData<ApiResponse<List<FilmResponse>>> getAllMovies() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<FilmResponse>>> resultMovie = new MutableLiveData<>();
        handler.postDelayed(() -> {
            resultMovie.setValue(ApiResponse.success(jsonHelper.loadMovies()));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultMovie;
    }

    public LiveData<ApiResponse<List<FilmResponse>>> getAllTvs() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<FilmResponse>>> resultTv = new MutableLiveData<>();
        handler.postDelayed(() -> {
            resultTv.setValue(ApiResponse.success(jsonHelper.loadTvs()));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultTv;
    }
}
