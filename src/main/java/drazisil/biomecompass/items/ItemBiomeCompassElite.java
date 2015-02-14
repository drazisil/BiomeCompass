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

package drazisil.biomecompass.items;

import cpw.mods.fml.common.registry.GameRegistry;
import drazisil.biomecompass.BiomeCompass;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ItemBiomeCompassElite extends ItemBiomeCompassBase {

    public ItemBiomeCompassElite() {
        super();

        /*
         Set name
          */
        setUnlocalizedName(BiomeCompass.MODID + "_biomeCompass_elite");

        /*
         Make available in creative mode
          */
        setCreativeTab(CreativeTabs.tabMisc);

        // Set texture
        setTextureName(BiomeCompass.MODID + ":biomeCompassElite");

        // This compass can tp
        setHasTP(true);

    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass){
        return true;
    }

    @Override
    public void registerRecipes() {
        super.registerRecipes();
        ItemBiomeCompassEnhanced itemBiomeCompassEnhanced = new ItemBiomeCompassEnhanced();
        ItemAmalgamPearlFlora itemAmalgamPearlFlora = new ItemAmalgamPearlFlora();
        ItemAmalgamPearlFauna itemAmalgamPearlFauna = new ItemAmalgamPearlFauna();
        ItemAmalgamPearlEarthen itemAmalgamPearlEarthen = new ItemAmalgamPearlEarthen();
        ItemAmalgamPearlPrecious itemAmalgamPearlPrecious = new ItemAmalgamPearlPrecious();
        ItemStack stackBiomeCompassEnhanced = new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, itemBiomeCompassEnhanced.getUnlocalizedName()));
        ItemStack stackAmalgamFlora = new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, itemAmalgamPearlFlora.getUnlocalizedName()));
        ItemStack stackAmalgamFauna = new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, itemAmalgamPearlFauna.getUnlocalizedName()));
        ItemStack stackAmalgamEarthen = new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, itemAmalgamPearlEarthen.getUnlocalizedName()));
        ItemStack stackAmalgamPrecious = new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, itemAmalgamPearlPrecious.getUnlocalizedName()));

        GameRegistry.addRecipe(new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, getUnlocalizedName())),
                " a ",
                "bcd",
                " e ",
                'a', stackAmalgamFlora,
                'b', stackAmalgamFauna,
                'c', stackBiomeCompassEnhanced,
                'd', stackAmalgamEarthen,
                'e', stackAmalgamPrecious);

    }


}
