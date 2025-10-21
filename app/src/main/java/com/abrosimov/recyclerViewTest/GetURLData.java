package com.abrosimov.recyclerViewTest;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GetURLData {

    private final TextView resultInfo;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public interface OnDataLoadedListener {
        void onDataLoaded(List<Game> games); // когда данные успешно получены
        void onError(String message);        // если произошла ошибка
    }

    public GetURLData(TextView resultInfo) {
        this.resultInfo = resultInfo;
    }

    public void execute(String urlString, OnDataLoadedListener listener) {
        resultInfo.setText("Загрузка данных...");

        executor.execute(() -> {
            String result = getDataFromUrl(urlString);
            List<Game> gameNames = new ArrayList<>();

            handler.post(() -> {
                if (result == null) {
                    resultInfo.setText("Ошибка: не удалось получить данные");
                    listener.onError("Ошибка загрузки");
                    return;
                }

                try {
                    JSONObject json = new JSONObject(result);
                    JSONObject response = json.getJSONObject("response");

                    int gameCount = response.getInt("game_count");
                    JSONArray games = response.getJSONArray("games");

                    for (int i = 0; i < games.length(); i++) {
                        JSONObject game = games.getJSONObject(i);
                        String name = game.getString("name");
                        gameNames.add(new Game(name));
                    }

                    resultInfo.setText("Количество игр: " + gameCount);
                    listener.onDataLoaded(gameNames);

                } catch (JSONException e) {
                    resultInfo.setText("Ошибка обработки JSON");
                    listener.onError("Ошибка парсинга");
                    e.printStackTrace();
                }
            });
        });
    }

    private String getDataFromUrl(String urlString) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder buffer = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null)
                buffer.append(line).append("\n");

            return buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        } finally {
            if (connection != null) connection.disconnect();
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
