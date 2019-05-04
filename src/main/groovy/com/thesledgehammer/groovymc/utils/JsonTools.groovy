package com.thesledgehammer.groovymc.utils

import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectPart
import com.thesledgehammer.groovymc.client.model.json.JsonQuads
import com.thesledgehammer.groovymc.utils.Log
import net.minecraft.util.EnumFacing

class JsonTools {

    static JsonQuads[] Quads(ArrayList<GroovysonObjectPart > modelParts, EnumFacing face) {
        JsonQuads[] jQuads = new JsonQuads[modelParts.size()];
        for(int i = 0; i < modelParts.size(); i++) {
            jQuads[i] = new JsonQuads(modelParts.get(i), modelParts.get(i).From() as float[], modelParts.get(i).To() as float[], face);
           // println "ModelPart " + modelParts.get(i).getPartName() + " Face " + face + " JsonQuads " + jQuads[i].face;
        }
        return jQuads;
    }

    static JsonQuads QuadAFace(ArrayList<GroovysonObjectPart > modelParts, EnumFacing face, int rawModelTexture) {
        if(rawModelTexture > Quads(modelParts, face).size()) {
            Log.logError("This ModelTexture does not contain " + face);
            Log.logError("Or this Model does not contain a ModelTexture at " + rawModelTexture);
            return null;
        }
        return Quads(modelParts, face)[rawModelTexture];
    }
}
