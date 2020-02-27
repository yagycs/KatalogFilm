package com.adeeva.katalogfilm.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;
import com.adeeva.katalogfilm.data.FilmRepository;
import com.adeeva.katalogfilm.utils.DataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailFilmViewModelTest {

    private DetailFilmViewModel viewModel;
    private FilmEntity dummyMovie = DataDummy.generateDummyMovie().get(0);
    private FilmEntity dummyTv = DataDummy.generateDummyTv().get(0);
    private String movieId = dummyMovie.getFilmId();
    private String tvId = dummyTv.getFilmId();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private Observer<FilmEntity> filmObserver;

    @Before
    public void setUp() {
        viewModel = new DetailFilmViewModel(filmRepository);
        viewModel.setSelectedMovie(movieId);
        viewModel.setSelectedTv(tvId);
    }

    @Test
    public void getMovie() {
        MutableLiveData<FilmEntity> movie = new MutableLiveData<>();
        movie.setValue(dummyMovie);

        when(filmRepository.getMoviesWithDetail(movieId)).thenReturn(movie);
        FilmEntity filmEntity = viewModel.getMovie().getValue();
        verify(filmRepository).getMoviesWithDetail(movieId);
        assertNotNull(filmEntity);
        assertEquals(dummyMovie.getFilmId(), filmEntity.getFilmId());
        assertEquals(dummyMovie.getDescription(), filmEntity.getDescription());
        assertEquals(dummyMovie.getReleaseDate(), filmEntity.getReleaseDate());
        assertEquals(dummyMovie.getImagePath(), filmEntity.getImagePath());
        assertEquals(dummyMovie.getTitle(), filmEntity.getTitle());

        viewModel.getMovie().observeForever(filmObserver);
        verify(filmObserver).onChanged(dummyMovie);
    }

    @Test
    public void getTv() {
        MutableLiveData<FilmEntity> tv = new MutableLiveData<>();
        tv.setValue(dummyTv);

        when(filmRepository.getTvsWithDetail(tvId)).thenReturn(tv);
        FilmEntity filmEntity = viewModel.getTv().getValue();
        verify(filmRepository).getTvsWithDetail(tvId);
        assertNotNull(filmEntity);
        assertEquals(dummyTv.getFilmId(), filmEntity.getFilmId());
        assertEquals(dummyTv.getDescription(), filmEntity.getDescription());
        assertEquals(dummyTv.getReleaseDate(), filmEntity.getReleaseDate());
        assertEquals(dummyTv.getImagePath(), filmEntity.getImagePath());
        assertEquals(dummyTv.getTitle(), filmEntity.getTitle());

        viewModel.getTv().observeForever(filmObserver);
        verify(filmObserver).onChanged(dummyTv);
    }
}