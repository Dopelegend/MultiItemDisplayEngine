package org.dopelegend.multiItemDisplayEngine.files.generate;

import org.dopelegend.multiItemDisplayEngine.MultiItemDisplayEngine;

import java.io.File;


public class FileStructure {

    /**
     *
     * Generates any missing parts of the file structure, this should be run in onEnable()
     *
     */
    public static void generateEntireFileStructure (){
        File dataFolder = generateDataFolder();
        generateModelFolder(dataFolder);
        generateTexturePackFolder(dataFolder);
        generateTempFolder(dataFolder);
    }

    /**
     *
     * Generates the root DataFolder of the plugin
     *
     */
    public static File generateDataFolder(){
        File dataFolder = MultiItemDisplayEngine.plugin.getDataFolder();
        if (dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        return dataFolder;
    }

    /**
     *
     * Generates the Model Folder of the plugin, assumes that the dataFolder input is valid.
     *
     */
    public static File generateModelFolder(File dataFolder){
        File modelFolder = new File(dataFolder, "Models");
        if (!modelFolder.exists()) {
            modelFolder.mkdirs();
        }
        return modelFolder;
    }

    /**
     *
     * Generates the texturepack folder of the plugin, assumes that the dataFolder input is valid.
     *
     */
    public static File generateTexturePackFolder(File dataFolder){
        File texturePackFolder = new File(dataFolder, "TexturePacks");
        if (!texturePackFolder.exists()) {
            texturePackFolder.mkdirs();
        }
        return texturePackFolder;
    }

    /**
     *
     * Generates the temp folder of the plugin, assumes that the dataFolder input is valid.
     *
     */
    public static File generateTempFolder(File dataFolder){
        File tempFolder = new File(dataFolder, "temp");
        if (!tempFolder.exists()) {
            tempFolder.mkdirs();
        }
        return tempFolder;
    }
}
