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

<<<<<<< HEAD
package com.thesledgehammer.groovymc.api.modules

import net.minecraft.client.renderer.texture.TextureMap
import net.minecraftforge.client.event.ModelBakeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

interface IRenderEventModule {

    @SideOnly(Side.CLIENT)
    void onModelBake(ModelBakeEvent event);

    @SideOnly(Side.CLIENT)
    void onTextureStitchPre(TextureMap textureMap);
}
=======
<<<<<<< HEAD:src/main/groovy/com/thesledgehammer/groovymc/modules/ModuleContainers.groovy
package com.thesledgehammer.groovymc.modules

import com.thesledgehammer.groovymc.api.minecraftjoules.CapabilityMj

class ModuleContainers {

    static void preInit() {
        CapabilityMj.register();
        CompatModuleContainer.preInit();
        RenderEventModuleContainer.preInit();
    }

    static void Init() {
        CompatModuleContainer.Init();
        RenderEventModuleContainer.Init();
    }

    static void postInit() {
        CompatModuleContainer.postInit();
        RenderEventModuleContainer.postInit();
    }
}
=======
package com.thesledgehammer.groovymc.api.modules

import net.minecraft.client.renderer.texture.AtlasTexture
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.event.ModelBakeEvent

interface IRenderEventModule {

    @OnlyIn(Dist.CLIENT)
    void onModelBake(ModelBakeEvent event);

    @OnlyIn(Dist.CLIENT)
    void onTextureStitchPre(AtlasTexture map);
}
>>>>>>> 1.16.x:src/main/groovy/com/thesledgehammer/groovymc/api/modules/IRenderEventModule.groovy
>>>>>>> 1.16.x
