package com.adeeva.katalogfilm.ui.detail;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.adeeva.katalogfilm.R;
import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;
import com.adeeva.katalogfilm.data.source.local.entity.TvEntity;
import com.adeeva.katalogfilm.viewmodel.ViewModelFactory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailFilmActivity extends AppCompatActivity {

    public static final String EXTRA_FILM = "extra_film";
    public static final String EXTRA_TV = "extra_tv";
    private TextView textTitle;
    private TextView textDesc;
    private TextView textDate;
    private ImageView imagePoster;
    private ProgressBar progressBar;
    DetailFilmViewModel viewModel;
    private Menu menu;

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
        viewModel = new ViewModelProvider(this, factory).get(DetailFilmViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String movieId = extras.getString(EXTRA_FILM);
            String tvId = extras.getString(EXTRA_TV);
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId);

                viewModel.filmDetail.observe(this, filmWithDetail -> {
                    if (filmWithDetail != null) {
                        switch (filmWithDetail.status) {
                            case LOADING:
                                progressBar.setVisibility(View.VISIBLE);
                                break;

                            case SUCCESS:
                                if (filmWithDetail.data != null) {
                                    progressBar.setVisibility(View.GONE);
                                    populateFilm(filmWithDetail.data);
                                }
                                break;

                            case ERROR:
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                //progressBar.setVisibility(View.VISIBLE);
                //viewModel.getMovie().observe(this, this::populateFilm);
            } else if (tvId != null) {
                viewModel.setSelectedTv(tvId);

                viewModel.tvDetail.observe(this, tvWithDetail -> {
                    if (tvWithDetail != null){
                        switch (tvWithDetail.status){
                            case LOADING:
                                progressBar.setVisibility(View.VISIBLE);
                                break;

                            case SUCCESS:
                                if (tvWithDetail.data != null){
                                    progressBar.setVisibility(View.GONE);
                                    populateTv(tvWithDetail.data);
                                }
                                break;

                            case ERROR:
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                //progressBar.setVisibility(View.VISIBLE);
                //viewModel.getTv().observe(this, this::populateFilm);
            }
        }
    }

    private void populateFilm(FilmEntity filmEntity) {
        //progressBar.setVisibility(View.GONE);
        textTitle.setText(filmEntity.getTitle());
        textDesc.setText(filmEntity.getDescription());
        textDate.setText(getResources().getString(R.string.release, filmEntity.getReleaseDate()));

        Glide.with(this)
                .load(filmEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imagePoster);
    }

    private void populateTv(TvEntity filmEntity) {
        //progressBar.setVisibility(View.GONE);
        textTitle.setText(filmEntity.getTitle());
        textDesc.setText(filmEntity.getDescription());
        textDate.setText(getResources().getString(R.string.release, filmEntity.getReleaseDate()));

        Glide.with(this)
                .load(filmEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imagePoster);
    }

}
