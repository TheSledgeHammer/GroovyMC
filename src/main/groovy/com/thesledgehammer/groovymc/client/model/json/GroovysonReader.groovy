/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.client.model.json

import groovy.json.JsonSlurper

class GroovysonReader {
	
	//Return absolute file path of resource assets file (.json)
	//Generic hooks to a (.Json) File;
	static String AssetsFilePath(String path, String modid, String resourceType, String fileName) {
		String assetsPath = new File(path + "/" + modid + "/" + resourceType + "/" + fileName).absolutePath;
		return assetsPath;
	}

	static String JsonFile(String path, String modid, String resourceType, String fileName) {
		String filePath = AssetsFilePath(path, modid, resourceType, fileName);
		String fileContents = new File(filePath + ".json").getText('UTF-8');
		return fileContents;
	}

	static String AssetsFilePath(String path, String modid, String resource, String resourceObject, String fileName) {
		String assetsPath = new File(path + "/" + modid + "/" + resource + "/" + resourceObject + "/" + fileName).absolutePath;
		return assetsPath;
	}

	static String JsonFile(String path, String modid, String resource, String resourceObject, String fileName) {
		String filePath = AssetsFilePath(path, modid, resource, resourceObject, fileName);
		String fileContents = new File(filePath + ".json").getText('UTF-8');
		return fileContents;
	}
	
	//Converts Json File to a readable JsonObject
	static def JsonSlurpy(String jsonFile) {
		def slurpinator = new JsonSlurper();
		def jsonObject = slurpinator.parseText(jsonFile);
		return jsonObject
	}
}
