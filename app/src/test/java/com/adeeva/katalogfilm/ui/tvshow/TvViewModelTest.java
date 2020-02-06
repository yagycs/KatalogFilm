package com.adeeva.katalogfilm.ui.tvshow;

import com.adeeva.katalogfilm.data.FilmEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TvViewModelTest {

    private TvViewModel viewModel;

    @Before
    public void setUp(){
        viewModel = new TvViewModel();
    }

    @Test
    public void getTvs() {
        List<FilmEntity> filmEntities = viewModel.getTvs();
        assertNotNull(filmEntities);
        assertEquals(5, filmEntities.size());
    }
}