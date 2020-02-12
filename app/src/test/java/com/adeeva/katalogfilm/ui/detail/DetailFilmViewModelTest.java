package com.adeeva.katalogfilm.ui.detail;

import com.adeeva.katalogfilm.data.FilmEntity;
import com.adeeva.katalogfilm.data.source.FilmRepository;
import com.adeeva.katalogfilm.utils.DataDummy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailFilmViewModelTest {

    private DetailFilmViewModel viewModel;
    private FilmEntity dummyMovie = DataDummy.generateDummyMovie().get(0);
    private FilmEntity dummyTv = DataDummy.generateDummyTv().get(0);
    private String movieId = dummyMovie.getFilmId();
    private String tvId = dummyTv.getFilmId();

    @Mock
    private FilmRepository filmRepository;

    @Before
    public void setUp(){
        viewModel = new DetailFilmViewModel(filmRepository);
        viewModel.setSelectedMovie(movieId);
        viewModel.setSelectedTv(tvId);
    }

    @Test
    public void getMovie() {
        when(filmRepository.getMoviesWithDetail(movieId)).thenReturn(dummyMovie);
        FilmEntity filmEntity = viewModel.getMovie();
        verify(filmRepository).getMoviesWithDetail(movieId);
        assertNotNull(filmEntity);
        assertEquals(dummyMovie.getFilmId(), filmEntity.getFilmId());
        assertEquals(dummyMovie.getDescription(), filmEntity.getDescription());
        assertEquals(dummyMovie.getReleaseDate(), filmEntity.getReleaseDate());
        assertEquals(dummyMovie.getImagePath(), filmEntity.getImagePath());
        assertEquals(dummyMovie.getTitle(), filmEntity.getTitle());
    }

    @Test
    public void getTv() {
        when(filmRepository.getTvsWithDetail(tvId)).thenReturn(dummyTv);
        FilmEntity filmEntity = viewModel.getTv();
        verify(filmRepository).getTvsWithDetail(tvId);
        assertNotNull(filmEntity);
        assertEquals(dummyTv.getFilmId(), filmEntity.getFilmId());
        assertEquals(dummyTv.getDescription(), filmEntity.getDescription());
        assertEquals(dummyTv.getReleaseDate(), filmEntity.getReleaseDate());
        assertEquals(dummyTv.getImagePath(), filmEntity.getImagePath());
        assertEquals(dummyTv.getTitle(), filmEntity.getTitle());
    }
}