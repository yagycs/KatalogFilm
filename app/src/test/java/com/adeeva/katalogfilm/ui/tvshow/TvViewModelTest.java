package com.adeeva.katalogfilm.ui.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.adeeva.katalogfilm.data.FilmEntity;
import com.adeeva.katalogfilm.data.source.FilmRepository;
import com.adeeva.katalogfilm.utils.DataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TvViewModelTest {

    private TvViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private Observer<List<FilmEntity>> observer;

    @Before
    public void setUp(){
        viewModel = new TvViewModel(filmRepository);
    }

    @Test
    public void getTvs() {
        ArrayList<FilmEntity> dummyTvs = DataDummy.generateDummyTv();
        MutableLiveData<List<FilmEntity>> tvs = new MutableLiveData<>();
       tvs.setValue(dummyTvs);

        when(filmRepository.getAllTvs()).thenReturn(tvs);
        List<FilmEntity> filmEntities = viewModel.getTvs().getValue();
        verify(filmRepository).getAllTvs();
        assertNotNull(filmEntities);
        assertEquals(5, filmEntities.size());

        viewModel.getTvs().observeForever(observer);
        verify(observer).onChanged(dummyTvs);
    }
}