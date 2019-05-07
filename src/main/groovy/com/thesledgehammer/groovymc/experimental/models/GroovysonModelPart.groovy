package com.thesledgehammer.groovymc.experimental.models

import com.thesledgehammer.groovymc.client.model.json.GroovysonObject
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectCache
import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.model.json.JsonQuads
import net.minecraft.util.EnumFacing

class GroovysonModelPart extends GroovysonObjectPart {

    private GroovysonObject groovysonObject;
    private List<GroovysonObjectPart> groovysonObjectParts;
    private GroovysonObjectCache groovysonObjectCache;

    GroovysonModelPart(GroovysonObject groovysonObject) {
        setGroovysonObject(groovysonObject);
        groovysonObjectParts = new ArrayList<>();
    }

    void setRawModelPart(String partName) {
        groovysonObjectParts.add(new GroovysonObjectPart(groovysonObject, partName));
        for(GroovysonObjectPart part : this) {
            setObjectCache(groovysonObject, part);
        }
    }

    //Returns all Model Elements in .json if applicable
    ArrayList<GroovysonObjectPart> getRawModelParts() {
        return groovysonObjectParts;
    }

    //Returns Individual Model Elements in .json if applicable
    GroovysonObjectPart getRawModelPart(int index) {
        return groovysonObjectParts.get(index)
    }

    private void setObjectCache(GroovysonObjectPart partName) {
        groovysonObjectCache = new GroovysonObjectCache(groovysonObject, partName);
    }

    GroovysonObjectCache getObjectCache() {
        return groovysonObjectCache;
    }

    private void setObjectCache(GroovysonObject go, GroovysonObjectPart partName) {
        groovysonObjectCache = new GroovysonObjectCache(go, partName);
    }

    JsonQuads[] Quads(face) {
        JsonQuads[] jQuads = new JsonQuads[this.size()];
        for(int i = 0; i < jQuads.size(); i++) {
            jQuads[i] = new JsonQuads(getRawModelPart(i), getRawModelPart(i).From() as float[], getRawModelPart(i).To() as float[], face);
        }
        return jQuads;
    }

    //Get Element variables at index by face name
    HashMap<String, EnumFacing> FaceMap(int index) {
        HashMap<String, EnumFacing> arrMap = new HashMap<>();
        for(EnumFacing face : EnumFacing.VALUES) {
            arrMap.put(face.getName(), getRawModelPart(index).Facing(face));
        }
        return arrMap;
    }
}
