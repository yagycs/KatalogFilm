package com.adeeva.katalogfilm.data;

import com.adeeva.katalogfilm.data.source.remote.RemoteDataSource;
import com.adeeva.katalogfilm.data.source.remote.response.FilmResponse;
import com.adeeva.katalogfilm.utils.DataDummy;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FilmRepositoryTest {

    private RemoteDataSource remote = Mockito.mock(RemoteDataSource.class);
    private FakeFilmRepository filmRepository = new FakeFilmRepository(remote);

    private ArrayList<FilmResponse> movieResponses = DataDummy.generateRemoteDummyMovies();
    private ArrayList<FilmResponse> tvResponses = DataDummy.generateRemoteDummyTv();
    private String movieId = movieResponses.get(0).getId();
    private String tvId = tvResponses.get(0).getId();

    @Test
    public void getAllMovies() {
        when(remote.getAllMovies()).thenReturn(movieResponses);
        ArrayList<FilmEntity> movieEntities = filmRepository.getAllMovies();
        verify(remote).getAllMovies();
        assertNotNull(movieEntities);
        assertEquals(movieResponses.size(), movieEntities.size());
    }

    @Test
    public void getAllTvs() {
        when(remote.getAllTvs()).thenReturn(tvResponses);
        ArrayList<FilmEntity> tvEntities = filmRepository.getAllTvs();
        verify(remote).getAllTvs();
        assertNotNull(tvEntities);
        assertEquals(tvResponses.size(), tvEntities.size());
    }

    @Test
    public void getMoviesWithDetail() {
        when(remote.getAllMovies()).thenReturn(movieResponses);
        FilmEntity resultMovie = filmRepository.getMoviesWithDetail(movieId);
        verify(remote).getAllMovies();
        assertNotNull(resultMovie);
        assertEquals(movieResponses.get(0).getTitle(), resultMovie.getTitle());
    }

    @Test
    public void getTvsWithDetail() {
        when(remote.getAllTvs()).thenReturn(tvResponses);
        FilmEntity resultTv = filmRepository.getTvsWithDetail(tvId);
        verify(remote).getAllTvs();
        assertNotNull(resultTv);
        assertEquals(tvResponses.get(0).getTitle(), resultTv.getTitle());
    }
}