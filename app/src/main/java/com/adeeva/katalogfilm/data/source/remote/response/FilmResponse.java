package com.adeeva.katalogfilm.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class FilmResponse implements Parcelable {

    private String id;
    private String title;
    private String description;
    private String releaseDate;
    private String imagePath;

    public FilmResponse() {
    }

    public FilmResponse(String id, String title, String description, String releaseDate, String imagePath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.imagePath = imagePath;
    }

    private FilmResponse(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        releaseDate = in.readString();
        imagePath = in.readString();
    }

    public static final Creator<FilmResponse> CREATOR = new Creator<FilmResponse>() {
        @Override
        public FilmResponse createFromParcel(Parcel in) {
            return new FilmResponse(in);
        }

        @Override
        public FilmResponse[] newArray(int size) {
            return new FilmResponse[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(releaseDate);
        parcel.writeString(imagePath);
    }
}
