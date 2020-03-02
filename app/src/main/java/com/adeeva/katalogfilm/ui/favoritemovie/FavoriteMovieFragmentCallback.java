package com.adeeva.katalogfilm.ui.favoritemovie;

import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;

interface FavoriteMovieFragmentCallback {
    void onShareClick(FilmEntity film);
}
