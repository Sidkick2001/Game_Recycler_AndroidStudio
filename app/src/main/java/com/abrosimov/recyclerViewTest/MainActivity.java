package com.abrosimov.recyclerViewTest;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

        List<Game> steamGames = new ArrayList<>();

        TextView textView = findViewById(R.id.textView);

        GetURLData getURLData = new GetURLData(textView);


        getURLData.execute(
                "https://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=0150E8C6A4D3994DBB9434338B829360&steamid=76561198844497951&format=json&include_appinfo=1",
                new GetURLData.OnDataLoadedListener() {
                    @Override
                    public void onDataLoaded(List<Game> games) {
                        // Здесь список уже готов 🎉
                        Log.d("Steam", "Загружено игр: " + games.size());
                        for (Game g : games) {
                            Log.d("Steam", g.getName());
                            steamGames.add(g);
                        }
                    }

                    @Override
                    public void onError(String message) {
                        Log.e("Steam", "Ошибка: " + message);
                    }
                }
        );

        Log.d("Steam", "Объем steamGames:" + steamGames.size());


        RecyclerView recyclerView = findViewById(R.id.myRecyclerGames);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GameAdapter gameAdapter = new GameAdapter(steamGames);
        recyclerView.setAdapter(gameAdapter);
    }
}