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

package com.drazisil.biomecompass.client.items;

import com.drazisil.biomecompass.BiomeCompass;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemBiomeCompass extends Item {

    protected String iconString;
    private IIcon[] Textures = new IIcon[4];

    public ItemBiomeCompass() {
        setUnlocalizedName(BiomeCompass.MODID + "_biomeCompass");
        setTextureName(BiomeCompass.MODID + ":biomeCompass_0");
        setCreativeTab(CreativeTabs.tabMisc);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(this.getIconString());

        // example of different textures assigned to a number
        this.Textures[0] = par1IconRegister.registerIcon(BiomeCompass.MODID + ":biomeCompass_0");
        this.Textures[1] = par1IconRegister.registerIcon(BiomeCompass.MODID + ":biomeCompass_90");
        this.Textures[2] = par1IconRegister.registerIcon(BiomeCompass.MODID + ":biomeCompass_180");
        this.Textures[3] = par1IconRegister.registerIcon(BiomeCompass.MODID + ":biomeCompass_270");
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        return p_77659_1_;
    }

}
