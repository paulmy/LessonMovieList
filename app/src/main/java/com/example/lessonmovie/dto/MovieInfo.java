package com.example.lessonmovie.dto;

import com.google.gson.annotations.SerializedName;

public class MovieInfo {
    @SerializedName("backdrop_path")
    public String photoUrl;
    @SerializedName("poster_path")
    public String posterUrl;
    @SerializedName("title")
    public String title;
    @SerializedName("overview")
    public String description;
    @SerializedName("release_date")
    public String data;
    @SerializedName("runtime")
    public long runtime;
    @SerializedName("revenue")
    public String revenue;
    @SerializedName("rating")
    public float rating;

}
