/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.utils

class GroovyLoader {

    private static GroovyLoader instance;
    private String modPath;
    private String modResourcePath;
    private String jvm;
    private String url;
    private String modID;
    private String modDirectory;
    private String modResourceDirectory

    GroovyLoader() {
        instance = this;
    }

    /**
     * @param jvm: java, scala, kotlin, groovy... etc
     * @param url: example: com.sledgehammer is the url of GroovyMC
     * @param modID: mod name
     */
    GroovyLoader(String jvm, String url, String modID) {
        setDirectories(jvm, url, modID);
        instance = this;
    }

    /**
     * @param modPath: directory containing mod
     * @param modResourcePath: directory containing resources & assets
     * @param jvm: java, scala, kotlin, groovy... etc
     * @param url: example: com.sledgehammer is the url of GroovyMC
     * @param modID: mod name
     */
    GroovyLoader(String modPath, String modResourcePath, String jvm, String url, String modID) {
        setCustomDirectories(modPath, modResourcePath, jvm, url, modID);
        instance = this;
    }

    static GroovyLoader Instance() {
        if(instance == null) {
            return null;
        }
        return instance;
    }

    String getModPath() {
        return modPath;
    }

    String getModResourcePath() {
        return modResourcePath;
    }

    String getJVM() {
        return jvm;
    }

    String getPackageURL() {
        return url;
    }

    String getModID() {
        return modID;
    }

    /**
     * @return Full Mod Directory
     */
    String getModDirectory() {
        return modDirectory;
    }

    /**
     * @return: Full Resource Directory
     */
    String getModResourceDirectory() {
        return modResourceDirectory;
    }

    void setModPath(String modPath) {
        this.modPath = modPath;
    }

    void setModResourcePath(String modResourcePath) {
        this.modResourcePath = modResourcePath;
    }

    void setJVMLanguage(String jvm) {
        this.jvm = jvm;
    }

    void setPackageURL(String url) {
        this.url = url;
    }

    void setModID(String modID) {
        this.modID = modID;
    }

    void setDirectories(String jvm, String url, String modID) {
        setModPath("src/main");
        setModResourcePath("resources/assets");
        setJVMLanguage(jvm);
        setPackageURL(url);
        setModID(modID);
        this.modDirectory = new File("src/main" + "/" + jvm + "/" + url + "/" + modID + "/").absolutePath;
        //this.modResourceDirectory = new File("src/main/resources/assets/" + modID + "/").absolutePath;
        this.modResourceDirectory = new File("src/main/resources/assets/").absolutePath;
    }

    void setCustomDirectories(String modPath, String modResourcePath, String jvm, String url, String modID) {
        setModPath(modPath);
        setModResourcePath(modResourcePath);
        setJVMLanguage(jvm);
        setPackageURL(url);
        setModID(modID);
        this.modDirectory = new File(modPath + "/" + jvm + "/" + url + "/" + modID + "/").absolutePath;
        //this.modResourceDirectory = new File(modPath + "/" + modResourcePath + "/" + modID + "/").absolutePath;
        this.modResourceDirectory = new File(modPath + "/" + modResourcePath + "/").absolutePath;
    }
}