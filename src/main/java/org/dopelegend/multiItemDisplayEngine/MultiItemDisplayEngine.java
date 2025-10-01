    package org.dopelegend.multiItemDisplayEngine;

    import org.bukkit.plugin.Plugin;
    import org.bukkit.plugin.java.JavaPlugin;
    import org.dopelegend.multiItemDisplayEngine.blockBench.generator.TexturePack;
    import org.dopelegend.multiItemDisplayEngine.commands.CommandListener;
    import org.dopelegend.multiItemDisplayEngine.files.generate.FileStructure;


    public final class MultiItemDisplayEngine extends JavaPlugin {

        public static Plugin plugin;

        @Override
        public void onEnable() {
            plugin = this;
            FileStructure.generateEntireFileStructure();
            new CommandListener(this);
            TexturePack.generateTexturePack();
        }

        @Override
        public void onDisable() {
            // Plugin shutdown logic
        }
    }
