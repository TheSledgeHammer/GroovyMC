package com.thesledgehammer.groovymc.client.model

import com.thesledgehammer.groovymc.client.model.json.GroovysonObject
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.utils.GroovyLoader
import com.thesledgehammer.groovymc.utils.StringTools

//Item & Block Models
//AbstractModels does not utilise IBakedModel, it reads .jsons directly from a GroovysonObject and GroovysonObjectPart
class GroovysonModel extends GroovysonObject {

    private String resourceObject;
    private List<GroovysonObjectPart> groovysonObjectParts = new ArrayList<>();
    private HashMap<String, String> rawModelTexturesMap = new HashMap<>();

    GroovysonModel(String resourceObject, String fileName) {
        setRawModel(resourceObject, fileName);
    }

    private void setRawModel(String resourceObject, String fileName) {
        setResourceObject(resourceObject);
        setJsonObject(GroovyLoader.Instance().getModResourceDirectory(), GroovyLoader.Instance().getModID(), "models", resourceObject, fileName);
    }

    private void setResourceObject(String resourceObject) {
        this.resourceObject = resourceObject;
    }

    //Texture Name & Location
    void setRawModelTextures(String textureName) {
        String texLocation = super.getTexturesByName(textureName);
        for(int i = 0; i < StringTools.StringToList(texLocation).size(); i++) {
            rawModelTexturesMap.put(textureName, StringTools.StringToList(texLocation).get(i));
        }
        StringTools.StringToList(texLocation).clear();
    }

    HashMap<String, String> getRawModelTextures() {
        return rawModelTexturesMap;
    }

    String getTexturesLocation(String textureName) {
        return rawModelTexturesMap.get(textureName);
    }

    void setRawModelParts(String partName) {
        groovysonObjectParts.add(new GroovysonObjectPart(this, partName));
    }

    //Returns all Model Elements in .json if applicable
    ArrayList<GroovysonObjectPart> getRawModelParts() {
        return groovysonObjectParts;
    }

    //Returns Individual Model Elements in .json if applicable
    GroovysonObjectPart getRawModelPart(int index) {
        return groovysonObjectParts.get(index)
    }
}
