package org.dopelegend.multiItemDisplayEngine.blockBench.generator;

import com.google.gson.*;
import org.dopelegend.multiItemDisplayEngine.MultiItemDisplayEngine;
import org.dopelegend.multiItemDisplayEngine.files.utils.FileGetter;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TexturePack {
    private static File[] allModels = null;
    /**
     *
     * Gets all model files in the model folder
     *
     * @return Array of all model files in the model folder and null if no files were found.
     */
    public static File[] getAllFiles() {
        if(allModels != null) return allModels;
        File dir = FileGetter.getModelFolder();
        FilenameFilter filter = (file, name) -> name.toLowerCase().endsWith("bbmodel");

        return dir.listFiles(filter);
    }

    public static boolean generateTexturePack() {
        File[] files = getAllFiles();
        if (files==null) return true;

        // Change this to change the texture pack name
        String texturePackName = "TexturePack";
        File workingDir = new File(FileGetter.getTempFolder(), texturePackName);
        workingDir.mkdirs();

        // Generate file structure
        generatePackMeta();
        generateModelOverrider(workingDir);



        return true;
    }

    private static void generatePackMeta(){
        // Generate pack.mcmeta
        try {
            File packMeta = new File(FileGetter.getTempFolder(), "pack.mcmeta");
            Files.writeString(packMeta.toPath(),
                    """
        {
          "pack": {
            "pack_format": 64,
            "description": "Made for the RobotEvent, overrides CustomModelData of stone"
          }
        }
        """);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void generateModelOverrider(File workingDir){
        // Generate pack.mcmeta
        try {
            JsonArray cases = new JsonArray();
            JsonObject[] allBones = getAllBones();

            for (JsonObject bone : allBones) {
                String boneUuid = bone.get("uuid").getAsString();

                JsonObject boneEntry = new JsonObject();
                boneEntry.addProperty("when", boneUuid);

                JsonObject model = new JsonObject();
                model.addProperty("type", "model");
                model.addProperty("model", "item/" + boneUuid);

                cases.add(boneEntry);
            }

            // Fallback object
            JsonObject fallback = new JsonObject();
            fallback.addProperty("type", "model");
            fallback.addProperty("model", "block/diamond");

            // Model object
            JsonObject model = new JsonObject();
            model.addProperty("type", "select");
            model.addProperty("property", "custom_model_data");
            model.add("cases", cases);
            model.add("fallback", fallback);

            // Wrap it in root
            JsonObject root = new JsonObject();
            root.add("model", model);

            // Pretty-print
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(root);

            // Write to file
            File file = new File(workingDir, "assets/minecraft/items/diamond.json");
            file.getParentFile().mkdirs();
            Files.writeString(file.toPath(), json);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    private static JsonObject[] getAllBones(){
        File[] allModels = getAllFiles();
        for (File modelFile : allModels) {
            JsonObject modelData;
            Gson gson = new Gson();

            //Get rootJsonObject
            try(java.io.FileReader reader = new java.io.FileReader(modelFile)){
                JsonElement root = gson.fromJson(reader, JsonElement.class);
                modelData = root.getAsJsonObject();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            JsonArray boneArray = modelData.get("outliner").getAsJsonArray();
            JsonObject rootBone = boneArray.get(0).getAsJsonObject();
        }
        // TODO add return
        return null;
    }
    private static JsonObject[] getChildBones(JsonObject bone) {
        JsonArray childrenArray = bone.get("children").getAsJsonArray();

        List<JsonObject> bones = new ArrayList<>();
        for (int i = 0; i < childrenArray.size(); i++) {
            if (childrenArray.get(i) instanceof JsonObject) {
                JsonObject childBone = childrenArray.get(i).getAsJsonObject();
                bones.addAll(Arrays.asList(getChildBones(childBone)));
                bones.add(childBone);
            }
        }
        return bones.toArray(new JsonObject[0]);
    }
}
