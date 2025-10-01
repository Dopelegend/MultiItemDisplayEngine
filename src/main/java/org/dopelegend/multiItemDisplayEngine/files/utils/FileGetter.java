package org.dopelegend.multiItemDisplayEngine.files.utils;

import org.dopelegend.multiItemDisplayEngine.MultiItemDisplayEngine;

import java.io.File;

public class FileGetter {

    static private File modelFolder;
    static private File texturePackFolder;
    static private File tempFolder;

    /**
     *
     * Gets the model folder
     *
     * @return The model folder or null if none exists
     */
    public static File getModelFolder (){
        if(!modelFolder.exists()){
            modelFolder = new File(MultiItemDisplayEngine.plugin.getDataFolder(), "Models");
        }
        return modelFolder;
    }

    /**
     *
     * Gets the texturepack folder
     *
     * @return The texturepack folder or null if none exists
     */
    public static File getTexturePackFolder (){
        if(!texturePackFolder.exists()){
            texturePackFolder = new File(MultiItemDisplayEngine.plugin.getDataFolder(), "TexturePacks");
        }
        return texturePackFolder;
    }

    /**
     *
     * Gets the tempfolder
     *
     * @return The tempfolder or null if none exists
     */
    public static File getTempFolder (){
        if(!tempFolder.exists()){
            tempFolder = new File(MultiItemDisplayEngine.plugin.getDataFolder(), "Temp");
        }
        return tempFolder;
    }
}
