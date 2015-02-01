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

package com.drazisil.biomecompass.event;

import com.drazisil.biomecompass.client.items.ItemBiomeCompass;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BiomeCompassEventHandler {

    private static final Logger logger = LogManager.getLogger("BiomeCompass");



    @SubscribeEvent
    public void playerInteractEvent(PlayerInteractEvent e)
    {
        if (FMLCommonHandler.instance().getEffectiveSide().isClient())
        {
            return;
        }

		/*
         * TODO: find a way for it to not repeat
		 */
        if (e.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR)
        {
            if (e.entityPlayer.getCurrentEquippedItem() != null && FMLCommonHandler.instance().getEffectiveSide().isServer())
            {
                ItemStack currentEquippedItemStack = e.entityPlayer.getCurrentEquippedItem();
                if (currentEquippedItemStack.getItem() instanceof ItemBiomeCompass)
                {
                    logger.info("Clicked with Biome Compass");

                    EntityPlayer player = e.entityPlayer;
                    World world = player.getEntityWorld();

                    ChunkCoordinates playerCoordinates = player.getPlayerCoordinates();
                    int senderX = playerCoordinates.posX;
                    int senderZ = playerCoordinates.posZ;


                    // Check for custom name
                    logger.info("Clicked with Biome Compass: " + currentEquippedItemStack.getItem().getItemStackDisplayName(currentEquippedItemStack) + " / " + currentEquippedItemStack.hasTagCompound());
                    if (currentEquippedItemStack.hasTagCompound()){
                        // Has metadata
                        // TODO: Check to make sure matadata is actually the name
                        NBTTagCompound currentEquippedItemTags = currentEquippedItemStack.getTagCompound();
                        String currentEquippedItemName = currentEquippedItemTags.getCompoundTag("display").getString("Name").toLowerCase();
                        int scanRadius = ((ItemBiomeCompass) currentEquippedItemStack.getItem()).getScanRadius();


                        BiomeGenBase[] allBiomes = BiomeGenBase.getBiomeGenArray();

                        if (!world.isRemote){
                            boolean biomeExists = checkIfBiomeExists(allBiomes, currentEquippedItemName);
                            if (biomeExists){
                                logger.info(currentEquippedItemName + " is a valid biome");
                                scanForBiomeMatch(player, senderX, senderZ, scanRadius, currentEquippedItemName);
                            } else {
                                //logger.info(currentEquippedItemName + " is NOT a valid biome");
                                player.addChatMessage(new ChatComponentText(currentEquippedItemName + " is not a valid biome. Did you spell it correctly?"));
                            }

                        }


                    } else {
                        if (!world.isRemote) {
                            player.addChatMessage(new ChatComponentText("Please activate your compass by renaming it to the biome you are seeking."));
                        }
                    }
                }
            }
            e.setCanceled(true);
        }
    }

    private boolean checkIfBiomeExists(BiomeGenBase[] allBiomes, String biomeName ){

        for (BiomeGenBase allBiome : allBiomes) {
            if (allBiome != null && allBiome != BiomeGenBase.hell) {
                String biomeNameI = allBiome.biomeName.toLowerCase();
                if (biomeNameI.equals(biomeName)) {
                    return true;
                }
            }

        }

        return false;

    }

    public void scanForBiomeMatch(ICommandSender sender, int centerX, int centerZ, int scanRadius, String requestedBiomeName){
        int chunkSize = 16;
        World world = sender.getEntityWorld();
        for(int i=(centerX - (scanRadius * chunkSize)); i<(centerZ + (scanRadius * chunkSize)); i += chunkSize){
            for(int j=(centerZ - (scanRadius * chunkSize)); j<(centerZ + (scanRadius * chunkSize)); j += chunkSize){
                String biomeName = world.getBiomeGenForCoords(i, j).biomeName;
                if (biomeName.toLowerCase().equals(requestedBiomeName)) {
                    if (!world.isRemote) {
                        sender.addChatMessage(new ChatComponentText("A " + biomeName + " biome was located at " + i + "," + j));
                    }
                    return;
                }
            }
        }
        //logger.info("A "+ requestedBiomeName + " biome was not located within the search radius.");
        ChatComponentText msg =new ChatComponentText("A "+ requestedBiomeName
                + " biome was not located within the search radius.");
        sender.addChatMessage(msg);

    }


}
