package com.adeeva.katalogfilm.ui.tvshow;

import com.adeeva.katalogfilm.data.FilmEntity;
import com.adeeva.katalogfilm.data.source.FilmRepository;
import com.adeeva.katalogfilm.utils.DataDummy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TvViewModelTest {

    private TvViewModel viewModel;

    @Mock
    private FilmRepository filmRepository;

    @Before
    public void setUp(){
        viewModel = new TvViewModel(filmRepository);
    }

    @Test
    public void getTvs() {
        when(filmRepository.getAllTvs()).thenReturn(DataDummy.generateDummyTv());
        List<FilmEntity> filmEntities = viewModel.getTvs();
        verify(filmRepository).getAllTvs();
        assertNotNull(filmEntities);
        assertEquals(5, filmEntities.size());
    }
}