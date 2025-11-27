package com.abrosimov.recyclerViewTest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParsingJsonSteam {

    List<String> getNameGames(String json) throws JSONException {
        List<String> gameList = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(json);
        JSONArray games = jsonObject.getJSONObject("response").getJSONArray("games");

        for (int i = 0; i < games.length(); i++) {
            gameList.add(games.getJSONObject(i).getString("name"));
        }

        return gameList;
    }

    List<Integer> getAppsId(String json) throws JSONException {
        List<Integer> appsId = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(json);
        JSONArray games = jsonObject.getJSONObject("response").getJSONArray("games");

        for (int i = 0; i < games.length(); i++) {
            appsId.add(games.getJSONObject(i).getInt("appid"));
        }

        return appsId;
    }

    List<String> getIconsUrl(String json) throws JSONException {
        List<String> iconUrl = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(json);
        JSONArray games = jsonObject.getJSONObject("response").getJSONArray("games");

        for (int i = 0; i < games.length(); i++) {
            iconUrl.add(games.getJSONObject(i).getString("img_icon_url"));
        }

        return iconUrl;
    }

}
