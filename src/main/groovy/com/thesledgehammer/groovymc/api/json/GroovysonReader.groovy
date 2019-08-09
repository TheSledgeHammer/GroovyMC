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

import com.thesledgehammer.groovymc.api.GroovyLoader
import com.thesledgehammer.groovymc.utils.StringTools
import groovy.json.JsonSlurper
import net.minecraft.util.ResourceLocation

/**
 * Allows for reading of resource in either .json or .xml format.
 */
class GroovysonReader {

	private static String path;
	private static ResourceLocation resourceLocation;
	private static String resourceDomain;
	private static String resourcePath;
	private static String fileName;

	static String getPath() {
		return path;
	}

	static ResourceLocation getResourceLocation() {
		return resourceLocation;
	}

	static String getFileName() {
		return fileName;
	}

	static String ResourcePath(String path, ResourceLocation resourceLocation) {
		setPath(path);
		setResourceLocation(resourceLocation);
		String assetsPath = path + "/" + resourceLocation.getResourceDomain().toString() + "/" + resourceLocation.getResourcePath().toString();
		return assetsPath;
	}

	static String ResourcePath(String path, String resourceDomain, String resourcePath) {
		setPath(path);
		setResourceLocation(new ResourceLocation(resourceDomain, resourcePath));
		String assetsPath = path + "/" + resourceLocation.getResourceDomain().toString() + "/" + resourceLocation.getResourcePath().toString();
		return assetsPath;
	}

	private static void setPath(String path) {
		this.path = path;
	}

	private static void setResourceLocation(ResourceLocation resourceLocation) {
		this.resourceLocation = resourceLocation;

		if(resourceLocation != null) {
			resourceDomain = resourceLocation.getResourceDomain().toString();
			resourcePath = resourceLocation.getResourcePath().toString();
		}

		String[] name = StringTools.split(resourceLocation.getResourcePath().toString(), "/");
		fileName = name[name.length - 1];
	}

	//Converts an Json File to a readable JsonObject
	static def JsonSlurpy(String jsonFile) {
		def slurpinator = new JsonSlurper();
		def jsonObject = slurpinator.parse(new FileReader(jsonFile + ".json"));
		return jsonObject
	}

	//Converts an Xml File to a readable XmlObject
	static def XmlSlurpy(String xmlFile) {
		def slurpinator = new XmlSlurper();
		def xmlObject = slurpinator.parse(new FileReader(xmlFile + ".xml"))
		return xmlObject;
	}
}