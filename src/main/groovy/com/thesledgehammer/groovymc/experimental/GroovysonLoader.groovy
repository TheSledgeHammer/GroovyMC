package com.thesledgehammer.groovymc.experimental

import net.minecraft.client.renderer.model.IUnbakedModel
import net.minecraft.resources.IResource
import net.minecraft.resources.IResourceManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ICustomModelLoader
import net.minecraftforge.client.model.ModelLoaderRegistry
import org.apache.commons.io.IOUtils
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

enum GroovysonLoader implements ICustomModelLoader {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger();
    private IResourceManager manager;
    private final Set<String> enabledDomains = new HashSet<>();
    private final Map<ResourceLocation, GroovysonObjectModel> cache = new HashMap<>();
    private final Map<ResourceLocation, Exception> errors = new HashMap<>();

    void addDomain(String domain) {
        enabledDomains.add(domain.toLowerCase());
        LOGGER.info("GroovysonLoader: Domain {} has been added.", domain.toLowerCase());
    }

    @Override
    void onResourceManagerReload(IResourceManager resourceManager) {
        this.manager = resourceManager;
    }

    @Override
    boolean accepts(ResourceLocation modelLocation) {
        return enabledDomains.contains(modelLocation.getNamespace()) && modelLocation.getPath().endsWith(".gom");
    }

    @Override
    IUnbakedModel loadModel(ResourceLocation modelLocation) throws Exception {
        ResourceLocation file = new ResourceLocation(modelLocation.getNamespace(), modelLocation.getPath());
        if(!cache.containsKey(file)) {
            IResource resource = null;
            try {
                try {
                    resource = manager.getResource(file);
                } catch (FileNotFoundException e) {
                    if (modelLocation.getPath().startsWith("models/block/")) {
                        resource = manager.getResource(new ResourceLocation(file.getNamespace(), "models/item/" + file.getPath().substring("models/block/".length())));
                    } else if (modelLocation.getPath().startsWith("models/item/")) {
                        resource = manager.getResource(new ResourceLocation(file.getNamespace(), "models/block/" + file.getPath().substring("models/item/".length())));
                    } else {
                        throw e;
                    }
                    GroovysonObjectModel model = null;
                }
            } finally {
                IOUtils.closeQuietly(resource);
            }
        }
        GroovysonObjectModel model = cache.get(file);
        if(model == null) {
            throw new ModelLoaderRegistry.LoaderException("Error loading model previously: ${file}", errors.get(modelLocation));
        }
        return model;
    }
}