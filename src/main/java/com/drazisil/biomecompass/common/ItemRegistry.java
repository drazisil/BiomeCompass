/*
 * Copyright 2015 Joseph W Becher <jwbecher@drazisil.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.drazisil.biomecompass.common;

import com.drazisil.biomecompass.BiomeCompass;
import com.drazisil.biomecompass.client.items.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ItemRegistry {

    public void registerItems(){

        int scanRange = BiomeCompass.proxy.getScanRange();

        GameRegistry.registerItem(new ItemBiomeCompass1(scanRange), BiomeCompass.MODID + "_biomeCompass1");
        GameRegistry.registerItem(new ItemBiomeCompass2(scanRange), BiomeCompass.MODID + "_biomeCompass2");

        /* Registering the amalgam pearls */
        GameRegistry.registerItem(new ItemAmalgamPearlFlora(), BiomeCompass.MODID + "_itemAmalgamPearlFlora");
        GameRegistry.registerItem(new ItemAmalgamPearlFauna(), BiomeCompass.MODID + "_itemAmalgamPearlFauna");
        GameRegistry.registerItem(new ItemAmalgamPearlEarthen(), BiomeCompass.MODID + "_itemAmalgamPearlEarthen");
        GameRegistry.registerItem(new ItemAmalgamPearlPrecious(), BiomeCompass.MODID + "_itemAmalgamPearlPrecious");
    }

    public void registerRecipes(){

        GameRegistry.addRecipe(new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, BiomeCompass.MODID + "_biomeCompass1")),
                " x ",
                "xyx",
                " x ",
                'x', new ItemStack(Items.map),
                'y', new ItemStack(Items.compass));

        GameRegistry.addRecipe(new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, BiomeCompass.MODID + "_biomeCompass2")),
                "zxz",
                "xyx",
                "zxz",
                'x', new ItemStack(Items.ender_eye),
                'y', new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, BiomeCompass.MODID + "_biomeCompass1")),
                'z', new ItemStack(Blocks.obsidian));

        ItemStack stackEnderPearl = new ItemStack(Items.ender_pearl);
        ItemStack stackJungleSapling = new ItemStack(Blocks.sapling, 1, 3);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);

        GameRegistry.addRecipe(new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, BiomeCompass.MODID + "_itemAmalgamPearlFlora")),
                "xxx",
                "xyx",
                "xxx",
                'x', stackJungleSapling,
                'y', stackEnderPearl);



    }
}
