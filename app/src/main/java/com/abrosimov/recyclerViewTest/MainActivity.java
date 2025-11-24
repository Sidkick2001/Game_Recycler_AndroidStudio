package com.abrosimov.recyclerViewTest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        AtomicBoolean flag = new AtomicBoolean();
        flag.set(false);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        //Пытаемся получить ответ в потоке
        executor.execute(() -> {
            RequestSteamAPI requestSteamAPI = new RequestSteamAPI();

            try {
                String result = requestSteamAPI.getDataFromUrl("https://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=0150E8C6A4D3994DBB9434338B829360&steamid=76561198844497951&format=json&include_appinfo=1");
                flag.set(true);

                handler.post(() -> {
                    Log.d("API_RESULT", result);
                    Log.d("API_FLAG", String.valueOf(flag.get()));
                });
            } catch (IOException e) {
                handler.post(() -> {
                    Log.e("API_ERROR", "Ошибка при получении данных", e);
                });
            }
        });




    /*    //Настройка recyclerView
        RecyclerView recyclerView = findViewById(R.id.myRecyclerGames);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GameAdapter gameAdapter = new GameAdapter(steamGames);
        recyclerView.setAdapter(gameAdapter);
    }*/
    }
}