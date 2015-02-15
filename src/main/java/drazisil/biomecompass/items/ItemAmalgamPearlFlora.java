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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ItemAmalgamPearlFlora extends  ItemAmalgamPearl
{

    public ItemAmalgamPearlFlora()
    {

        /* Set name */
        setUnlocalizedName(BiomeCompass.MODID + "_amalgampearl_flora");

        /* Set texture */
        setTextureName(BiomeCompass.MODID + ":amalgampearl_flora");

         /* Make available in creative mode */
         setCreativeTab(CreativeTabs.tabMisc);
    }

    @Override
    public void registerRecipes()
    {
        super.registerRecipes();
        ItemStack stackEnderPearl = new ItemStack(Items.ender_pearl);
        ItemStack stackJungleSapling = new ItemStack(Blocks.sapling, 1, 3);
        ItemStack stackSunflower = new ItemStack(Blocks.double_plant);
        ItemStack stackAcaciaSapling = new ItemStack(Blocks.sapling, 1, 4);
        ItemStack stackCactus = new ItemStack(Blocks.cactus);
        ItemStack stackWatermelonBlock = new ItemStack(Blocks.melon_block);
        ItemStack stackDarkOakSapling = new ItemStack(Blocks.sapling, 1, 5);
        ItemStack stackLilyPad = new ItemStack(Blocks.waterlily);
        ItemStack stackSpruceSapling = new ItemStack(Blocks.sapling, 1, 1);

        GameRegistry.addRecipe(new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, getUnlocalizedName())),
                "abc",
                "def",
                "ghi",
                'a', stackJungleSapling,
                'b', stackSunflower,
                'c', stackAcaciaSapling,
                'd', stackCactus,
                'e', stackEnderPearl,
                'f', stackWatermelonBlock,
                'g', stackDarkOakSapling,
                'h', stackLilyPad,
                'i', stackSpruceSapling);
    }
}
