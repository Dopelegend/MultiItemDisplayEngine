    package org.dopelegend.multiItemDisplayEngine;

    import org.bukkit.plugin.Plugin;
    import org.bukkit.plugin.java.JavaPlugin;
    import org.dopelegend.multiItemDisplayEngine.commands.CommandListener;
    import org.dopelegend.multiItemDisplayEngine.files.generate.FileStructure;

    import java.io.File;

    public final class MultiItemDisplayEngine extends JavaPlugin {

        public static Plugin plugin;

        @Override
        public void onEnable() {
            FileStructure.generateEntireFileStructure();
            plugin = this;
            new CommandListener(this);
        }

        @Override
        public void onDisable() {
            // Plugin shutdown logic
        }
    }
