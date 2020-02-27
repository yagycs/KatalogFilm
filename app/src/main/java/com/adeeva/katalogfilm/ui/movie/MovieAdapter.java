package com.adeeva.katalogfilm.ui.movie;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adeeva.katalogfilm.R;
import com.adeeva.katalogfilm.data.source.local.entity.FilmEntity;
import com.adeeva.katalogfilm.ui.detail.DetailFilmActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<FilmEntity> listFilms = new ArrayList<>();

    void setFilms(List<FilmEntity> listFilms) {
        if (listFilms == null) return;
        this.listFilms.clear();
        this.listFilms.addAll(listFilms);
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_film, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        FilmEntity film = listFilms.get(position);
        holder.bind(film);
    }

    @Override
    public int getItemCount() {
        return listFilms.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            imgPoster = itemView.findViewById(R.id.img_poster);
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
        }
    }
}
