package org.dopelegend.multiItemDisplayEngine;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MultiItemDisplayEngine extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendRichMessage("<Green>MultiItemDisplayEngine has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
