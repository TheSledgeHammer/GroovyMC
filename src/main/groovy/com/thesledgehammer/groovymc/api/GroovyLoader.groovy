/*
 * Copyright [2018] [TheSledgeHammer]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thesledgehammer.groovymc.api

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder

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
<<<<<<< HEAD:src/main/groovy/com/thesledgehammer/groovymc/api/GroovyLoader.groovy
        this.modDirectory = new File("src/main/${jvm}/${url}/${modID}/").toString();
        this.modResourceDirectory = new File("src/main/resources/assets/").toString();
=======
        this.modDirectory = new File("src/main/${jvm}/${url}/${modID}/").absolutePath;
        this.modResourceDirectory = new File("src/main/resources/assets/").absolutePath;
>>>>>>> 1.16.x:src/main/groovy/com/thesledgehammer/groovymc/utils/GroovyLoader.groovy
    }

    void setCustomDirectories(String modPath, String modResourcePath, String jvm, String url, String modID) {
        setModPath(modPath);
        setModResourcePath(modResourcePath);
        setJVMLanguage(jvm);
        setPackageURL(url);
        setModID(modID);
<<<<<<< HEAD:src/main/groovy/com/thesledgehammer/groovymc/api/GroovyLoader.groovy
        this.modDirectory = new File("${modPath}/${jvm}/${url}/${modID}/").toString();
        this.modResourceDirectory = new File("${modPath}/${modResourcePath}/").toString();
=======
        this.modDirectory = new File("${modPath}/${jvm}/${url}/${modID}/").absolutePath;
        this.modResourceDirectory = new File("${modPath}/${modResourcePath}/").absolutePath;
>>>>>>> 1.16.x:src/main/groovy/com/thesledgehammer/groovymc/utils/GroovyLoader.groovy
    }

    final int hashCode() {
        return new HashCodeBuilder(7, 31)
                .append(this.modPath)
                .append(this.modResourcePath)
                .append(this.jvm)
                .append(this.url)
                .append(this.modID)
                .append(this.modDirectory)
                .append(this.modResourceDirectory)
                .toHashCode();
    }

    final boolean equals(GroovyLoader obj) {
        GroovyLoader other = obj;
        return new EqualsBuilder()
                .append(this.modPath, other.modPath)
                .append(this.modResourcePath, other.modResourcePath)
                .append(this.jvm, other.jvm)
                .append(this.url, other.url)
                .append(this.modID, other.modID)
                .append(this.modDirectory, other.modDirectory)
                .append(this.modResourceDirectory, other.modResourceDirectory)
                .isEquals();
    }
}