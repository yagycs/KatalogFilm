package com.adeeva.katalogfilm.utils;

import android.content.Context;

import com.adeeva.katalogfilm.data.source.remote.response.FilmResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    private Context context;

    public JsonHelper(Context context) {
        this.context = context;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private String parsingFileToString(String fileName) {
        try {
            InputStream is = context.getAssets().open(fileName);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<FilmResponse> loadMovies() {
        ArrayList<FilmResponse> list = new ArrayList<>();
        try {
            String json = parsingFileToString("MovieResponses.json");
            if (json != null) {
                JSONObject responseObject = new JSONObject(json);
                JSONArray listArray = responseObject.getJSONArray("results");
                for (int i = 0; i < listArray.length(); i++) {
                    JSONObject movie = listArray.getJSONObject(i);

                    String id = movie.getString("id");
                    String title = movie.getString("title");
                    String description = movie.getString("description");
                    String releaseDate = movie.getString("release_date");
                    String imagePath = movie.getString("imagePath");

                    FilmResponse filmResponse = new FilmResponse(id, title, description, releaseDate, imagePath);
                    list.add(filmResponse);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<FilmResponse> loadTvs() {
        ArrayList<FilmResponse> list = new ArrayList<>();
        try {
            String json = parsingFileToString("TvShowResponses.json");
            if (json != null) {
                JSONObject responseObject = new JSONObject(json);
                JSONArray listArray = responseObject.getJSONArray("results");
                for (int i = 0; i < listArray.length(); i++) {
                    JSONObject movie = listArray.getJSONObject(i);

                    String id = movie.getString("id");
                    String title = movie.getString("title");
                    String description = movie.getString("description");
                    String releaseDate = movie.getString("release_date");
                    String imagePath = movie.getString("imagePath");

                    FilmResponse filmResponse = new FilmResponse(id, title, description, releaseDate, imagePath);
                    list.add(filmResponse);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
