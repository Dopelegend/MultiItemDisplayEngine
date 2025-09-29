package org.dopelegend.multiItemDisplayEngine.itemDisplay.utils.itemDisplayGroups;

import org.bukkit.Location;
import org.dopelegend.multiItemDisplayEngine.blockBench.Bone;
import org.dopelegend.multiItemDisplayEngine.blockBench.FileReader;
import org.dopelegend.multiItemDisplayEngine.utils.classes.Triple;

import java.io.File;

/**
 * A group of ItemDisplays that are connected, that way you can move and rotate them around a center without their relative position to each other breaking.
 */
public class ItemDisplayGroup {

    //Class variables
    private Location pivotPoint;
    private Bone rootBone;
    private double yaw;
    private double pitch;
    private double roll;

    //Constructor
    /**
     *
     * @param pivotPoint The location of the center which the itemDisplayGroup should be rotated around.
     * @param rootBone The bone at the top of the bone hiearchy, this is obviously also the bone with no parent and all other bones as children/grandchildren or any other generation under it.
     *
     */
    public ItemDisplayGroup(Location pivotPoint, Bone rootBone) {
        if (pivotPoint == null || rootBone == null) {
            throw new IllegalArgumentException("Display groups cannot be intialized with null or empty values.");
        }
        this.pivotPoint = pivotPoint;
        this.rootBone = rootBone;
        this.yaw = 0;
        this.pitch = 0;
        this.roll = 0;
    }

    /**
     *
     * @param pivotPoint The location of the center which the itemDisplayGroup should be rotated around.
     * @param modelName The bone at the top of the bone hierarchy, this is obviously also the bone with no parent and all other bones as children/grandchildren or any other generation under it.
     *
     */
    public ItemDisplayGroup(Location pivotPoint, String modelName) {
        if (pivotPoint == null || modelName == null || modelName.isEmpty()) {
            throw new IllegalArgumentException("Display groups cannot be intialized with null or empty values.");
        }
        this.pivotPoint = pivotPoint;

        File file = FileReader.getModelFile(modelName);
        if(file == null) {
            throw new IllegalArgumentException("Display group got intialized with a model that doesn't exist.");
        }
        this.rootBone = FileReader.getRootBone(file);
        this.yaw = 0;
        this.pitch = 0;
        this.roll = 0;
    }

    public boolean Spawn(){
        rootBone.spawn(new Triple(this.pivotPoint.getX(), this.pivotPoint.getY(), this.pivotPoint.getZ()), this.pivotPoint.getWorld());
        return true;
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

//    public void AddRotation2D(double yaw, int ticks) {
//        for (ItemDisplay itemDisplay : itemDisplayList) {
//            itemDisplay.setTeleportDuration(ticks);
//            itemDisplay.teleport(Rotate2D.AddRotation(yaw, this.centerOfRotation, itemDisplay.getLocation().clone()));
//        }
//    }
//
//    public void SetRotation2D(double yaw, int ticks) {
//        for (ItemDisplay itemDisplay : itemDisplayList) {
//            itemDisplay.setTeleportDuration(ticks);
//            itemDisplay.teleport(Rotate2D.SetRotation(yaw, this.centerOfRotation, itemDisplay.getLocation().clone(), 0));
//        }
//    }
//
//    public void SetRotation3D(double yaw, double pitch, double roll, int ticks) {
//        this.yaw = yaw;
//        this.pitch = pitch;
//        this.roll = roll;
//
//        for (ItemDisplay itemDisplay : itemDisplayList) {
//            itemDisplay.setTeleportDuration(ticks);
//            itemDisplay.teleport(Rotate3D.rotateAroundCenter(itemDisplay, this.centerOfRotation, yaw, pitch, roll));
//        }
//    }
    public void RunAnimation(String animationName, boolean loop, double speed) {

    }
}
