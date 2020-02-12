package com.adeeva.katalogfilm.ui.detail;

import android.os.Bundle;

import com.adeeva.katalogfilm.data.FilmEntity;
import com.adeeva.katalogfilm.viewmodel.ViewModelFactory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adeeva.katalogfilm.R;

public class DetailFilmActivity extends AppCompatActivity {

    public static final String EXTRA_FILM = "extra_film";
    public static final String EXTRA_TV = "extra_tv";
    private TextView textTitle;
    private TextView textDesc;
    private TextView textDate;
    private ImageView imagePoster;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        textTitle = findViewById(R.id.text_title);
        textDesc = findViewById(R.id.text_description);
        textDate = findViewById(R.id.text_date);
        imagePoster = findViewById(R.id.image_poster);
        progressBar = findViewById(R.id.progress_bar);

        ViewModelFactory factory = ViewModelFactory.getInstance(this);
        DetailFilmViewModel viewModel = new ViewModelProvider(this, factory).get(DetailFilmViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String movieId = extras.getString(EXTRA_FILM);
            String tvId = extras.getString(EXTRA_TV);
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId);
                populateFilm(viewModel.getMovie());
            } else if (tvId != null){
                viewModel.setSelectedTv(tvId);
                populateFilm(viewModel.getTv());
            }
        }
    }

    private void populateFilm(FilmEntity filmEntity) {
        textTitle.setText(filmEntity.getTitle());
        textDesc.setText(filmEntity.getDescription());
        textDate.setText(getResources().getString(R.string.release, filmEntity.getReleaseDate()));

        Glide.with(this)
                .load(filmEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imagePoster);
    }

}
