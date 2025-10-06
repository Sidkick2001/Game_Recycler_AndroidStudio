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

        List<String> games = Arrays.asList("Minecraft", "Valorant", "GTA V", "Skyrim", "Hollow Knight", "Counter-Strike 2",
                "PUBG: BATTLEGROUNDS", "Dota 2", "Naraka: Bladepoint", "Marvel Rivals", "Apex Legends", "Rust",
                "Tom Clancy's Rainbow Six Siege", "Delta Force", "War Thunder", "Baldur's Gate 3", "Warframe", "EA SPORTS FC 25",
                "Call of Duty", "Elden Ring", "Don't Starve Together", "DayZ", "Football Manager 2024", "Destiny 2", "Team Fortress 2");

        RecyclerView recyclerView = findViewById(R.id.myRecyclerGames);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GameAdapter gameAdapter = new GameAdapter(games);
        recyclerView.setAdapter(gameAdapter);
    }
}