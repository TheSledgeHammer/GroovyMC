package com.thesledgehammer.groovymc.experimental.models

import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectModel
import net.minecraft.client.resources.IResourceManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.resource.IResourceType
import net.minecraftforge.client.resource.ISelectiveResourceReloadListener

import java.util.function.Predicate

//Work In Progress
interface ICustomGroovyModelLoader extends ISelectiveResourceReloadListener {

    @Override
    void onResourceManagerReload(IResourceManager resourceManager);

    @Override
    void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate);

    /*
     * Checks if given model should be loaded by this loader.
     * Reading file contents is inadvisable, if possible decision should be made based on the location alone.
     */
    boolean accepts(ResourceLocation modelLocation);

    /*
     * loads (or reloads) specified model
     */
    GroovysonObjectModel loadModel(ResourceLocation modelLocation) throws Exception;

}
