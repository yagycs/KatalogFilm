package com.adeeva.katalogfilm.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.adeeva.katalogfilm.data.FilmRepository;
import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;
import com.adeeva.katalogfilm.data.source.local.entity.TvEntity;
import com.adeeva.katalogfilm.utils.DataDummy;
import com.adeeva.katalogfilm.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailFilmViewModelTest {

    private DetailFilmViewModel viewModel;
    private FilmEntity dummyMovie = DataDummy.generateDummyMovie().get(0);
    private TvEntity dummyTv = DataDummy.generateDummyTv().get(0);
    private String movieId = dummyMovie.getFilmId();
    private String tvId = dummyTv.getFilmId();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private Observer<Resource<FilmEntity>> filmObserver;

    @Mock
    private Observer<Resource<TvEntity>> tvObserver;

    @Before
    public void setUp() {
        viewModel = new DetailFilmViewModel(filmRepository);
        viewModel.setSelectedMovie(movieId);
        viewModel.setSelectedTv(tvId);
    }

    @Test
    public void getMovieWithDetail() {
        Resource<FilmEntity> dummyMovieWithDetail = Resource.success(DataDummy.generateDummyMovieWithDetail(false));
        MutableLiveData<Resource<FilmEntity>> movie = new MutableLiveData<>();
        movie.setValue(dummyMovieWithDetail);

        when(filmRepository.getMoviesWithDetail(movieId)).thenReturn(movie);

        viewModel.filmDetail.observeForever(filmObserver);

        verify(filmObserver).onChanged(dummyMovieWithDetail);
    }

    @Test
    public void getTvWithDetail() {
        Resource<TvEntity> dummyTvWithDetail = Resource.success(DataDummy.generateDummyTvWithDetail(false));
        MutableLiveData<Resource<TvEntity>> tv = new MutableLiveData<>();
        tv.setValue(dummyTvWithDetail);

        when(filmRepository.getTvsWithDetail(tvId)).thenReturn(tv);

        viewModel.tvDetail.observeForever(tvObserver);

        verify(tvObserver).onChanged(dummyTvWithDetail);
    }
}