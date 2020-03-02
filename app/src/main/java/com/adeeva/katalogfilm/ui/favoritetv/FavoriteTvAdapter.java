package com.adeeva.katalogfilm.ui.favoritetv;

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
import com.adeeva.katalogfilm.data.source.local.entity.TvEntity;
import com.adeeva.katalogfilm.ui.detail.DetailFilmActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class FavoriteTvAdapter extends RecyclerView.Adapter<FavoriteTvAdapter.TvViewHolder> {

    private final FavoriteTvFragmentCallback callback;
    private ArrayList<TvEntity> listTvs = new ArrayList<>();

    FavoriteTvAdapter(FavoriteTvFragmentCallback callback) {
        this.callback = callback;
    }

    void setTv(List<TvEntity> tvs) {
        if (tvs == null) return;
        this.listTvs.clear();
        this.listTvs.addAll(tvs);
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_favorite, parent, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, int position) {
        TvEntity tv = listTvs.get(position);
        holder.bind(tv);
    }

    @Override
    public int getItemCount() {
        return listTvs.size();
    }

    public class TvViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;
        final ImageView imgShare;

        public TvViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            imgPoster = itemView.findViewById(R.id.img_poster);
            imgShare = itemView.findViewById(R.id.img_share);
        }

        void bind(TvEntity tv){
            tvTitle.setText(tv.getTitle());
            tvDescription.setText(tv.getDescription());
            tvDate.setText(itemView.getResources().getString(R.string.release, tv.getReleaseDate()));
            Glide.with(itemView.getContext())
                    .load(tv.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgPoster);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(itemView.getContext(), DetailFilmActivity.class);
                intent.putExtra(DetailFilmActivity.EXTRA_TV, tv.getFilmId());
                itemView.getContext().startActivity(intent);
            });

            imgShare.setOnClickListener(v -> callback.onShareClick(tv));
            Glide.with(itemView.getContext())
                    .load(tv.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgPoster);
        }
    }
}
