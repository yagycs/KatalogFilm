package com.adeeva.katalogfilm.ui.movie;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.adeeva.katalogfilm.R;
import com.adeeva.katalogfilm.data.FilmEntity;
import com.adeeva.katalogfilm.viewmodel.ViewModelFactory;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private RecyclerView rvMovie;
    private ProgressBar progressBar;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        rvMovie = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null){
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            MovieViewModel viewModel = new ViewModelProvider(this, factory).get(MovieViewModel.class);

            MovieAdapter movieAdapter = new MovieAdapter();
            progressBar.setVisibility(View.VISIBLE);
            viewModel.getMovies().observe(this, movies ->{
                progressBar.setVisibility(View.GONE);
                movieAdapter.setFilms(movies);
                movieAdapter.notifyDataSetChanged();
            } );

            rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            rvMovie.setHasFixedSize(true);
            rvMovie.setAdapter(movieAdapter);
        }
    }
}
