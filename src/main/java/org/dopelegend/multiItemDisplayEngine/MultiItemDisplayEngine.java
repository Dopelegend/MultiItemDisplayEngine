    package org.dopelegend.multiItemDisplayEngine;

    import org.bukkit.plugin.Plugin;
    import org.bukkit.plugin.java.JavaPlugin;
    import org.dopelegend.multiItemDisplayEngine.commands.CommandListener;

    import java.io.File;

    public final class MultiItemDisplayEngine extends JavaPlugin {

        public static File modelFolder;
        public static Plugin plugin;

        @Override
        public void onEnable() {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            plugin = this;

            modelFolder = new File(getDataFolder(), "Models");
            if (!modelFolder.exists()) {
                modelFolder.mkdirs();
            }
            new CommandListener(this);
        }

        @Override
        public void onDisable() {
            // Plugin shutdown logic
        }
    }
