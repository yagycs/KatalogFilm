package com.adeeva.katalogfilm.ui.detail;

import com.adeeva.katalogfilm.data.FilmEntity;
import com.adeeva.katalogfilm.utils.DataDummy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DetailFilmViewModelTest {

    private DetailFilmViewModel viewModel;
    private FilmEntity dummyMovie = DataDummy.generateDummyMovie().get(0);
    private FilmEntity dummyTv = DataDummy.generateDummyTv().get(0);
    private String movieId = dummyMovie.getFilmId();
    private String tvId = dummyTv.getFilmId();

    @Before
    public void setUp(){
        viewModel = new DetailFilmViewModel();
        viewModel.setSelectedMovie(movieId);
        viewModel.setSelectedTv(tvId);
    }

    @Test
    public void getMovie() {
        viewModel.setSelectedMovie(dummyMovie.getFilmId());
        FilmEntity filmEntity = viewModel.getMovie();
        assertNotNull(filmEntity);
        assertEquals(dummyMovie.getFilmId(), filmEntity.getFilmId());
        assertEquals(dummyMovie.getDescription(), filmEntity.getDescription());
        assertEquals(dummyMovie.getReleaseDate(), filmEntity.getReleaseDate());
        assertEquals(dummyMovie.getImagePath(), filmEntity.getImagePath());
        assertEquals(dummyMovie.getTitle(), filmEntity.getTitle());
    }

    @Test
    public void getTv() {
        viewModel.setSelectedTv(dummyTv.getFilmId());
        FilmEntity filmEntity = viewModel.getTv();
        assertNotNull(filmEntity);
        assertEquals(dummyTv.getFilmId(), filmEntity.getFilmId());
        assertEquals(dummyTv.getDescription(), filmEntity.getDescription());
        assertEquals(dummyTv.getReleaseDate(), filmEntity.getReleaseDate());
        assertEquals(dummyTv.getImagePath(), filmEntity.getImagePath());
        assertEquals(dummyTv.getTitle(), filmEntity.getTitle());
    }
}