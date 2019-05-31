package com.thesledgehammer.groovymc.experimental.models

import com.google.common.collect.HashBasedTable
import com.thesledgehammer.groovymc.client.definitions.GroovyDefinitionContext
import com.thesledgehammer.groovymc.client.definitions.GroovyModelDefinition
import com.thesledgehammer.groovymc.client.definitions.GroovyRenderDefinition
import com.thesledgehammer.groovymc.client.definitions.GroovyResourceDefinition
import com.thesledgehammer.groovymc.client.definitions.render.CutoutKey
import com.thesledgehammer.groovymc.client.definitions.render.CutoutMippedKey
import com.thesledgehammer.groovymc.client.definitions.render.SolidKey
import com.thesledgehammer.groovymc.client.definitions.render.TranslucentKey
import com.thesledgehammer.groovymc.client.model.ModelUtil
import com.thesledgehammer.groovymc.client.model.MutableQuad
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.model.json.JsonRule
import com.thesledgehammer.groovymc.client.model.json.JsonTexture
import com.thesledgehammer.groovymc.experimental.jsons.GroovysonVariableModel
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.util.EnumFacing

//Work In Progress
class GroovyVariableModel {

    private GroovysonVariableModel GROOVY_MODEL;
    private GroovyDefinitionContext GDC;
    private HashBasedTable<EnumFacing, Integer, JsonTexture> JSON_TEXTABLE = HashBasedTable.create();
    private JsonRule[] rules;

    GroovyVariableModel(String resourceObject, String fileName) {
        this.GROOVY_MODEL = new GroovysonVariableModel(resourceObject, fileName);
        GDC = new GroovyDefinitionContext(new GroovyResourceDefinition(), new GroovyModelDefinition(), new GroovyRenderDefinition(GROOVY_MODEL));
    }

    GroovyVariableModel(String resourceDirectory, String modID, String resourceObject, String fileName) {
        this.GROOVY_MODEL = new GroovysonVariableModel(resourceDirectory, modID, resourceObject, fileName);
        GDC = new GroovyDefinitionContext(new GroovyResourceDefinition(), new GroovyModelDefinition(), new GroovyRenderDefinition(GROOVY_MODEL));
    }

    GroovyVariableModel(GroovysonVariableModel GROOVY_MODEL) {
        this.GROOVY_MODEL = GROOVY_MODEL;
        GDC = new GroovyDefinitionContext(new GroovyResourceDefinition(), new GroovyModelDefinition(), new GroovyRenderDefinition(GROOVY_MODEL));
    }

    GroovysonVariableModel getGroovysonModel() {
        return GROOVY_MODEL;
    }

    void setRenderKeysDefintion(GroovysonVariableModel GROOVY_MODEL) {
        GDC.setCutoutKey(new CutoutKey(GROOVY_MODEL));
        GDC.setTranslucentKey(new TranslucentKey(GROOVY_MODEL));
        GDC.setSolidKey(new SolidKey(GROOVY_MODEL));
        GDC.setCutoutMippedKey(new CutoutMippedKey(GROOVY_MODEL));
    }

    MutableQuad[] bakePart(ArrayList<GroovysonObjectPart> modelParts) {
        List<MutableQuad> list = new ArrayList<>();
        for (GroovysonObjectPart part : modelParts) {

        }
        for (JsonRule rule : rules) {

        }
        return list.toArray(new MutableQuad[list.size()]);
    }

    MutableQuad[] getCutoutQuads() {
        return bakePart(GDC.getCutoutKey().getCutoutModelElements());
    }

    MutableQuad[] getTranslucentQuads() {
        return bakePart(GDC.getTranslucentKey().getTranslucentModelElements());
    }

    MutableQuad[] getSolidQuads() {
        return bakePart(GDC.getSolidKey().getSolidModelElements());
    }

    MutableQuad[] getCutoutMippedQuads() {
        return bakePart(GDC.getCutoutMippedKey().getCutoutMippedModelElements());
    }

    HashBasedTable<EnumFacing, Integer, JsonTexture> getJsonTextureMappings() {
        return JSON_TEXTABLE;
    }

    JsonTexture getJsonTexture(EnumFacing face, int index) {
        return JSON_TEXTABLE.get(face, index);
    }

    private void JsonTextureMapping() {
        ArrayList<GroovysonObjectPart> modelPartTexture = new ArrayList<>();
        ArrayList<String> textureName = GROOVY_MODEL.getRawModelTextures().keySet().toArray() as ArrayList<String>;
        String textureLocation = "";

        //Model Elements (Model Parts)
        for (int i = 0; i < GROOVY_MODEL.getRawModelParts().size(); i++) {
            modelPartTexture.add(GROOVY_MODEL.getRawModelParts().get(i));
        }

        //JsonTexTable: Defines JsonTextures by face and model part
        for (EnumFacing face : EnumFacing.VALUES) {
            for (int j = 0; j < modelPartTexture.size(); j++) {
                if (modelPartTexture.get(j).Facing(face) != null) {
                    for (int k = 0; k < textureName.size(); k++) {
                        textureLocation = GROOVY_MODEL.getRawModelTextures().get(textureName.get(k));
                    }
                    JsonTexture texture = new JsonTexture(modelPartTexture.get(j), textureLocation, face);
                    JSON_TEXTABLE.put(face, j, texture);
                }
            }
        }
    }

    ModelUtil.TexturedFace TexturedFaceLookup(EnumFacing facing, int index) {
        TextureAtlasSprite sprite;
        String name = GROOVY_MODEL.getRawModelPart(index).TextureFace(facing);
        String lookup = getJsonTexture(facing, index).location;
        sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(lookup);
        ModelUtil.TexturedFace face = new ModelUtil.TexturedFace();
        face.sprite = sprite;
        face.faceData = getJsonTexture(facing, index).faceData;
        return face;
    }

    interface ITextureGetter {
        ModelUtil.TexturedFace get(String location);
    }

    void setModelElements(String name) {
        GROOVY_MODEL.setRawModelParts(name);
    }

    void setModelTextures(String name) {
        GROOVY_MODEL.setRawModelTextures(name);
    }

    GroovysonObjectPart getModelElements(int index) {
        return GROOVY_MODEL.getRawModelPart(index);
    }

    ArrayList<GroovysonObjectPart> getModelElements() {
        return GROOVY_MODEL.getRawModelParts();
    }

    String getModelTextures(String textureName) {
        return GROOVY_MODEL.getRawModelTextures().get(textureName);
    }

    //Returns a Texture from x model element and face
    String getModelElementTextures(int index, EnumFacing face) {
        return GROOVY_MODEL.getRawModelParts().get(index).TextureFace(face);
    }
}
