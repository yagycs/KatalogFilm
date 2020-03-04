package com.adeeva.katalogfilm.ui.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.adeeva.katalogfilm.data.FilmRepository;
import com.adeeva.katalogfilm.data.source.local.entity.TvEntity;
import com.adeeva.katalogfilm.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
    private Observer<Resource<PagedList<TvEntity>>> observer;

    @Mock
    private PagedList<TvEntity> pagedList;

    @Before
    public void setUp() {
        viewModel = new TvViewModel(filmRepository);
    }

    @Test
    public void getTvs() {
        Resource<PagedList<TvEntity>> dummyTvs = Resource.success(pagedList);
        when(dummyTvs.data.size()).thenReturn(5);
        MutableLiveData<Resource<PagedList<TvEntity>>> tvs = new MutableLiveData<>();
        tvs.setValue(dummyTvs);

        when(filmRepository.getAllTvs()).thenReturn(tvs);
        List<TvEntity> filmEntities = viewModel.getTvs().getValue().data;
        verify(filmRepository).getAllTvs();
        assertNotNull(filmEntities);
        assertEquals(5, filmEntities.size());

        viewModel.getTvs().observeForever(observer);
        verify(observer).onChanged(dummyTvs);
    }
}