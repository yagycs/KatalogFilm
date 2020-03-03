package com.adeeva.katalogfilm.ui.favoritemovie;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adeeva.katalogfilm.R;
import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;
import com.adeeva.katalogfilm.viewmodel.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment implements FavoriteMovieFragmentCallback{

    private RecyclerView rvFavorite;
    private ProgressBar progressBar;


    public FavoriteMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        rvFavorite = view.findViewById(R.id.rv_favorite_movie);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null){
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            FavoriteMovieViewModel viewModel = new ViewModelProvider(this, factory).get(FavoriteMovieViewModel.class);

            FavoriteMovieAdapter adapter = new FavoriteMovieAdapter(this);
            progressBar.setVisibility(View.VISIBLE);
            viewModel.getFavorites().observe(this, movies -> {
                progressBar.setVisibility(View.GONE);
                adapter.setMovie(movies);
                adapter.notifyDataSetChanged();
            });

            rvFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFavorite.setHasFixedSize(true);
            rvFavorite.setAdapter(adapter);
        }
    }

    @Override
    public void onShareClick(FilmEntity film){
        if (getActivity() != null){
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle("Bagikan aplikasi ini sekarang.")
                    .setText(String.format("Judul Film: %s", film.getTitle()))
                    .startChooser();
        }
    }
}
