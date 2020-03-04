package com.adeeva.katalogfilm.ui.tvshow;

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
import com.adeeva.katalogfilm.data.source.local.entity.TvEntity;
import com.adeeva.katalogfilm.ui.detail.DetailFilmActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class TvShowAdapter extends PagedListAdapter<TvEntity, TvShowAdapter.TvShowViewHolder> {

    TvShowAdapter() {
        super(DIFF_CALLBACK);
    }

    private static DiffUtil.ItemCallback<TvEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TvEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull TvEntity oldItem, @NonNull TvEntity newItem) {
                    return oldItem.getFilmId().equals(newItem.getFilmId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TvEntity oldItem, @NonNull TvEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };


    /*private List<TvEntity> listFilms = new ArrayList<>();

    void setFilms(List<TvEntity> listFilms) {
        if (listFilms == null) return;
        this.listFilms.clear();
        this.listFilms.addAll(listFilms);
    }
     */

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_film, parent, false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        TvEntity film = getItem(position);
        if (film != null) {
            holder.bind(film);
        }
    }

    //@Override
    //public int getItemCount() {
    //    return listFilms.size();
    //}

    class TvShowViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;

        TvShowViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }

        void bind(TvEntity film) {
            tvTitle.setText(film.getTitle());
            tvDescription.setText(film.getDescription());
            tvDate.setText(itemView.getResources().getString(R.string.release, film.getReleaseDate()));
            Glide.with(itemView.getContext())
                    .load(film.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgPoster);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(itemView.getContext(), DetailFilmActivity.class);
                intent.putExtra(DetailFilmActivity.EXTRA_TV, film.getFilmId());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
