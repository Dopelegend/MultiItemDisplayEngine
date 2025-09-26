package org.dopelegend.multiItemDisplayEngine.ItemDisplay.Utils.ItemDisplayGroups;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ItemDisplay;
import org.dopelegend.multiItemDisplayEngine.Rotation.Rotate2D;
import org.dopelegend.multiItemDisplayEngine.Rotation.Rotate3D;

import java.util.List;

/**
 * A group of ItemDisplays that are connected, that way you can move and rotate them around a center without their relative position to each other breaking.
 */
public class ItemDisplayGroup {

    //Class variables
    Location centerOfRotation;
    List<ItemDisplay> itemDisplayList;
    String name;
    double yaw;
    double pitch;
    double roll;

    //Constructor
    /**
     *
     * @param centerOfGroup The location of the center which the itemDisplayGroup should be rotated around.
     * @param itemDisplays The List of itemDisplays that make up the itemDisplayGroup
     * @param name The name of the ItemDisplayGroup, this is used primarily for ingame commands and to find this class instance later,
     *            the name is also used in the data file, to save the ItemDisplayGroup.
     */
    public ItemDisplayGroup(Location centerOfGroup, List<ItemDisplay> itemDisplays, String name) {
        if (centerOfGroup == null || itemDisplays == null || itemDisplays.isEmpty() || name == null || name.isEmpty()) {
            System.err.println("Display groups cannot be intialized with null or empty values");
            return;
        }
        this.centerOfRotation = centerOfGroup;
        this.itemDisplayList = itemDisplays;
        this.name = name;
        this.yaw = 0;
        this.pitch = 0;
        this.roll = 0;
    }

    public double GetYaw() {
        return this.yaw;
    }

    public double GetPitch() {
        return this.pitch;
    }

    public double GetRoll() {
        return this.roll;
    }

    public void AddRotation2D(double yaw, int ticks) {
        for (ItemDisplay itemDisplay : itemDisplayList) {
            itemDisplay.setTeleportDuration(ticks);
            itemDisplay.teleport(Rotate2D.AddRotation(yaw, this.centerOfRotation, itemDisplay.getLocation().clone()));
        }
    }

    public void SetRotation2D(double yaw, int ticks) {
        for (ItemDisplay itemDisplay : itemDisplayList) {
            itemDisplay.setTeleportDuration(ticks);
            itemDisplay.teleport(Rotate2D.SetRotation(yaw, this.centerOfRotation, itemDisplay.getLocation().clone(), 0));
        }
    }

    public void SetRotation3D(double yaw, double pitch, double roll, int ticks) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.roll = roll;

        for (ItemDisplay itemDisplay : itemDisplayList) {
            itemDisplay.setTeleportDuration(ticks);
            itemDisplay.teleport(Rotate3D.rotateAroundCenter(itemDisplay, this.centerOfRotation, yaw, pitch, roll));
        }
    }
}
