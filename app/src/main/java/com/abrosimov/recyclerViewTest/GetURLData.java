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

    // TextView, в который будем выводить результат
    private final TextView resultInfo;

    // Executor — для фоновой работы (сетевые запросы нельзя делать в UI-потоке)
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    // Handler — чтобы вернуться из фонового потока обратно в UI
    private final Handler handler = new Handler(Looper.getMainLooper());

    private List<Game> gameNames = new ArrayList<>();

    // Конструктор — передаём TextView, чтобы можно было вывести результат
    public GetURLData(TextView resultInfo) {
        this.resultInfo = resultInfo;
    }

    // Основной метод — запускает запрос
    public List<Game> execute(String urlString) {
        // 1️⃣ Сообщаем пользователю, что идёт загрузка
        resultInfo.setText("Загрузка данных...");

        // 2️⃣ Запускаем сетевую операцию в фоне
        executor.execute(() -> {
            String result = getDataFromUrl(urlString);

            // 3️⃣ После завершения фоновой операции возвращаемся в главный поток
            handler.post(() -> {
                if (result == null) {
                    resultInfo.setText("Ошибка: не удалось получить данные");
                    return;
                }

                // 4️⃣ Парсим JSON
                try {
                    JSONObject json = new JSONObject(result);
                    JSONObject response = json.getJSONObject("response");

                    // Количество игр
                    int gameCount = response.getInt("game_count");

                    // Массив игр
                    JSONArray games = response.getJSONArray("games");


                    // Проходим по каждой игре
                    for (int i = 0; i < games.length(); i++) {
                        JSONObject game = games.getJSONObject(i);
                        String name = game.getString("name");
                        gameNames.add(new Game(name));
                    }

                    // 5️⃣ Выводим результат в интерфейс
                    resultInfo.setText("Количество игр: " + gameCount);

                } catch (JSONException e) {
                    resultInfo.setText("Ошибка обработки JSON");
                    e.printStackTrace();
                }
            });
        });
        return gameNames;
    }

    // Метод для загрузки данных с URL (работает в фоне)
    private String getDataFromUrl(String urlString) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            // 1️⃣ Открываем соединение
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            // 2️⃣ Читаем ответ сервера построчно
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder buffer = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null)
                buffer.append(line).append("\n");

            // 3️⃣ Возвращаем полученный JSON в виде строки
            return buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        } finally {
            // 4️⃣ Закрываем соединение и потоки
            if (connection != null) connection.disconnect();
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
