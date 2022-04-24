package com.example.lessonmovie.ui.movie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lessonmovie.App;
import com.example.lessonmovie.R;
import com.example.lessonmovie.databinding.ActivityMovieBinding;
import com.example.lessonmovie.dto.MovieInfo;
import com.example.lessonmovie.ui.root.MainActivity;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {
    private ActivityMovieBinding binding;
    private final static String ARG_ID = "arg_id";
    private long id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        id = getIntent().getLongExtra(ARG_ID, 0);
        loadMovie(id); // add LoadMovie(id);
        // binding.name.setText(id + "");

    }

    private void loadMovie(long id) {
        App.api.getMovie(id).enqueue(new Callback<MovieInfo>() {
            @Override
            public void onResponse(Call<MovieInfo> call, Response<MovieInfo> response) {
                if (response.code() == 200) {
                    assert response.body() != null;
                    bindMovie(response.body()); //add bindMovie(response.body());
                } else {
                    Toast.makeText(MovieActivity.this,
                            response.message(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MovieInfo> call, Throwable t) {

            }
        });

    }

    private void bindMovie(MovieInfo info) {
        binding.name.setText(info.title);
        binding.date.setText(info.data);
        binding.description.setText(info.description);
        binding.revenue.setText(info.revenue);
        binding.runtime.setText(String.valueOf(info.runtime));//todo convert
        binding.vote.setText(String.valueOf(info.rating));
        Picasso.get()
                .load("https://themoviedb.org/t/p/w300" + info.posterUrl)
                .resize(96, 144)
                .centerInside()
                .placeholder(R.drawable.ic_download)
                .error(R.drawable.ic_error)
                .into(binding.poster);
        Picasso.get()
                .load("https://themoviedb.org/t/p/w780" + info.photoUrl)
                .placeholder(R.drawable.ic_download)
                .error(R.drawable.ic_error)
                .into(binding.background);

        binding.loader.setVisibility(View.GONE);
        binding.content.setVisibility(View.VISIBLE);
    }

    public static Intent newIntent(Context context, long id) {
        Intent intent = new Intent(context, MovieActivity.class);
        intent.putExtra(ARG_ID, id);
        return intent;
    }
}