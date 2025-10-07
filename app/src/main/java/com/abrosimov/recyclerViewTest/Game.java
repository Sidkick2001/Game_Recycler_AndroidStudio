package com.abrosimov.recyclerViewTest;

public class Game {
    private final String name;
    private final int idIcon;

    public Game(String name, int idIcon) {
        this.name = name;
        this.idIcon = idIcon;
    }

    public String getName() {
        return name;
    }

    public int getIdIcon() {
        return idIcon;
    }
}