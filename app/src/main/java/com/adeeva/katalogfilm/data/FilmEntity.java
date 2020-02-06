package com.adeeva.katalogfilm.data;

public class FilmEntity {
    private String filmId;
    private String title;
    private String description;
    private String releaseDate;
    private String imagePath;

    public FilmEntity(String filmId, String title, String description, String releaseDate, String imagePath) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.imagePath = imagePath;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String mImagePath) {
        this.imagePath = mImagePath;
    }
}
