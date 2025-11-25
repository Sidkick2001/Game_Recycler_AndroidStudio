package com.abrosimov.recyclerViewTest;

import android.util.Log;

import java.io.IOException;
import okhttp3.*;

public class RequestSteamAPI {

    //Прописал коллбэк
    public interface ResponseCallback {
        void onSuccess(String answer);
        void onError(Exception e);
    }

    public void getDataFromUrl(String URL, ResponseCallback callback) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        callback.onError(new IOException("Ошибка: " +
                                response.code() + " " + response.message()));
                        return;
                    }
                    String answer = responseBody.string();
                    callback.onSuccess(answer);
                }
            }
        });
    }
}