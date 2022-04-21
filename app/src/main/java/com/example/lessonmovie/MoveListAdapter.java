package com.example.lessonmovie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lessonmovie.databinding.ItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoveListAdapter extends RecyclerView.Adapter<MoveListAdapter.ViewHolder> {
    private ArrayList<MovieModel> data = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBinding binding = ItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
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

        public void bind(MovieModel item) {
            Picasso.get()
                    .load( "https://themoviedb.org/t/p/w300"+item.getImageUrl())
                    .into(binding.poster);
            //TODO: add image

            binding.date.setText(item.getDate());
            binding.name.setText(item.getName());
            binding.description.setText(item.getDescription());
           setVisibleOrGone(binding.date,item.getDate());
           setVisibleOrGone(binding.name,item.getName());
            setVisibleOrGone(binding.description,item.getDescription());
        }
        private static void setVisibleOrGone(@NonNull View v,Object o){
            if(o==null){
                v.setVisibility(View.GONE);
            }else{
               v.setVisibility(View.VISIBLE);
            }
        }
    }


}
