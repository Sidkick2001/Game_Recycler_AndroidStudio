package com.abrosimov.recyclerViewTest;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Game minecraft = new Game("Minecraft", R.drawable.minecraft_icon);
        Game valorant = new Game("Valorant", R.drawable.valorant_icon);
        List<Game> games = new ArrayList<>();
        games.add(minecraft);
        games.add(valorant);


        RecyclerView recyclerView = findViewById(R.id.myRecyclerGames);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GameAdapter gameAdapter = new GameAdapter(games);
        recyclerView.setAdapter(gameAdapter);
    }
}