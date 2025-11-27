package com.abrosimov.recyclerViewTest;

public class SteamAccountGame {
    private int appid;
    private String name;
    private String imgIconUrl;

    SteamAccountGame(int appId, String name, String imgIconUrl) {
        this.appid = appId;
        this.name = name;
        this.imgIconUrl = imgIconUrl;
    }

    public String getName() {
        return name;
    }

    public int getAppid() {
        return appid;
    }

    public String getImgIconUrl() {
        return imgIconUrl;
    }

    public String getFullIconUrl() {
        return "http://media.steampowered.com/steamcommunity/public/images/apps/"
                + appid + "/" + imgIconUrl + ".jpg";
    }
}
