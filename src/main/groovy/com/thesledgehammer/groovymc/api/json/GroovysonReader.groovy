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

package com.thesledgehammer.groovymc.api.json

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
