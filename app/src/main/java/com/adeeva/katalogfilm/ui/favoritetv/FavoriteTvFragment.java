package com.adeeva.katalogfilm.ui.favoritetv;


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
import com.adeeva.katalogfilm.data.source.local.entity.TvEntity;
import com.adeeva.katalogfilm.ui.favoritemovie.FavoriteMovieAdapter;
import com.adeeva.katalogfilm.ui.favoritemovie.FavoriteMovieViewModel;
import com.adeeva.katalogfilm.viewmodel.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvFragment extends Fragment implements FavoriteTvFragmentCallback {

    private RecyclerView rvFavorite;
    private ProgressBar progressBar;

    public FavoriteTvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        rvFavorite = view.findViewById(R.id.rv_favorite);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null){
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            FavoriteTvViewModel viewModel = new ViewModelProvider(this, factory).get(FavoriteTvViewModel.class);

            FavoriteTvAdapter adapter = new FavoriteTvAdapter(this);
            progressBar.setVisibility(View.VISIBLE);
            viewModel.getFavoritesTv().observe(this, tvs -> {
                progressBar.setVisibility(View.GONE);
                adapter.setTv(tvs);
                adapter.notifyDataSetChanged();
            });

            rvFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFavorite.setHasFixedSize(true);
            rvFavorite.setAdapter(adapter);
        }
    }

    @Override
    public void onShareClick(TvEntity tv) {
        if (getActivity() != null){
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle("Bagikan aplikasi ini sekarang.")
                    .setText(String.format("Judul Film: %s", tv.getTitle()))
                    .startChooser();
        }
    }
}
