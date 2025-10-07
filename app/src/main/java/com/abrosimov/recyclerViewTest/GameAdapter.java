package com.abrosimov.recyclerViewTest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewHolder> {
    private final List<Game> games;
    GameAdapter(List<Game> games) {
        this.games = games;
    }

     static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textItem;
        ImageView gameIcon; //опа
        MyViewHolder(View itemView) {
            super(itemView);
            textItem = itemView.findViewById(R.id.gameText);
            gameIcon = itemView.findViewById(R.id.gameIcon); //оп
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
        holder.gameIcon.setImageResource(games.get(position).getIdIcon());

        /*// Обработка клика
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), games.get(position), Toast.LENGTH_SHORT).show();
        });*/
    }

    @Override
    public int getItemCount() {
        return games.size();
    }
}