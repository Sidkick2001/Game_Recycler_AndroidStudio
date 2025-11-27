package com.abrosimov.recyclerViewTest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewHolder> {
    private final List<SteamAccountGame> games;

    GameAdapter(List<SteamAccountGame> games) {
        this.games = games;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textItem;
        ImageView gameIcon;

        MyViewHolder(View itemView) {
            super(itemView);
            textItem = itemView.findViewById(R.id.gameText);
            gameIcon = itemView.findViewById(R.id.gameIcon);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textItem.setText(games.get(position).getName());
        Picasso.get()
                .load(games.get(position).getFullIconUrl())
                .into(holder.gameIcon);



        /*Обработка клика*//*
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), games.get(position), Toast.LENGTH_SHORT).show();
        });*/

    }

    @Override
    public int getItemCount() {
        return games.size();
    }
}
