package com.example.lessonmovie.net;

import com.example.lessonmovie.dto.MovieInfo;
import com.example.lessonmovie.dto.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("movie/popular")
    Call<MovieList> getPopularMovie(
            @Query("page") int page);

    @GET("movie/{id}")
    Call<MovieInfo> getMovie(
            @Path("id") long id);
/*
   @GET("movie/{id}")

   @Path("id")

   @GET("movie/{id}/credits")
   @Path("id")*/
}
