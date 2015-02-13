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

                /*
        Registering the item
         */
        GameRegistry.registerItem(new ItemBiomeCompass1(scanRange), BiomeCompass.MODID + "_biomeCompass1");

        /*
         Register the recipe
          */
        GameRegistry.addRecipe(new ItemStack(new ItemBiomeCompass1(scanRange)),
                " x ",
                "xyx",
                " x ",
                'x', new ItemStack(Items.map),
                'y', new ItemStack(Items.compass));

        /*
        Registering the item
         */
        GameRegistry.registerItem(new ItemBiomeCompass2(scanRange), BiomeCompass.MODID + "_biomeCompass2");

        /*
         Register the recipe
          */
        ItemStack stackBiomeCompass = new ItemStack(new ItemBiomeCompass1(scanRange));
        GameRegistry.addRecipe(new ItemStack(new ItemBiomeCompass2(scanRange)),
                "zxz",
                "xyx",
                "zxz",
                'x', new ItemStack(Items.ender_eye),
                'y', stackBiomeCompass,
                'z', new ItemStack(Blocks.obsidian));


        /* Registering the amalgam pearls */
        /* flora */
        GameRegistry.registerItem(new ItemAmalgamPearlFlora(), BiomeCompass.MODID + "_itemAmalgamPearlFlora");

        /* fauna */
        GameRegistry.registerItem(new ItemAmalgamPearlFauna(), BiomeCompass.MODID + "_itemAmalgamPearlFauna");

        /* earthen */
        GameRegistry.registerItem(new ItemAmalgamPearlEarthen(), BiomeCompass.MODID + "_itemAmalgamPearlEarthen");


        /* precious */
        GameRegistry.registerItem(new ItemAmalgamPearlPrecious(), BiomeCompass.MODID + "_itemAmalgamPearlPrecious");


        /*
         Register the recipe
          */
        //ItemStack amalgamPearlFloraStack = new ItemStack(itemAmalgamPearlFlora);
        ItemStack stackEnderPearl = new ItemStack(Items.ender_pearl);
        ItemStack stackJungleSappling = new ItemStack(Blocks.sapling, 1, 3);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);

        GameRegistry.addRecipe(new ItemStack(new ItemAmalgamPearlFlora()), "xxx", "xyx", "xxx",
                'x', stackEnderPearl, 'y', stackJungleSappling);



    }
}
