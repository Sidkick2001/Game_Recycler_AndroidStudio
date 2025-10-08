package com.abrosimov.recyclerViewTest;

import java.util.ArrayList;
import java.util.List;

public class GameRepository {
    public List<Game> getGames() {
        List<Game> games = new ArrayList<>();
        games.add(new Game("Minecraft", R.drawable.minecraft_icon));
        games.add(new Game("Valorant", R.drawable.valorant_icon));
        games.add(new Game("Fortnite", R.drawable.fortnite_icon));
        games.add(new Game("Genshin Impact", R.drawable.genshin_icon));
        games.add(new Game("Apex Legends", R.drawable.apex_icon));
        games.add(new Game("PUBG Mobile", R.drawable.pubg_icon));
        games.add(new Game("League of Legends", R.drawable.lol_icon));
        games.add(new Game("Dota 2", R.drawable.dota2_icon));
        games.add(new Game("Counter-Strike 2", R.drawable.cs2_icon));
        games.add(new Game("Overwatch 2", R.drawable.overwatch_icon));
        games.add(new Game("The Witcher 3", R.drawable.witcher_icon));
        games.add(new Game("Cyberpunk 2077", R.drawable.cyberpunk_icon));
        games.add(new Game("Elden Ring", R.drawable.eldenring_icon));
        games.add(new Game("Red Dead Redemption 2", R.drawable.rdr2_icon));
        games.add(new Game("Grand Theft Auto V", R.drawable.gta5_icon));
        games.add(new Game("Assassin’s Creed Valhalla", R.drawable.ac_valhalla_icon));
        games.add(new Game("Call of Duty: Warzone", R.drawable.warzone_icon));
        games.add(new Game("Battlefield 2042", R.drawable.battlefield_icon));
        games.add(new Game("Terraria", R.drawable.terraria_icon));
        games.add(new Game("Stardew Valley", R.drawable.stardew_icon));
        games.add(new Game("Among Us", R.drawable.amongus_icon));
        games.add(new Game("Hollow Knight", R.drawable.hollowknight_icon));
        games.add(new Game("Celeste", R.drawable.celeste_icon));
        games.add(new Game("Dark Souls III", R.drawable.darksouls3_icon));
        games.add(new Game("Sekiro: Shadows Die Twice", R.drawable.sekiro_icon));
        games.add(new Game("Resident Evil 4 Remake", R.drawable.re4_icon));
        games.add(new Game("Dead by Daylight", R.drawable.dbd_icon));
        games.add(new Game("Rainbow Six Siege", R.drawable.r6_icon));
        games.add(new Game("Rocket League", R.drawable.rocketleague_icon));
        games.add(new Game("The Sims 4", R.drawable.sims4_icon));

        return games;
    }
}
