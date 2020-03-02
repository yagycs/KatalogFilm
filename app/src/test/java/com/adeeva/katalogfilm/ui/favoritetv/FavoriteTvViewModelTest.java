package com.adeeva.katalogfilm.ui.favoritetv;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.adeeva.katalogfilm.data.FilmRepository;
import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;
import com.adeeva.katalogfilm.data.source.local.entity.TvEntity;
import com.adeeva.katalogfilm.ui.favoritemovie.FavoriteMovieViewModel;
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
public class FavoriteTvViewModelTest {

    private FavoriteTvViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private Observer<List<TvEntity>> observer;

    @Before
    public void setUp(){
        viewModel = new FavoriteTvViewModel(filmRepository);
    }

    @Test
    public void getFavoritesTv() {
        ArrayList<TvEntity> dummyTvs = DataDummy.generateDummyTv();
        MutableLiveData<List<TvEntity>> tvs = new MutableLiveData<>();
        tvs.setValue(dummyTvs);

        when(filmRepository.getFavoritedTvs()).thenReturn(tvs);
        List<TvEntity> tvEntities = viewModel.getFavoritesTv().getValue();
        verify(filmRepository).getFavoritedTvs();
        assertNotNull(tvEntities);
        assertEquals(5, tvEntities.size());

        viewModel.getFavoritesTv().observeForever(observer);
        verify(observer).onChanged(dummyTvs);
    }
}