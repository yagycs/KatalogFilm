package com.adeeva.katalogfilm.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;
import androidx.paging.LivePagedListBuilder;

import com.adeeva.katalogfilm.data.source.local.LocalDataSource;
import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;
import com.adeeva.katalogfilm.data.source.local.entity.TvEntity;
import com.adeeva.katalogfilm.data.source.remote.ApiResponse;
import com.adeeva.katalogfilm.data.source.remote.RemoteDataSource;
import com.adeeva.katalogfilm.data.source.remote.response.FilmResponse;
import com.adeeva.katalogfilm.utils.AppExecutors;
import com.adeeva.katalogfilm.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public class FilmRepository implements FilmDataSource {

    private volatile static FilmRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;
    private final AppExecutors appExecutors;

    private FilmRepository(@NonNull RemoteDataSource remoteDataSource, @NonNull LocalDataSource localDataSource, AppExecutors appExecutors) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.appExecutors = appExecutors;
    }

    public static FilmRepository getInstance(RemoteDataSource remoteData, LocalDataSource localDataSource, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (FilmRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FilmRepository(remoteData, localDataSource, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<PagedList<FilmEntity>>> getAllMovies() {
        return new NetworkBoundResource<PagedList<FilmEntity>, List<FilmResponse>>(appExecutors) {
            @Override
            public LiveData<PagedList<FilmEntity>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getAllFilms(),config).build();
                // untuk membaca getAllFilms dari LocalDataSource kemudian akan diteruskan ke method shouldFetch di bawah ini
            }

            @Override
            public Boolean shouldFetch(PagedList<FilmEntity> data) {
                return (data == null) || (data.size() == 0);
                // dilakukan pengecekan apakah ada datanya atau tidak
                // jika balikan nilainya true maka akan memanggil fungsi berikut
            }

            @Override
            public LiveData<ApiResponse<List<FilmResponse>>> createCall() {
                return remoteDataSource.getAllMovies();
                // karena data dari LocalDataSource null atau empty, maka akan dilakukan pengambilan data dari RemoteDataSource
                // dan selanjutnya akan dilakukan proses inserting pada method bagian di bawah ini
            }

            @Override
            public void saveCallResult(List<FilmResponse> filmResponses) {
                ArrayList<FilmEntity> movieList = new ArrayList<>();
                for (FilmResponse response : filmResponses) {
                    FilmEntity movie = new FilmEntity(
                            response.getId(),
                            response.getTitle(),
                            response.getDescription(),
                            response.getReleaseDate(),
                            false,
                            response.getImagePath());

                    movieList.add(movie);
                }

                localDataSource.insertFilms(movieList);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TvEntity>>> getAllTvs() {
        return new NetworkBoundResource<PagedList<TvEntity>, List<FilmResponse>>(appExecutors) {
            @Override
            public LiveData<PagedList<TvEntity>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getAllTvs(),config).build();
                // untuk membaca getAllTvs dari LocalDataSource kemudian akan diteruskan ke method shouldFetch di bawah ini
            }

            @Override
            public Boolean shouldFetch(PagedList<TvEntity> data) {
                return (data == null) || (data.size() == 0);
                // dilakukan pengecekan apakah ada datanya atau tidak
                // jika balikan nilainya true maka akan memanggil fungsi berikut
            }

            @Override
            public LiveData<ApiResponse<List<FilmResponse>>> createCall() {
                return remoteDataSource.getAllTvs();
                // karena data dari LocalDataSource null atau empty, maka akan dilakukan pengambilan data dari RemoteDataSource
                // dan selanjutnya akan dilakukan proses inserting pada method bagian di bawah ini
            }

            @Override
            public void saveCallResult(List<FilmResponse> filmResponses) {
                ArrayList<TvEntity> tvList = new ArrayList<>();
                for (FilmResponse response : filmResponses) {
                    TvEntity tv = new TvEntity(
                            response.getId(),
                            response.getTitle(),
                            response.getDescription(),
                            response.getReleaseDate(),
                            false,
                            response.getImagePath());

                    tvList.add(tv);
                }

                localDataSource.insertTvs(tvList);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<PagedList<FilmEntity>> getFavoritedFilms() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getFavoritedFilms(),config).build();
    }

    @Override
    public LiveData<PagedList<TvEntity>> getFavoritedTvs() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getFavoritedTvs(),config).build();
    }

    @Override
    public LiveData<Resource<FilmEntity>> getMoviesWithDetail(final String movieId) {
        return new NetworkBoundResource<FilmEntity, List<FilmResponse>>(appExecutors) {
            @Override
            protected LiveData<FilmEntity> loadFromDB() {
                return localDataSource.getFilmWithDetail(movieId);
            }

            @Override
            protected Boolean shouldFetch(FilmEntity filmEntity) {
                return (filmEntity == null);
            }

            @Override
            protected LiveData<ApiResponse<List<FilmResponse>>> createCall() {
                return remoteDataSource.getAllMovies();
            }

            @Override
            protected void saveCallResult(List<FilmResponse> filmResponses) {
                ArrayList<FilmEntity> movieList = new ArrayList<>();
                for (FilmResponse response : filmResponses) {
                    if (response.getId().equals(movieId)) {
                        FilmEntity movie = new FilmEntity(
                                response.getId(),
                                response.getTitle(),
                                response.getDescription(),
                                response.getReleaseDate(),
                                false,
                                response.getImagePath());

                        movieList.add(movie);
                    }

                    localDataSource.insertFilms(movieList);
                }
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TvEntity>> getTvsWithDetail(final String tvId) {
        return new NetworkBoundResource<TvEntity, List<FilmResponse>>(appExecutors) {
            @Override
            protected LiveData<TvEntity> loadFromDB() {
                return localDataSource.getTvWithDetail(tvId);
            }

            @Override
            protected Boolean shouldFetch(TvEntity tvEntity) {
                return (tvEntity == null);
            }

            @Override
            protected LiveData<ApiResponse<List<FilmResponse>>> createCall() {
                return remoteDataSource.getAllTvs();
            }

            @Override
            protected void saveCallResult(List<FilmResponse> filmResponses) {
                ArrayList<TvEntity> tvList = new ArrayList<>();
                for (FilmResponse response : filmResponses) {
                    if (response.getId().equals(tvId)) {
                        TvEntity tv = new TvEntity(
                                response.getId(),
                                response.getTitle(),
                                response.getDescription(),
                                response.getReleaseDate(),
                                false,
                                response.getImagePath());

                        tvList.add(tv);
                    }

                    localDataSource.insertTvs(tvList);
                }
            }
        }.asLiveData();
    }

    @Override
    public void setFilmFavorite(FilmEntity film, boolean state) {
        appExecutors.diskIO().execute(() -> localDataSource.setFilmFavorited(film, state));
    }

    @Override
    public void setTvFavorite(TvEntity tv, boolean state) {
        appExecutors.diskIO().execute(() -> localDataSource.setTvFavorited(tv, state));
    }
}
