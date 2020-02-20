package com.adeeva.katalogfilm.ui.movie;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieViewModelTest {

    private MovieViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private Observer<List<FilmEntity>> observer;

    @Before
    public void setUp() {
        viewModel = new MovieViewModel(filmRepository);
    }

    @Test
    public void getMovies() {
        ArrayList<FilmEntity> dummyMovies = DataDummy.generateDummyMovie();
        MutableLiveData<List<FilmEntity>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);

        when(filmRepository.getAllMovies()).thenReturn(movies);
        List<FilmEntity> filmEntities = viewModel.getMovies().getValue();
        verify(filmRepository).getAllMovies();
        assertNotNull(filmEntities);
        assertEquals(5, filmEntities.size());

        viewModel.getMovies().observeForever(observer);
        verify(observer).onChanged(dummyMovies);
    }
}