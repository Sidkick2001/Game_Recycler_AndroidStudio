package com.abrosimov.recyclerViewTest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParsingJsonSteam {


    List<String> getListGames(String json) throws JSONException {
        List<String> gameList = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(json);
        JSONArray games = jsonObject.getJSONObject("response").getJSONArray("games");

        for (int i = 0; i < games.length()/*26*/; i++) {
            gameList.add(games.getJSONObject(i).getString("name"));
        }

        return gameList;
    }
}
