package com.example.lessonmovie.ui.root;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lessonmovie.R;
import com.example.lessonmovie.databinding.ItemBinding;
import com.example.lessonmovie.dto.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoveListAdapter extends RecyclerView.Adapter<MoveListAdapter.ViewHolder> {
    private OnItemClickListener onClickListener = null;
    private ArrayList<MovieModel> data = new ArrayList<>();

    public void setOnItemListener(OnItemClickListener listener) {
        onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBinding binding = ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position),onClickListener);
    }

    public void setItems(List<MovieModel> newItems) {
        data.clear();
        data.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemBinding binding;

        public ViewHolder(@NonNull ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(MovieModel item, OnItemClickListener listener) {
            Picasso.get()
                    .load("https://themoviedb.org/t/p/w300" + item.imageUrl)
                    .placeholder(R.drawable.ic_download)
                    .error(R.drawable.ic_error)
                    .into(binding.poster);
            //TODO: add image

            binding.date.setText(item.date);
            binding.name.setText(item.name);
            binding.description.setText(item.description);
            binding.getRoot().setOnClickListener(v->listener.onClick(item));
            setVisibleOrGone(binding.date, item.date);
            setVisibleOrGone(binding.name, item.name);
            setVisibleOrGone(binding.description, item.description);

        }

        private static void setVisibleOrGone(@NonNull View v, Object o) {
            if (o == null) {
                v.setVisibility(View.GONE);
            } else {
                v.setVisibility(View.VISIBLE);
            }
        }
    }


}
