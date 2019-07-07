package com.thesledgehammer.groovymc.experimental.models

import com.thesledgehammer.groovymc.client.model.json.GroovysonObjectModel
import net.minecraft.client.resources.IResource
import net.minecraft.client.resources.IResourceManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ModelLoaderRegistry
import net.minecraftforge.client.resource.IResourceType
import net.minecraftforge.client.resource.VanillaResourceType

import java.util.function.Predicate
//Work In Progress:
class GroovyModelLoader implements ICustomGroovyModelLoader {

    private IResourceManager manager;
    private final Set<String> enabledDomains = new HashSet<>();
    private final Map<ResourceLocation, GroovysonObjectModel> cache = new HashMap<>();
    private final Map<ResourceLocation, Exception> errors = new HashMap<>();


    @Override
    void onResourceManagerReload(IResourceManager resourceManager) {
        this.manager = resourceManager;
        cache.clear();
        errors.clear();
    }

    @Override
    void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
        if (resourcePredicate.test(VanillaResourceType.MODELS)) {
            onResourceManagerReload(resourceManager);
        }
        super.onResourceManagerReload(resourceManager, resourcePredicate)
    }

    @Override
    boolean accepts(ResourceLocation modelLocation) {
        return false
    }

    @Override
    GroovysonObjectModel loadModel(ResourceLocation modelLocation) throws Exception {
        ResourceLocation file = new ResourceLocation(modelLocation.getResourceDomain(), modelLocation.getResourcePath());
        if (!cache.containsKey(file)) {
            IResource resource = null;
            try {
                try {
                    resource = manager.getResource(file);
                } catch (FileNotFoundException e) {
                    if (modelLocation.getResourcePath().startsWith("models/block/")) {
                        resource = manager.getResource(new ResourceLocation(file.getResourceDomain(), "models/item/" + file.getResourcePath().substring("models/block/".length())));
                    } else if(modelLocation.getResourcePath().startsWith("models/item/")) {
                        resource = manager.getResource(new ResourceLocation(file.getResourceDomain(), "models/block/" + file.getResourcePath().substring("models/item/".length())));
                    } else {
                        throw e;
                    }
                    GroovysonObjectModelParser parser = new GroovysonObjectModelParser(resource, manager);
                    GroovysonObjectModel model = null;
                    /*try {
                        model = parser.parse();
                    }
                   // catch (Exception e) {
                        errors.put(modelLocation, e);
                    }
                    finally {
                        cache.put(modelLocation, model);
                    }*/
                }
            } finally {
               // cache.put(modelLocation, model);
            }
        }

        GroovysonObjectModel model = cache.get(file);
        if (model == null) {
            throw new ModelLoaderRegistry.LoaderException("Error loading model previously: " + file, errors.get(modelLocation))
        };
        return model;
    }
}
