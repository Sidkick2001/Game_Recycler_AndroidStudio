package com.abrosimov.recyclerViewTest;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestSteamAPI {
    private final OkHttpClient client = new OkHttpClient();

    public String getDataFromUrl (String URL) throws IOException {
        Request request = new Request.Builder()
                    .url(URL)
                    .build();

        try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Ошибка запроса: " + response);
                }
                return response.body().string(); // Возвращаем тело ответа как строку
            }
        }
}