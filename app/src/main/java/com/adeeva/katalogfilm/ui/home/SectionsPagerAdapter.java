package com.adeeva.katalogfilm.ui.home;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.adeeva.katalogfilm.R;
import com.adeeva.katalogfilm.ui.movie.FavoriteMovieFragment;
import com.adeeva.katalogfilm.ui.movie.MovieFragment;
import com.adeeva.katalogfilm.ui.tvshow.FavoriteTvFragment;
import com.adeeva.katalogfilm.ui.tvshow.TvShowFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.movies, R.string.tvs, R.string.favorite_movies, R.string.favorite_tvs};
    private final Context mContext;

    SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MovieFragment();
            case 1:
                return new TvShowFragment();
            case 2:
                return new FavoriteMovieFragment();
            case 3:
                return new FavoriteTvFragment();
            default:
                return new Fragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
