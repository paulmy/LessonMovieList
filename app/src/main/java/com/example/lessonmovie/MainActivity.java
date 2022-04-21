package com.example.lessonmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.lessonmovie.databinding.ActivityMainBinding;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MoveListAdapter adapter = new MoveListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recycler.setAdapter(adapter);
        getDataFromNetwork();

    }

    private void getDataFromNetwork() {

        App.api.getPopularMovie(1).enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response.code() == 200) {
                    assert response.body() != null;
                    addToRecycler(response.body().results);
                }
                else
                    showError(response.code());
            }


            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
            Toast.makeText(MainActivity.this, "Invalid response. Error: " + t.getMessage(),
                    Toast.LENGTH_LONG).show();
            }
        
    });
    }
    private void showError(int code) {
        Toast.makeText(MainActivity.this, "Invalid response. Code: " + code,
                Toast.LENGTH_LONG).show();
    }

    private void addToRecycler(List<MovieModel> items) {
        adapter.setItems(items);
    }

}
