package com.drazisil.biomecompass.items;

import com.drazisil.biomecompass.BiomeCompass;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBiomeCompass extends Item {
    public ItemBiomeCompass() {
        setUnlocalizedName(BiomeCompass.MODID + "_biomeCompass");
        setTextureName(BiomeCompass.MODID + ":biomeCompass");
        setCreativeTab(CreativeTabs.tabMisc);
    }
}
