package org.dopelegend.multiItemDisplayEngine.Rotation;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;

public class Rotate2D {
    /**
     *
     * @param angle How many radians the object should be rotated around the center, in degrees (360).
     * @param centerOfRotation The point the object should be rotated around
     * @param object The object that is rotated
     */
    public static Location AddRotation(double angle, Location centerOfRotation, Location object) {
        angle = Math.toRadians(angle);
        double radius = Math.sqrt(Math.pow((centerOfRotation.getX()-object.getX()), 2) + Math.pow((centerOfRotation.getZ()-object.getZ()), 2));
        double totalAngle = angle+Math.atan2(object.getZ()-centerOfRotation.getZ(), object.getX()-centerOfRotation.getX());
        object.setX(centerOfRotation.getX()+Math.cos(totalAngle)*radius);
        object.setZ(centerOfRotation.getZ()+Math.sin(totalAngle)*radius);
        object.setYaw((float) (object.getYaw()+Math.toDegrees(angle)));
        return object;
    }

    /**
     *
     * @param angle The angle that the object should be at compared to the center, in degrees (360).
     * @param centerOfRotation The point the object should be rotated around
     * @param object The object that is rotated
     * @param offset The offset the returned location should have in degrees (360)
     *
     * @return The Location
     */
    public static Location SetRotation(double angle, Location centerOfRotation, Location object, double offset) {
        angle = Math.toRadians(angle);
        double radius = Math.sqrt(Math.pow((centerOfRotation.getX()-object.getX()), 2) + Math.pow((centerOfRotation.getZ()-object.getZ()), 2));
        centerOfRotation.getWorld().sendMessage(Component.text(String.valueOf(radius)));
        offset = Math.toRadians(offset);
        object.setX(centerOfRotation.getX()+Math.cos(angle+offset)*radius);
        object.setZ(centerOfRotation.getZ()+Math.sin(angle+offset)*radius);
        object.setYaw((float) (Math.toDegrees(angle)));
        return object;
    }
}
