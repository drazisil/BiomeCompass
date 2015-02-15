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

public class ItemAmalgamPearlEarthen extends  ItemAmalgamPearl
{

    public ItemAmalgamPearlEarthen()
    {

        /* Set name */
        setUnlocalizedName(BiomeCompass.MODID + "_amalgampearl_earthen");

        /* Set texture */
        setTextureName(BiomeCompass.MODID + ":amalgampearl_earthen");

        /* Make available in creative mode */
        setCreativeTab(CreativeTabs.tabMisc);
    }

    @Override
    public void registerRecipes()
    {
        super.registerRecipes();
        ItemStack stackEnderPearl = new ItemStack(Items.ender_pearl);
        ItemStack stackDirt = new ItemStack(Blocks.dirt);
        ItemStack stackGravel = new ItemStack(Blocks.gravel);
        ItemStack stackClay = new ItemStack(Blocks.clay);
        ItemStack stackGrass = new ItemStack(Blocks.grass);
        ItemStack stackStone = new ItemStack(Blocks.stone);
        ItemStack stackSnow = new ItemStack(Blocks.snow);
        ItemStack stackSand = new ItemStack(Blocks.sand);
        ItemStack stackSandStone = new ItemStack(Blocks.sandstone);

        GameRegistry.addRecipe(new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, getUnlocalizedName())),
                "abc",
                "def",
                "ghi",
                'a', stackDirt,
                'b', stackGravel,
                'c', stackClay,
                'd', stackGrass,
                'e', stackEnderPearl,
                'f', stackStone,
                'g', stackSnow,
                'h', stackSand,
                'i', stackSandStone);
    }
}
