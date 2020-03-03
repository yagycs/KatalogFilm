package com.adeeva.katalogfilm.data.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tventities")
public class TvEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "filmId")
    private String filmId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "releaseDate")
    private String releaseDate;

    @ColumnInfo(name = "favorited")
    private boolean favorited = false;

    @ColumnInfo(name = "imagePath")
    private String imagePath;

    public TvEntity(String filmId, String title, String description, String releaseDate, Boolean favorited, String imagePath) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        if (favorited != null){
            this.favorited = favorited;
        }
        this.imagePath = imagePath;
    }

    @Ignore
    public TvEntity() {
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String mFilmId) {
        this.filmId = mFilmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        this.title = mTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String mDescription) {
        this.description = mDescription;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String mReleaseDate) {
        this.releaseDate = mReleaseDate;
    }

    public boolean isFavorited(){
        return favorited;
    }

    public void setFavorited(boolean mFavorited){
        this.favorited = mFavorited;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String mImagePath) {
        this.imagePath = mImagePath;
    }
}
