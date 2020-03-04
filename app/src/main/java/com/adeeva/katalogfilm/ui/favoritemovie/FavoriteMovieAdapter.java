package com.adeeva.katalogfilm.ui.favoritemovie;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.adeeva.katalogfilm.R;
import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;
import com.adeeva.katalogfilm.ui.detail.DetailFilmActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class FavoriteMovieAdapter extends PagedListAdapter<FilmEntity, FavoriteMovieAdapter.MovieViewHolder> {

    private final FavoriteMovieFragmentCallback callback;

    /*private ArrayList<FilmEntity> listMovies = new ArrayList<>();

    void setMovie(List<FilmEntity> movies) {
        if (movies == null) return;
        this.listMovies.clear();
        this.listMovies.addAll(movies);
    }

     */

    FavoriteMovieAdapter(FavoriteMovieFragmentCallback callback) {
        super(DIFF_CALLBACK);
        this.callback = callback;
    }

    private static DiffUtil.ItemCallback<FilmEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<FilmEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull FilmEntity oldItem, @NonNull FilmEntity newItem) {
                    return oldItem.getFilmId().equals(newItem.getFilmId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull FilmEntity oldItem, @NonNull FilmEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_favorite, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        FilmEntity movie = getItem(position);
        if (movie != null) {
            holder.bind(movie);
        }
    }

    //@Override
    //public int getItemCount() {
    //    return listMovies.size();
    //}

    public FilmEntity getSwipedData(int swipedPosition) {
        return getItem(swipedPosition);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;
        final ImageView imgShare;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            imgPoster = itemView.findViewById(R.id.img_poster);
            imgShare = itemView.findViewById(R.id.img_share);
        }

        void bind(FilmEntity film) {
            tvTitle.setText(film.getTitle());
            tvDescription.setText(film.getDescription());
            tvDate.setText(itemView.getResources().getString(R.string.release, film.getReleaseDate()));
            Glide.with(itemView.getContext())
                    .load(film.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgPoster);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(itemView.getContext(), DetailFilmActivity.class);
                intent.putExtra(DetailFilmActivity.EXTRA_FILM, film.getFilmId());
                itemView.getContext().startActivity(intent);
            });

            imgShare.setOnClickListener(v -> callback.onShareClick(film));
            Glide.with(itemView.getContext())
                    .load(film.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgPoster);
        }
    }
}
