package com.thesledgehammer.groovymc.experimental.jsons

import com.thesledgehammer.groovymc.client.model.ModelUtil

interface ITextureGetter {

    ModelUtil.TexturedFace get(String location);
}