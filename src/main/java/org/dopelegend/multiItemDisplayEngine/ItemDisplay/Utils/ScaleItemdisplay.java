package org.dopelegend.multiItemDisplayEngine.ItemDisplay.Utils;

import org.bukkit.entity.ItemDisplay;
import org.bukkit.util.Transformation;
import org.joml.Vector3f;

public class ScaleItemdisplay {

    public static void AddSize(ItemDisplay itemDisplay, float x, float y, float z){
        Transformation itemDisplayTransformation = itemDisplay.getTransformation();
        Vector3f itemDisplayTransformationScale = itemDisplayTransformation.getScale().add(x, y, z);
        Transformation newItemDisplayTransformation = new Transformation(itemDisplayTransformation.getTranslation(),
                itemDisplayTransformation.getLeftRotation(),
                itemDisplayTransformationScale,
                itemDisplayTransformation.getRightRotation());
        itemDisplay.setTransformation(newItemDisplayTransformation);
    }

    public static void SetSize(ItemDisplay itemDisplay, float x, float y, float z){
        Transformation itemDisplayTransformation = itemDisplay.getTransformation();
        Transformation newItemDisplayTransformation = new Transformation(itemDisplayTransformation.getTranslation(),
                itemDisplayTransformation.getLeftRotation(),
                new Vector3f(x, y, z),
                itemDisplayTransformation.getRightRotation());
        itemDisplay.setTransformation(newItemDisplayTransformation);
    }
}
