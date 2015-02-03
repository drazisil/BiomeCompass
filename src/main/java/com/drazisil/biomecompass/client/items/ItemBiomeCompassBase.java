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
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemBiomeCompassBase extends Item {

    protected static final Logger logger = LogManager.getLogger("BiomeCompass");

    protected IIcon[] Textures = new IIcon[4];

    // This gets overridden in implementations
    private int scanRadius = 1;

    public ItemBiomeCompassBase() {
        /*
        Set name
         */
        setUnlocalizedName(BiomeCompass.MODID + "_biomeCompass");
        /*
        Set texture
         */
        setTextureName(BiomeCompass.MODID + ":biomeCompass_0");
        /*
        Set default of 1 chunk around player
         */
        scanRadius = 1;
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
    public ItemStack onItemRightClick(ItemStack equippedItemStack, World world, EntityPlayer player)
    {

        if (FMLCommonHandler.instance().getEffectiveSide().isClient())
        {
            return equippedItemStack;
        }

        /*
            Only run on server
             */
        if (player.getCurrentEquippedItem() != null && FMLCommonHandler.instance().getEffectiveSide().isServer())
            {
                    logger.info("Clicked with Biome Compass");

                    /*
                    Get player's location
                     */
                int senderX = (int) Math.floor(player.posX);
                int senderZ = (int) Math.floor(player.posZ);

                    /*
                     Check for custom name
                      */
                    //logger.info("Clicked with Biome Compass: " + equippedItemStack.getItem().getItemStackDisplayName(equippedItemStack) + " / " + equippedItemStack.hasTagCompound());
                    if (equippedItemStack.hasTagCompound()){
                        /*
                        Item has metadata, so it was probably renamed
                         */
                        NBTTagCompound currentEquippedItemTags = equippedItemStack.getTagCompound();
                        String currentEquippedItemName = currentEquippedItemTags.getCompoundTag("display").getString("Name").toLowerCase();

                        /*
                        Get an array of all biomes
                         */
                        BiomeGenBase[] allBiomes = BiomeGenBase.getBiomeGenArray();

                        if (!world.isRemote){
                            boolean biomeExists = checkIfBiomeExists(allBiomes, currentEquippedItemName);
                            if (biomeExists){
                                /*
                                Valid biome name
                                 */
                                logger.info(currentEquippedItemName + " is a valid biome");

                                /*
                                Search for biome matching name
                                 */
                                scanForBiomeMatch(player, senderX, senderZ, getScanRadius(), currentEquippedItemName);
                            } else {
                                /*
                                Invalid Biome name
                                 */
                                //logger.info(currentEquippedItemName + " is NOT a valid biome");
                                String msgBiomeCompassInvalidBiomeName = currentEquippedItemName + " is not a valid biome. Did you spell it correctly?";
                                player.addChatMessage(new ChatComponentText(msgBiomeCompassInvalidBiomeName));
                            }

                        }


                    } else {
                        // Does not have a custom name, so 'not activated'
                        String msgBiomeCompassNotActivated = "Please activate your compass by renaming it to the biome you are seeking.";
                        player.addChatMessage(new ChatComponentText(msgBiomeCompassNotActivated));
                    }
                }
        return equippedItemStack;
        }

    public Item setTextureName(String p_111206_1_)
    {
        this.iconString = p_111206_1_;
        return this;
    }

    public int getScanRadius() {
        return scanRadius;
    }

    public void setScanRadius(int scanRadius) {
        this.scanRadius = scanRadius;
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


    protected void scanForBiomeMatch(EntityPlayer player, int centerX, int centerZ, int scanRadius, String requestedBiomeName){
        int chunkSize = 16;
        World world = player.getEntityWorld();

        logger.info("Searching " + scanRadius + " chunks around " + centerX + "," + centerZ + " for a " + requestedBiomeName);

        for(int i=(centerX - (scanRadius * chunkSize)); i<(centerZ + (scanRadius * chunkSize)); i += chunkSize){
            for(int j=(centerZ - (scanRadius * chunkSize)); j<(centerZ + (scanRadius * chunkSize)); j += chunkSize){
                String biomeName = world.getBiomeGenForCoords(i, j).biomeName;
                if (biomeName.toLowerCase().equals(requestedBiomeName)) {
                    logger.info("A " + biomeName + " biome was located at " + i + "," + j);
                    if (hasTP()){
                        tpPlayertoBiome(player, i, j);
                    }
                    player.addChatMessage(new ChatComponentText("A " + biomeName + " biome was located at " + i + "," + j));
                    return;
                }
            }
        }
        logger.info("A "+ requestedBiomeName + " biome was not located within the search radius.");
        ChatComponentText msg =new ChatComponentText("A "+ requestedBiomeName
                + " biome was not located within the search radius.");
        player.addChatMessage(msg);

    }

    protected boolean hasTP(){
        return false;
    }


    protected void tpPlayertoBiome(EntityPlayer player, int x, int z){
        int safeY = player.getEntityWorld().getTopSolidOrLiquidBlock(x, z);
        if (player.worldObj.getBlock(x, safeY, z).isAir(player.worldObj, x, safeY, z)){
            logger.info("y=" + safeY + " may be a safe place.");
            player.setPosition(x, safeY, z);

        } else {
            logger.info("y=" + safeY + " may be a safe place....but it's registered as not air.");
        }

    }

}
