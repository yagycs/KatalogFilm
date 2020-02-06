package com.adeeva.katalogfilm.ui.movie;

import com.adeeva.katalogfilm.data.FilmEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MovieViewModelTest {

    private MovieViewModel viewModel;

    @Before
    public void setUp(){
        viewModel = new MovieViewModel();
    }

    @Test
    public void getMovies() {
        List<FilmEntity> filmEntities = viewModel.getMovies();
        assertNotNull(filmEntities);
        assertEquals(5, filmEntities.size());
    }
}