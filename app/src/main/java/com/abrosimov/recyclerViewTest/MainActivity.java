package com.abrosimov.recyclerViewTest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar mainProgressBar = findViewById(R.id.MainProgressBar);
        TextView textView = findViewById(R.id.textView);
        RecyclerView recyclerView = findViewById(R.id.myRecyclerGames);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RequestSteamAPI api = new RequestSteamAPI();
        ParsingJsonSteam parsingJsonSteam = new ParsingJsonSteam();

        List<SteamAccountGame> steamAccountGames = new ArrayList<>();

        mainProgressBar.setVisibility(View.VISIBLE);

        //Добро пожаловать в callBack
        api.getDataFromUrl("https://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=0150E8C6A4D3994DBB9434338B829360&steamid=76561198844497951&format=json&include_appinfo=1",
                new RequestSteamAPI.ResponseCallback() {
                    @Override
                    public void onSuccess(String answer) {
                        runOnUiThread(() -> {
                            mainProgressBar.setVisibility(View.GONE);
                            try {
                                List<String> nameGames = parsingJsonSteam.getNameGames(answer);
                                List<Integer> appsId = parsingJsonSteam.getAppsId(answer);
                                List<String> iconsUrl = parsingJsonSteam.getIconsUrl(answer);


                                // Заполняем класс SteamAccountGame
                                for (int i = 0; i < nameGames.size(); i++) {
                                    steamAccountGames.add(new SteamAccountGame(
                                            appsId.get(i),
                                            nameGames.get(i),
                                            iconsUrl.get(i)));

                                }


                                GameAdapter gameAdapter = new GameAdapter(steamAccountGames);
                                recyclerView.setAdapter(gameAdapter);

                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {
                        textView.setVisibility(View.VISIBLE);
                        textView.setText("Ошибка: " + e.getMessage());
                    }
                });

    }
}