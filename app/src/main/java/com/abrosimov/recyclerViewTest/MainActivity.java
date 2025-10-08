package com.abrosimov.recyclerViewTest;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Game> games = new ArrayList<>();
        GameRepository gameRepository = new GameRepository();



        RecyclerView recyclerView = findViewById(R.id.myRecyclerGames);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GameAdapter gameAdapter = new GameAdapter(gameRepository.getGames());
        recyclerView.setAdapter(gameAdapter);
    }
}