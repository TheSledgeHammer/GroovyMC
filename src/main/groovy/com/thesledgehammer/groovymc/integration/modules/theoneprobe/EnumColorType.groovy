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
package com.thesledgehammer.groovymc.integration.modules.theoneprobe

import net.minecraft.util.IStringSerializable

import java.awt.*

enum EnumColorType implements IStringSerializable {
    FE(new Color(0xff555555), new Color(0x000000), new Color(0xEE2C2C), new Color(0xff4300)),
    EU(new Color(0xff555555), new Color(0x000000), new Color(0xFFFF00), new Color(0xCCCC00)),
    MJ(new Color(0xff555555), new Color(0x000000), new Color(0x00CD66), new Color(0x009A33)),
    EMC(new Color(0xff555555), new Color(0x000000), new Color(0x470E51), new Color(0xC60310));

    static final EnumColorType[] VALUES = values();

    public final String name;
    public final int borderColor;
    public final int backgroundColor;
    public final int filledColor;
    public final int alternateFilledColor;

    EnumColorType(Color borderColor, Color backgroundColor, Color filledColor, Color alternateFilledColor) {
        this.name = toString().toLowerCase(Locale.ENGLISH);
        this.borderColor = borderColor.getRGB();
        this.backgroundColor = backgroundColor.getRGB();
        this.filledColor = filledColor.getRGB();
        this.alternateFilledColor = alternateFilledColor.getRGB();
    }

    @Override
    String getName() {
        return name
    }

    int getBorderColor() {
        return borderColor;
    }

    int getBackgroundColor() {
        return backgroundColor;
    }

    int getFilledColor() {
        return filledColor;
    }

    int getAlternateFilledColor() {
        return alternateFilledColor;
    }
}