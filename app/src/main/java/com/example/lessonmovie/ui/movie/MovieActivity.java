package com.example.lessonmovie.ui.movie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lessonmovie.databinding.ActivityMovieBinding;
import com.example.lessonmovie.ui.root.MainActivity;

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
        binding.name.setText(id + "");
    }

    public static Intent newIntent(Context context, long id) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(ARG_ID, id);
        return intent;
    }
}