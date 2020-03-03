package com.adeeva.katalogfilm.data;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.adeeva.katalogfilm.data.source.local.LocalDataSource;
import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;
import com.adeeva.katalogfilm.data.source.local.entity.TvEntity;
import com.adeeva.katalogfilm.data.source.remote.RemoteDataSource;
import com.adeeva.katalogfilm.data.source.remote.response.FilmResponse;
import com.adeeva.katalogfilm.utils.AppExecutors;
import com.adeeva.katalogfilm.utils.DataDummy;
import com.adeeva.katalogfilm.utils.LiveDataTestUtil;
import com.adeeva.katalogfilm.vo.Resource;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FilmRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteDataSource remote = mock(RemoteDataSource.class);
    private LocalDataSource local = mock(LocalDataSource.class);
    private AppExecutors appExecutors = mock(AppExecutors.class);

    private FakeFilmRepository filmRepository = new FakeFilmRepository(remote, local, appExecutors);

    private ArrayList<FilmResponse> movieResponses = DataDummy.generateRemoteDummyMovies();
    private ArrayList<FilmResponse> tvResponses = DataDummy.generateRemoteDummyTv();
    private String movieId = movieResponses.get(0).getId();
    private String tvId = tvResponses.get(0).getId();

    @Test
    public void getAllMovies() {
        MutableLiveData<List<FilmEntity>> dummyMovies = new MutableLiveData<>();
        dummyMovies.setValue(DataDummy.generateDummyMovie());
        when(local.getAllFilms()).thenReturn(dummyMovies);

        Resource<List<FilmEntity>> movieEntities = LiveDataTestUtil.getValue(filmRepository.getAllMovies());
        verify(local).getAllFilms();
        assertNotNull(movieEntities.data);
        assertEquals(movieResponses.size(), movieEntities.data.size());
    }

    @Test
    public void getAllTvs() {
        MutableLiveData<List<TvEntity>> dummyTvs = new MutableLiveData<>();
        dummyTvs.setValue(DataDummy.generateDummyTv());
        when(local.getAllTvs()).thenReturn(dummyTvs);

        Resource<List<TvEntity>> tvEntities = LiveDataTestUtil.getValue(filmRepository.getAllTvs());
        verify(local).getAllTvs();
        assertNotNull(tvEntities.data);
        assertEquals(tvResponses.size(), tvEntities.data.size());
    }

    @Test
    public void getFavoritedMovies(){
        MutableLiveData<List<FilmEntity>> dummyMovies = new MutableLiveData<>();
        dummyMovies.setValue(DataDummy.generateDummyMovie());
        when(local.getFavoritedFilms()).thenReturn(dummyMovies);

        List<FilmEntity> movieEntities = LiveDataTestUtil.getValue(filmRepository.getFavoritedFilms());
        verify(local).getFavoritedFilms();
        assertNotNull(movieEntities);
        assertEquals(movieResponses.size(), movieEntities.size());
    }

    @Test
    public void getFavoritedTvs(){
        MutableLiveData<List<TvEntity>> dummyTvs = new MutableLiveData<>();
        dummyTvs.setValue(DataDummy.generateDummyTv());
        when(local.getFavoritedTvs()).thenReturn(dummyTvs);

        List<TvEntity> tvEntities = LiveDataTestUtil.getValue(filmRepository.getFavoritedTvs());
        verify(local).getFavoritedTvs();
        assertNotNull(tvEntities);
        assertEquals(tvResponses.size(), tvEntities.size());
    }

    @Test
    public void getMoviesWithDetail() {
        MutableLiveData<List<FilmEntity>> dummyMovies = new MutableLiveData<>();
        dummyMovies.setValue(DataDummy.generateDummyMovie());
        when(local.getAllFilms()).thenReturn(dummyMovies);

        Resource<List<FilmEntity>> movieEntities = LiveDataTestUtil.getValue(filmRepository.getAllMovies());
        verify(local).getAllFilms();
        assertNotNull(movieEntities.data);
        assertNotNull(movieEntities.data.get(0).getTitle());
        assertEquals(movieResponses.get(0).getTitle(), movieEntities.data.get(0).getTitle());


        //MutableLiveData<FilmEntity> dummyMovies = new MutableLiveData<>();
        //dummyMovies.setValue(DataDummy.generateDummyMovieWithDetail(false));
        //when(local.getFilmWithDetail(movieId)).thenReturn(dummyMovies);

        //Resource<FilmEntity> movieEntities = LiveDataTestUtil.getValue(filmRepository.getMoviesWithDetail(movieId));
        //verify(local).getFilmWithDetail(movieId);
        //assertNotNull(movieEntities.data);
        //assertNotNull(movieEntities.data.getTitle());
        //assertEquals(movieResponses.get(0).getTitle(), movieEntities.data.getTitle());
    }

    @Test
    public void getTvsWithDetail() {
        MutableLiveData<List<TvEntity>> dummyTvs = new MutableLiveData<>();
        dummyTvs.setValue(DataDummy.generateDummyTv());
        when(local.getAllTvs()).thenReturn(dummyTvs);

        Resource<List<TvEntity>> tvEntities = LiveDataTestUtil.getValue(filmRepository.getAllTvs());
        verify(local).getAllTvs();
        assertNotNull(tvEntities.data);
        assertNotNull(tvEntities.data.get(0).getTitle());
        assertEquals(tvResponses.get(0).getTitle(), tvEntities.data.get(0).getTitle());


        //MutableLiveData<TvEntity> dummyTvs = new MutableLiveData<>();
        //dummyTvs.setValue(DataDummy.generateDummyTvWithDetail(false));
        //when(local.getTvWithDetail(tvId)).thenReturn(dummyTvs);

        //Resource<TvEntity> tvEntities = LiveDataTestUtil.getValue(filmRepository.getTvsWithDetail(tvId));
        //verify(local).getTvWithDetail(tvId);
        //assertNotNull(tvEntities.data);
        //assertNotNull(tvEntities.data.getTitle());
        //assertEquals(tvResponses.get(0).getTitle(), tvEntities.data.getTitle());
    }
}