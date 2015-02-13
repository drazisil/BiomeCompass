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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemAmalgamPearlFlora extends  ItemAmalgamPearl{

    protected static final Logger logger = LogManager.getLogger("BiomeCompass");

    public ItemAmalgamPearlFlora() {

        /* Set name */
        setUnlocalizedName(BiomeCompass.MODID + "_amalgampearl_flora");

        /* Set texture */
        setTextureName(BiomeCompass.MODID + ":amalgampearl_flora");

         /* Make available in creative mode */
         setCreativeTab(CreativeTabs.tabMisc);
    }

    @Override
    public void registerRecipes() {
        super.registerRecipes();
        ItemStack stackEnderPearl = new ItemStack(Items.ender_pearl);
        ItemStack stackJungleSapling = new ItemStack(Blocks.sapling, 1, 3);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);
        //ItemStack stackObsidian = new ItemStack(Blocks.obsidian);

        GameRegistry.addRecipe(new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, getUnlocalizedName())),
                "xxx",
                "xyx",
                "xxx",
                'x', stackJungleSapling,
                'y', stackEnderPearl);


    }
}
