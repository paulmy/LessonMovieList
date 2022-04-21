package com.example.lessonmovie;

import android.app.Application;

import com.example.lessonmovie.net.MovieApi;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    public static MovieApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        api = createRetrofit().create(MovieApi.class);

    }

    private Retrofit createRetrofit() {
    return  new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(getGson()))
            .client(getClient())
            .build();
    }
    private Gson getGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
    }



    private OkHttpClient getClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(new Cache(getApplicationContext().getCacheDir(), 10L * 1024 * 1024));
        Interceptor interceptor = chain -> {
            Request request = chain.request();
            HttpUrl newUrl = request
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key", "2e774b038b2dc15a1db7397f1b6b63a7")
                    .build();
            return chain.proceed(request.newBuilder().url(newUrl).build());
        };
        return client.addInterceptor(interceptor).build();

    }
}
