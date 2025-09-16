package org.dopelegend.multiItemDisplayEngine;

import org.bukkit.plugin.java.JavaPlugin;

public final class MultiItemDisplayEngine extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // 3d rotation test
//        Location centerLoc = new Location(Bukkit.getWorlds().getFirst(), 0, 100, 0);
//        Location displaySpawnLoc = centerLoc.clone().add(0, 0, 5);
//        ItemDisplay itemDisplay = (ItemDisplay) displaySpawnLoc.getWorld().spawnEntity(displaySpawnLoc, EntityType.ITEM_DISPLAY);
//        itemDisplay.setItemStack(new ItemStack(Material.STONE));
//
//        Bukkit.getScheduler().runTaskTimer(this, () -> {
//            itemDisplay.setTeleportDuration(5);
//            itemDisplay.teleport(Rotate3D.AddRotation(Math.toRadians(2), Math.toRadians(2), centerLoc, displaySpawnLoc));
//        }, 0, 5);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
