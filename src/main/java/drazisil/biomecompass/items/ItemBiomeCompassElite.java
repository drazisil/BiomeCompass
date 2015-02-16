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

public class ItemBiomeCompassElite extends ItemBiomeCompassBase
{

    public ItemBiomeCompassElite()
    {
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
    public void registerRecipes()
    {
        super.registerRecipes();
        ItemBiomeCompassEnhanced itemBiomeCompassEnhanced = new ItemBiomeCompassEnhanced();
        ItemStack stackAmalgamPearlFlora = new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, new ItemAmalgamPearl().getUnlocalizedName()), 1, 0);
        ItemStack stackAmalgamPearlFauna = new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, new ItemAmalgamPearl().getUnlocalizedName()), 1, 1);
        ItemStack stackAmalgamPearlEarthen = new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, new ItemAmalgamPearl().getUnlocalizedName()), 1, 2);
        ItemStack stackAmalgamPearlPrecious = new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, new ItemAmalgamPearl().getUnlocalizedName()), 1, 3);
        ItemStack stackBiomeCompassEnhanced = new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, itemBiomeCompassEnhanced.getUnlocalizedName()));

        GameRegistry.addRecipe(new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, getUnlocalizedName())),
                " a ",
                "bcd",
                " e ",
                'a', stackAmalgamPearlFlora,
                'b', stackAmalgamPearlFauna,
                'c', stackBiomeCompassEnhanced,
                'd', stackAmalgamPearlEarthen,
                'e', stackAmalgamPearlPrecious);

    }
}
