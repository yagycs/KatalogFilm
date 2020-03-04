package com.adeeva.katalogfilm.data;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.adeeva.katalogfilm.data.source.local.LocalDataSource;
import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;
import com.adeeva.katalogfilm.data.source.local.entity.TvEntity;
import com.adeeva.katalogfilm.data.source.remote.RemoteDataSource;
import com.adeeva.katalogfilm.data.source.remote.response.FilmResponse;
import com.adeeva.katalogfilm.utils.AppExecutors;
import com.adeeva.katalogfilm.utils.DataDummy;
import com.adeeva.katalogfilm.utils.LiveDataTestUtil;
import com.adeeva.katalogfilm.utils.PagedListUtil;
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
        //MutableLiveData<List<FilmEntity>> dummyMovies = new MutableLiveData<>();
        //dummyMovies.setValue(DataDummy.generateDummyMovie());
        //when(local.getAllFilms()).thenReturn(dummyMovies);

        DataSource.Factory<Integer, FilmEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getAllFilms()).thenReturn(dataSourceFactory);
        filmRepository.getAllMovies();

        Resource<PagedList<FilmEntity>> movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()));
        verify(local).getAllFilms();
        assertNotNull(movieEntities.data);
        assertEquals(movieResponses.size(), movieEntities.data.size());
    }

    @Test
    public void getAllTvs() {
        //MutableLiveData<List<TvEntity>> dummyTvs = new MutableLiveData<>();
        //dummyTvs.setValue(DataDummy.generateDummyTv());
        //when(local.getAllTvs()).thenReturn(dummyTvs);

        DataSource.Factory<Integer, TvEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getAllTvs()).thenReturn(dataSourceFactory);
        filmRepository.getAllTvs();

        Resource<PagedList<TvEntity>> tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTv()));
        verify(local).getAllTvs();
        assertNotNull(tvEntities.data);
        assertEquals(tvResponses.size(), tvEntities.data.size());
    }

    @Test
    public void getFavoritedMovies(){
        //MutableLiveData<List<FilmEntity>> dummyMovies = new MutableLiveData<>();
        //dummyMovies.setValue(DataDummy.generateDummyMovie());
        //when(local.getFavoritedFilms()).thenReturn(dummyMovies);

        DataSource.Factory<Integer, FilmEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getFavoritedFilms()).thenReturn(dataSourceFactory);
        filmRepository.getFavoritedFilms();

        Resource<PagedList<FilmEntity>> movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()));
        verify(local).getFavoritedFilms();
        assertNotNull(movieEntities);
        assertEquals(movieResponses.size(), movieEntities.data.size());
    }

    @Test
    public void getFavoritedTvs(){
        //MutableLiveData<List<TvEntity>> dummyTvs = new MutableLiveData<>();
        //dummyTvs.setValue(DataDummy.generateDummyTv());
        //when(local.getFavoritedTvs()).thenReturn(dummyTvs);

        DataSource.Factory<Integer, TvEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getFavoritedTvs()).thenReturn(dataSourceFactory);
        filmRepository.getFavoritedTvs();

        Resource<PagedList<TvEntity>> tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTv()));
        verify(local).getFavoritedTvs();
        assertNotNull(tvEntities);
        assertEquals(tvResponses.size(), tvEntities.data.size());
    }

    @Test
    public void getMoviesWithDetail() {
        //MutableLiveData<List<FilmEntity>> dummyMovies = new MutableLiveData<>();
        //dummyMovies.setValue(DataDummy.generateDummyMovie());
        //when(local.getAllFilms()).thenReturn(dummyMovies);

        //Resource<List<FilmEntity>> movieEntities = LiveDataTestUtil.getValue(filmRepository.getAllMovies());
        //verify(local).getAllFilms();
        //assertNotNull(movieEntities.data);
        //assertNotNull(movieEntities.data.get(0).getTitle());
        //assertEquals(movieResponses.get(0).getTitle(), movieEntities.data.get(0).getTitle());

        DataSource.Factory<Integer, FilmEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getAllFilms()).thenReturn(dataSourceFactory);
        filmRepository.getAllMovies();

        Resource<PagedList<FilmEntity>> movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()));
        verify(local).getAllFilms();
        assertNotNull(movieEntities.data);
        assertNotNull(movieEntities.data.get(0).getTitle());
        assertEquals(movieResponses.get(0).getTitle(), movieEntities.data.get(0).getTitle());

    }

    @Test
    public void getTvsWithDetail() {
        //MutableLiveData<List<TvEntity>> dummyTvs = new MutableLiveData<>();
        //dummyTvs.setValue(DataDummy.generateDummyTv());
        //when(local.getAllTvs()).thenReturn(dummyTvs);

        //Resource<List<TvEntity>> tvEntities = LiveDataTestUtil.getValue(filmRepository.getAllTvs());
        //verify(local).getAllTvs();
        //assertNotNull(tvEntities.data);
        //assertNotNull(tvEntities.data.get(0).getTitle());
        //assertEquals(tvResponses.get(0).getTitle(), tvEntities.data.get(0).getTitle());

        DataSource.Factory<Integer, TvEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getAllTvs()).thenReturn(dataSourceFactory);
        filmRepository.getAllTvs();

        Resource<PagedList<TvEntity>> tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTv()));
        verify(local).getAllTvs();
        assertNotNull(tvEntities.data);
        assertNotNull(tvEntities.data.get(0).getTitle());
        assertEquals(tvResponses.get(0).getTitle(), tvEntities.data.get(0).getTitle());
    }
}