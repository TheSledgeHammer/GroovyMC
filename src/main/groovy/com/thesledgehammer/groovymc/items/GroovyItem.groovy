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

package com.thesledgehammer.groovymc.items

<<<<<<< HEAD
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

class GroovyItem extends Item {

    GroovyItem() {

    }

    ItemStack getWildcard() {
        return new ItemStack(this, 1, OreDictionary.WILDCARD_VALUE);
=======

import net.minecraft.item.Item
import net.minecraft.item.ItemGroup

class GroovyItem extends Item {

    GroovyItem(Properties properties) {
        super(properties);
    }

    GroovyItem(ItemGroup itemGroup) {
        super(new Properties().group(itemGroup));
>>>>>>> 1.16.x
    }
}
