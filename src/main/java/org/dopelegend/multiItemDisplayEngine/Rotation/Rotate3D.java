package org.dopelegend.multiItemDisplayEngine.Rotation;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.World;

public class Rotate3D {
    /**
     *
     * @param yaw The yaw (side to side) angle in Radians
     * @param pitch The pitch (up / down), angle in Radians
     * @param centerOfRotation The location to rotate the object around
     * @param object The object to rotate
     * @return Returns the new location of the object
     */
    public static Location SetRotation(double yaw, double pitch , Location centerOfRotation, Location object) {
        Location rotatedLoc = Rotate2D.SetRotation(yaw, centerOfRotation, object);
        double relativeZ = rotatedLoc.getZ() - centerOfRotation.getZ();
        double relativeX = rotatedLoc.getX() - centerOfRotation.getX();
        double relativeY = rotatedLoc.getY() - centerOfRotation.getY();

        double sinZYaw = Math.sin(yaw);
        double cosXYaw = Math.cos(yaw);

        double radius2D = Math.sqrt(Math.pow(relativeX, 2) + Math.pow(relativeZ, 2));
        double radius = Math.sqrt(Math.pow(relativeY, 2) + Math.pow(radius2D, 2));

        object.setX(centerOfRotation.getX()+Math.cos(pitch)*radius*cosXYaw);
        object.setZ(centerOfRotation.getZ()+Math.cos(pitch)*radius*sinZYaw);
        object.setY(centerOfRotation.getY()+Math.sin(pitch)*radius);
        return object;
    }

    public static Location AddRotation(double yaw, double pitch , Location centerOfRotation, Location object) {
        Location rotatedLoc = Rotate2D.AddRotation(yaw, centerOfRotation, object);
        double totalYaw = yaw+Math.atan2(object.getZ()-centerOfRotation.getZ(), object.getX()-centerOfRotation.getX());
        double relativeZ = rotatedLoc.getZ() - centerOfRotation.getZ();
        double relativeX = rotatedLoc.getX() - centerOfRotation.getX();
        double relativeY = rotatedLoc.getY() - centerOfRotation.getY();

        double sinZYaw = Math.sin(totalYaw);
        double cosXYaw = Math.cos(totalYaw);

        double radius2D = Math.sqrt(Math.pow(relativeX, 2) + Math.pow(relativeZ, 2));
        double radius = Math.sqrt(Math.pow(relativeY, 2) + Math.pow(radius2D, 2));
        double totalPitch = pitch+Math.atan2(object.getY()-centerOfRotation.getY(), radius);

        World world = centerOfRotation.getWorld();
        world.sendMessage(Component.text("pitch: "+Math.toDegrees(totalPitch)));
        world.sendMessage(Component.text("yaw: "+Math.toDegrees(totalYaw)));
        world.sendMessage(Component.text("2d radius: "+radius2D));
        world.sendMessage(Component.text("3d radius: "+radius));

        object.setX(centerOfRotation.getX()+Math.cos(totalPitch)*radius*cosXYaw);
        object.setZ(centerOfRotation.getZ()+Math.cos(totalPitch)*radius*sinZYaw);
        object.setY(centerOfRotation.getY()+Math.sin(totalPitch)*radius);
        return object;
    }
}
