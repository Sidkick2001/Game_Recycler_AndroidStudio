package com.abrosimov.recyclerViewTest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar mainProgressBar = findViewById(R.id.MainProgressBar);
        TextView textView = findViewById(R.id.textView);

        RequestSteamAPI api = new RequestSteamAPI();

        mainProgressBar.setVisibility(View.VISIBLE);

        //Добро пожаловать в callBack
        api.getDataFromUrl("https://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=0150E8C6A4D3994DBB9434338B829360&steamid=76561198844497951&format=json&include_appinfo=1",
                new RequestSteamAPI.ResponseCallback() {
                    @Override
                    public void onSuccess(String answer) {
                        runOnUiThread(() -> {
                            mainProgressBar.setVisibility(View.GONE);
                            try {
                                JSONObject json = new JSONObject(answer);
                                JSONObject response = json.getJSONObject("response");
                                JSONArray games = response.getJSONArray("games");
                                String game = games.getString(0);

                                Log.d("gameJson", game);

                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {
                        textView.setText("Ошибка: " + e.getMessage());
                    }
                });




         //Когда получу ответ

        /*Log.d("ResponseServer", requestSteamAPI.getAnswer());*/









    /*    //Настройка recyclerView
        RecyclerView recyclerView = findViewById(R.id.myRecyclerGames);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GameAdapter gameAdapter = new GameAdapter(steamGames);
        recyclerView.setAdapter(gameAdapter);
    }*/
    }
}