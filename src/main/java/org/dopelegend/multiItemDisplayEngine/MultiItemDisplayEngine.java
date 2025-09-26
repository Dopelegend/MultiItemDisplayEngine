    package org.dopelegend.multiItemDisplayEngine;

    import org.bukkit.Bukkit;
    import org.bukkit.Location;
    import org.bukkit.Material;
    import org.bukkit.World;
    import org.bukkit.entity.EntityType;
    import org.bukkit.entity.ItemDisplay;
    import org.bukkit.inventory.ItemStack;
    import org.bukkit.plugin.java.JavaPlugin;
    import org.dopelegend.multiItemDisplayEngine.ItemDisplay.Utils.ItemDisplayGroups.ItemDisplayGroup;

    import java.util.List;

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

            World world = Bukkit.getWorlds().getFirst();
            Location centerLoc = new Location(world, 0, 100, 0);
            ItemDisplay baseItemDisplay = (ItemDisplay) world.spawnEntity(centerLoc.clone().add(0, 0, 1), EntityType.ITEM_DISPLAY);
            baseItemDisplay.setItemStack(new ItemStack(Material.STONE));

            List<ItemDisplay> itemDisplays = List.of(
                    baseItemDisplay,
                    (ItemDisplay) baseItemDisplay.copy(centerLoc.clone().add(1, 0, 0)),
                    (ItemDisplay) baseItemDisplay.copy(centerLoc.clone().add(1, 0, 1)),
                    (ItemDisplay) baseItemDisplay.copy(centerLoc.clone().add(-1, 0, 0)),
                    (ItemDisplay) baseItemDisplay.copy(centerLoc.clone().add(0, 0, -1)),
                    (ItemDisplay) baseItemDisplay.copy(centerLoc.clone().add(-1, 0, -1)),
                    (ItemDisplay) baseItemDisplay.copy(centerLoc.clone().add(1, 0, -1)),
                    (ItemDisplay) baseItemDisplay.copy(centerLoc.clone().add(-1, 0, 1))
            );
            String name = "MultiItemDisplay";
            ItemDisplayGroup itemDisplayGroup = new ItemDisplayGroup(centerLoc, itemDisplays, name);


            Bukkit.getScheduler().runTaskTimer(this, () -> {
                itemDisplayGroup.SetRotation3D(1, 0, 0, 0);
            }, 10,  2);
        }

        @Override
        public void onDisable() {
            // Plugin shutdown logic
        }
    }
