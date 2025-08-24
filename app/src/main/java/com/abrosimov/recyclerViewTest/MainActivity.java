package com.abrosimov.recyclerViewTest;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> games = Arrays.asList("Minecraft", "Valorant", "GTA V", "Skyrim", "Hollow Knight");

        RecyclerView recyclerView = findViewById(R.id.myRecyclerGames);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GameAdapter gameAdapter = new GameAdapter(games);
        recyclerView.setAdapter(gameAdapter);
    }
}