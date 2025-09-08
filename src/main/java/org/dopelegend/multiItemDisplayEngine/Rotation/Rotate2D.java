package org.dopelegend.multiItemDisplayEngine.Rotation;


import org.bukkit.Location;

public class Rotate2D {
    /**
     *
     * @param angle How many radians the object should be rotated around the center
     * @param centerOfRotation The point the object should be rotated around
     * @param object The object that is rotated
     */
    public static void rotate(double angle, Location centerOfRotation, Location object) {
        double radius = centerOfRotation.distance(object);
        double totalAngle = angle+Math.asin(radius/(object.getZ()-centerOfRotation.getZ()));
        object.setX(centerOfRotation.getX()+Math.cos(totalAngle)*radius);
        object.setZ(centerOfRotation.getZ()+Math.sin(totalAngle)*radius);
        object.setYaw((float) (object.getYaw()+Math.toDegrees(angle)));
    }
}
