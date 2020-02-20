package com.adeeva.katalogfilm.data;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.adeeva.katalogfilm.data.source.remote.RemoteDataSource;
import com.adeeva.katalogfilm.data.source.remote.response.FilmResponse;
import com.adeeva.katalogfilm.utils.DataDummy;
import com.adeeva.katalogfilm.utils.LiveDataTestUtil;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FilmRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteDataSource remote = mock(RemoteDataSource.class);
    private FakeFilmRepository filmRepository = new FakeFilmRepository(remote);

    private ArrayList<FilmResponse> movieResponses = DataDummy.generateRemoteDummyMovies();
    private ArrayList<FilmResponse> tvResponses = DataDummy.generateRemoteDummyTv();
    private String movieId = movieResponses.get(0).getId();
    private String tvId = tvResponses.get(0).getId();

    @Test
    public void getAllMovies() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadFilmCallback) invocation.getArguments()[0])
                    .onAllFilmReceived(movieResponses);
            return null;
        }).when(remote).getAllMovies(any(RemoteDataSource.LoadFilmCallback.class));
        List<FilmEntity> movieEntities = LiveDataTestUtil.getValue(filmRepository.getAllMovies());
        verify(remote).getAllMovies(any(RemoteDataSource.LoadFilmCallback.class));
        assertNotNull(movieEntities);
        assertEquals(movieResponses.size(), movieEntities.size());
    }

    @Test
    public void getAllTvs() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadFilmCallback) invocation.getArguments()[0])
                    .onAllFilmReceived(tvResponses);
            return null;
        }).when(remote).getAllTvs(any(RemoteDataSource.LoadFilmCallback.class));
        List<FilmEntity> tvEntities = LiveDataTestUtil.getValue(filmRepository.getAllTvs());
        verify(remote).getAllTvs(any(RemoteDataSource.LoadFilmCallback.class));
        assertNotNull(tvEntities);
        assertEquals(tvResponses.size(), tvEntities.size());
    }

    @Test
    public void getMoviesWithDetail() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadFilmCallback) invocation.getArguments()[0])
                    .onAllFilmReceived(movieResponses);
            return null;
        }).when(remote).getAllMovies(any(RemoteDataSource.LoadFilmCallback.class));
        FilmEntity resultMovie = LiveDataTestUtil.getValue(filmRepository.getMoviesWithDetail(movieId));
        verify(remote).getAllMovies(any(RemoteDataSource.LoadFilmCallback.class));
        assertNotNull(resultMovie);
        assertNotNull(resultMovie.getTitle());
        assertEquals(movieResponses.get(0).getTitle(), resultMovie.getTitle());
    }

    @Test
    public void getTvsWithDetail() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadFilmCallback) invocation.getArguments()[0])
                    .onAllFilmReceived(tvResponses);
            return null;
        }).when(remote).getAllTvs(any(RemoteDataSource.LoadFilmCallback.class));
        FilmEntity resultTv = LiveDataTestUtil.getValue(filmRepository.getTvsWithDetail(tvId));
        verify(remote).getAllTvs(any(RemoteDataSource.LoadFilmCallback.class));
        assertNotNull(resultTv);
        assertNotNull(resultTv.getTitle());
        assertEquals(tvResponses.get(0).getTitle(), resultTv.getTitle());
    }
}