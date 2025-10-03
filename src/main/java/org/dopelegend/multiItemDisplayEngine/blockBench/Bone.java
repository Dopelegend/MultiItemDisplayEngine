package org.dopelegend.multiItemDisplayEngine.blockBench;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.dopelegend.multiItemDisplayEngine.itemDisplay.utils.itemDisplayGroups.ItemDisplayGroup;
import org.dopelegend.multiItemDisplayEngine.utils.CustomModelData;
import org.dopelegend.multiItemDisplayEngine.utils.classes.Triple;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * A single bone of a model, these are the smallest parts that models are split into each bone is equal to either one or zero item displays.
 * Each bone has a parent bone (except the root bone), as well as children bones a bone is not guaranteed to have children bones.
 *
 */
public class Bone {

    Triple relPivot;

    private String UUID = "";
    private Element[] elements;
    private Bone[] childrenBones;
    private Bone parentBone;

    boolean hasElement;

    /**
     *
     * @param relPivot The origin represented by 3 doubles in the form of a triple.
     * @param parentBone Parent of this bone
     * @param childrenBones All children direct child bones from this bone
     * @param elements All elements that this bone represents
     * @param UUID UUID of this bone
     */
    public Bone(Triple relPivot, Bone parentBone, Bone[] childrenBones, Element[] elements, String UUID) {
        this.relPivot = relPivot;
        this.UUID = UUID;
        this.childrenBones = childrenBones;
        this.elements = elements;
        this.parentBone = parentBone;
        this.hasElement = false;
    }

    public void spawn(Triple originPosition, World world){
        for(int i = 0; i < this.elements.length; i++){
            this.elements[i].spawn(originPosition, world);
        }
        for(int i = 0; i < this.childrenBones.length; i++){
            this.childrenBones[i].spawn(originPosition, world);
        }
    }

    public Triple getRelOrigin() {
        return relPivot;
    }

    public void setRelOrigin(Triple relOrigin) {
        this.relPivot = relOrigin;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Bone[] getChildrenBones() {
        return childrenBones;
    }

    public void setChildrenBones(Bone[] childrenBones) {
        this.childrenBones = childrenBones;
    }

    public void setChildrenBone(Bone[] childrenBone) {
        this.childrenBones = childrenBone;
    }

    public Bone getParentBone() {
        return parentBone;
    }

    public void setParentBone(Bone parentBone) {
        this.parentBone = parentBone;
    }
}
