package com.adeeva.katalogfilm.ui.favoritemovie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.adeeva.katalogfilm.data.FilmRepository;
import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;

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
public class FavoriteMovieViewModelTest {

    private FavoriteMovieViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private Observer<PagedList<FilmEntity>> observer;

    @Mock
    private PagedList<FilmEntity> pagedList;

    @Before
    public void setUp() {
        viewModel = new FavoriteMovieViewModel(filmRepository);
    }

    @Test
    public void getFavorites() {
        PagedList<FilmEntity> dummyMovies = pagedList;
        when(dummyMovies.size()).thenReturn(5);
        MutableLiveData<PagedList<FilmEntity>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);

        when(filmRepository.getFavoritedFilms()).thenReturn(movies);
        List<FilmEntity> movieEntities = viewModel.getFavorites().getValue();
        verify(filmRepository).getFavoritedFilms();
        assertNotNull(movieEntities);
        assertEquals(5, movieEntities.size());

        viewModel.getFavorites().observeForever(observer);
        verify(observer).onChanged(dummyMovies);
    }
}