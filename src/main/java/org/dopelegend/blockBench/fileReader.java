package org.dopelegend.blockBench;

import com.google.gson.*;
import org.dopelegend.multiItemDisplayEngine.MultiItemDisplayEngine;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public class fileReader {

    /**
     *
     * Gets a model file with the path from the model folder
     *
     * @param path Path to model file
     * @return found model file
     */
    public static File getModelFile(String path) {
        File file = new File(MultiItemDisplayEngine.modelFolder, path);
        if(!file.exists()){
            MultiItemDisplayEngine.plugin.getLogger().warning("The model file could not be found: " + path);
            return null;
        }
        return file;
    }

    /**
     *
     * Gets the root bone from a model file, this needs to be a .bbmodel file, and won't work probably if the model has multiple root bones.
     *
     * @param modelFile The file to get the root bone from
     * @return The root bone
     */
    public static Bone getRootBone(File modelFile) {
        //File doesn't exist
        if(!modelFile.exists()){
            MultiItemDisplayEngine.plugin.getLogger().warning("The model file could not be found: " + modelFile.getPath());
            return null;
        }

        //File isn't a file (folder)
        if(!modelFile.isFile()){
            MultiItemDisplayEngine.plugin.getLogger().warning("The provided modelFile is a folder. should be a file: " + modelFile.getName());
            return null;
        }
        //File isn't readable
        if(!modelFile.canRead()){
            MultiItemDisplayEngine.plugin.getLogger().warning("The model file can't be read: " + modelFile.getName());
            return null;
        }
        //File isn't a .bbmodel file
        if(!modelFile.getName().endsWith(".bbmodel")){
            MultiItemDisplayEngine.plugin.getLogger().warning("The model file is not a file type .bbmodel: " + modelFile.getName());
            return null;
        }
        //Declare rootJsonObject
        JsonObject modelData;
        Gson gson = new Gson();

        //Get rootJsonObject
        try(FileReader reader = new FileReader(modelFile)){
            JsonElement root = gson.fromJson(reader, JsonElement.class);
            modelData = root.getAsJsonObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Get rootBone
        JsonObject rootBoneObject = modelData.getAsJsonArray("outliner").get(0).getAsJsonObject();

        return createBone(rootBoneObject, null);
    }


    /**
     *
     * Gets a bone from a json bone object and the parent bone, use parent bone null for root bones. This function iterates over itself to create child bones.
     *
     * @param boneObject Json object of the bone
     * @param parent Parent bone
     * @return Created bone
     */
    static private Bone createBone(JsonObject boneObject, Bone parent){
        JsonArray originArray = boneObject.getAsJsonArray("origin");

        Bone bone = new Bone(
                originArray.get(0).getAsDouble(),
                originArray.get(1).getAsDouble(),
                originArray.get(2).getAsDouble(),
                parent,
                List.of(),
                boneObject.get("uuid").getAsString()
        );

        //Create children bones
        JsonArray childrenArray = boneObject.getAsJsonArray("children");
        for(int i = 0; i < childrenArray.size(); i++){
            if(childrenArray.get(i).getAsJsonObject().get("uuid") != null){
                break;
            }
            bone.addChildrenBone(createBone(childrenArray.get(i).getAsJsonObject(), bone));
        }

        return bone;
    }
}
