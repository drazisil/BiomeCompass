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

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemAmalgamPearl extends Item {

    protected static final Logger logger = LogManager.getLogger("BiomeCompass");

    public ItemAmalgamPearl() {

    }

    /**
    * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
    */

    public ItemStack onItemRightClick(ItemStack equippedItemStack, World world, EntityPlayer player) {

        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            return equippedItemStack;
        }

        /*
            Only run on server
             */
        if (player.getCurrentEquippedItem() != null && FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            return equippedItemStack;
        }
        return equippedItemStack;
    }

}

