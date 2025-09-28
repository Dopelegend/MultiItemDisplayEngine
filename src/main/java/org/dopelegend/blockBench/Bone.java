package org.dopelegend.blockBench;

import java.util.List;

public class Bone {

    private double relOriginX;
    private double relOriginY;
    private double relOriginZ;

    private String UUID = "";

    private List<Bone> childrenBones;
    private Bone parentBone;

    public Bone(double relOriginX, double relOriginY, double relOriginZ, Bone parentBone, List<Bone> childrenBones, String UUID) {
        this.relOriginX = relOriginX;
        this.relOriginY = relOriginY;
        this.relOriginZ = relOriginZ;
        this.UUID = UUID;
        this.childrenBones = childrenBones;
        this.parentBone = parentBone;
    }

    public double getRelOriginX() {
        return relOriginX;
    }

    public void setRelOriginX(double relOriginX) {
        this.relOriginX = relOriginX;
    }

    public double getRelOriginY() {
        return relOriginY;
    }

    public void setRelOriginY(double relOriginY) {
        this.relOriginY = relOriginY;
    }

    public double getRelOriginZ() {
        return relOriginZ;
    }

    public void setRelOriginZ(double relOriginZ) {
        this.relOriginZ = relOriginZ;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public List<Bone> getChildrenBones() {
        return childrenBones;
    }

    public void setChildrenBones(List<Bone> childrenBones) {
        this.childrenBones = childrenBones;
    }

    public void addChildrenBone(Bone childrenBone) {
        this.childrenBones.add(childrenBone);
    }

    public Bone getParentBone() {
        return parentBone;
    }

    public void setParentBone(Bone parentBone) {
        this.parentBone = parentBone;
    }
}
