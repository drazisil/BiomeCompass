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
        ItemBiomeCompassBase itemBiomeCompass1 = new ItemBiomeCompass1(scanRange);
        String nameItemBiomeCompass1 = BiomeCompass.MODID + "_biomeCompass1";
        GameRegistry.registerItem(itemBiomeCompass1, nameItemBiomeCompass1);

        /*
         Register the recipe
          */
        ItemStack stackCompass = new ItemStack(Items.compass);
        ItemStack stackMap = new ItemStack(Items.map);

        GameRegistry.addRecipe(new ItemStack(itemBiomeCompass1), " x ", "xyx", " x ",
                'x', stackMap, 'y', stackCompass);

        /*
        Registering the item
         */
        ItemBiomeCompassBase itemBiomeCompass2 = new ItemBiomeCompass2(scanRange);
        String nameItemBiomeCompass2 = BiomeCompass.MODID + "_biomeCompass2";
        GameRegistry.registerItem(itemBiomeCompass2, nameItemBiomeCompass2);

        /*
         Register the recipe
          */
        ItemStack stackBiomeCompass = new ItemStack(itemBiomeCompass1);
        ItemStack stackEnderEye = new ItemStack(Items.ender_eye);
        ItemStack stackObsidian = new ItemStack(Blocks.obsidian);

        GameRegistry.addRecipe(new ItemStack(itemBiomeCompass2), "zxz", "xyx", "zxz",
                'x', stackEnderEye, 'y', stackBiomeCompass, 'z', stackObsidian);


        /* Registering the amalgam pearls */
        /* flora */
        ItemAmalgamPearlFlora itemAmalgamPearlFlora = new ItemAmalgamPearlFlora();
        String itemAmalgamPearlFloraName = BiomeCompass.MODID + "_itemAmalgamPearlFlora";
        GameRegistry.registerItem(itemAmalgamPearlFlora, itemAmalgamPearlFloraName);

        /* fauna */
        ItemAmalgamPearlFauna itemAmalgamPearlFauna = new ItemAmalgamPearlFauna();
        String itemAmalgamPearlFaunaName = BiomeCompass.MODID + "_itemAmalgamPearlFauna";
        GameRegistry.registerItem(itemAmalgamPearlFauna, itemAmalgamPearlFaunaName);

        /* earthen */
        ItemAmalgamPearlEarthen itemAmalgamPearlEarthen = new ItemAmalgamPearlEarthen();
        String itemAmalgamPearlEarthenName = BiomeCompass.MODID + "_itemAmalgamPearlEarthen";
        GameRegistry.registerItem(itemAmalgamPearlEarthen, itemAmalgamPearlEarthenName);


        /* precious */
        ItemAmalgamPearlPrecious itemAmalgamPearlPrecious = new ItemAmalgamPearlPrecious();
        String itemAmalgamPearlPraciousName = BiomeCompass.MODID + "_itemAmalgamPearlPrecious";
        GameRegistry.registerItem(itemAmalgamPearlPrecious, itemAmalgamPearlPraciousName);


        /*
         Register the recipe
          */
        //ItemStack amalgamPearlFloraStack = new ItemStack(itemAmalgamPearlFlora);
        ItemStack enderPearlStack = new ItemStack(Items.ender_pearl);
        ItemStack jungleSapplingStack = new ItemStack(Blocks.sapling, 1, 3);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);

        GameRegistry.addRecipe(new ItemStack(itemAmalgamPearlFlora), "xxx", "xyx", "xxx",
                'x', enderPearlStack, 'y', jungleSapplingStack);



    }
}
