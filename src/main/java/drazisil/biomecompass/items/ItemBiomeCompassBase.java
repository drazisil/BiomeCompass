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

import drazisil.biomecompass.BiomeCompass;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemBiomeCompassBase extends Item {

    protected static final Logger logger = LogManager.getLogger("BiomeCompass");

    // This gets overridden in implementations
    private int scanRadius = 1;

    private boolean hasTP = false;

    public ItemBiomeCompassBase() {
        // Set name
        setUnlocalizedName(BiomeCompass.MODID + "_biomeCompass");
        // Set texture
        setTextureName(BiomeCompass.MODID + ":biomeCompassBase");
        // Set default of 1 chunk around player
        scanRadius = 1;
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

        // Only run on server
        if (player.getCurrentEquippedItem() != null && FMLCommonHandler.instance().getEffectiveSide().isServer())
        {
            logger.info("Clicked with Biome Compass");

            // Check for custom name
            //logger.info("Clicked with Biome Compass: " + equippedItemStack.getItem().getItemStackDisplayName(equippedItemStack) + " / " + equippedItemStack.hasTagCompound());
            if (equippedItemStack.hasTagCompound()){
                // Item has metadata, so it was probably renamed
                NBTTagCompound currentEquippedItemTags = equippedItemStack.getTagCompound();
                String currentEquippedItemName = currentEquippedItemTags.getCompoundTag("display").getString("Name").toLowerCase();

                if (!world.isRemote){
                    if (isValidBiomeName(currentEquippedItemName)){
                        // Valid biome name
                        logger.info(currentEquippedItemName + " is a valid biome");

                        // Search for biome matching name
                        scanForBiomeMatch(player, getScanRadius(), currentEquippedItemName);
                    } else {
                        // Invalid Biome name
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

    public int getScanRadius() {
        return scanRadius;
    }

    public ItemBiomeCompassBase setScanRadius(int scanRadius) {
        this.scanRadius = scanRadius;
        return this;
    }

    /**
     * Looks though a list of all biomes to see of (biomeName) is a valid match
     * @param biomeName String
     * @return Boolean
     */
    private boolean isValidBiomeName(String biomeName)
    {
        // Get an array of all biomes
        BiomeGenBase[] allBiomes = BiomeGenBase.getBiomeGenArray();

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

    /**
     * Searches (scanRadius) around the (player)'s X/Z position
     * @param player EntityPlayer
     * @param scanRadius int
     * @param requestedBiomeName String
     */
    protected void scanForBiomeMatch(EntityPlayer player, int scanRadius, String requestedBiomeName)
    {
        int chunkSize = 16;
        World world = player.getEntityWorld();
        int centerX = (int) player.posX;
        int centerZ = (int) player.posZ;

        logger.info("Searching " + scanRadius + " chunks around " + centerX + "," + centerZ + " for a " + requestedBiomeName);

        for(int i=(centerX - (scanRadius * chunkSize)); i<(centerZ + (scanRadius * chunkSize)); i += chunkSize){
            for(int j=(centerZ - (scanRadius * chunkSize)); j<(centerZ + (scanRadius * chunkSize)); j += chunkSize){
                String biomeName = world.getBiomeGenForCoords(i, j).biomeName;
                if (biomeName.toLowerCase().equals(requestedBiomeName)) {
                    logger.info("A " + biomeName + " biome was located at " + i + "," + j);
                    player.addChatMessage(new ChatComponentText("A " + biomeName + " biome was located at " + i + "," + j));
                    if (canTp()){
                        tpPlayertoBiome(player, i, j);
                    }
                    return;
                }
            }
        }
        logger.info("A "+ requestedBiomeName + " biome was not located within the search radius.");
        ChatComponentText msg =new ChatComponentText("A "+ requestedBiomeName
                + " biome was not located within the search radius.");
        player.addChatMessage(msg);

    }

    protected void tpPlayertoBiome(EntityPlayer player, int x, int z)
    {
        int safeY = player.getEntityWorld().getTopSolidOrLiquidBlock(x, z);
        if (player.worldObj.getBlock(x, safeY, z).isAir(player.worldObj, x, safeY, z))
        {
            //logger.info("y=" + safeY + " may be a safe place.");
            ChatComponentText msg =new ChatComponentText("y=" + safeY + " may be a safe place. Teleporting...");
            player.addChatMessage(msg);
            if (!player.getEntityWorld().isRemote){
                player.setPositionAndUpdate(x, safeY, z);
            }

        } else {
            //logger.info("y=" + safeY + " may be a safe place....but it's registered as not air.");
            ChatComponentText msg =new ChatComponentText("y=" + safeY + " may be a safe place....but it's not identified as air.");
            player.addChatMessage(msg);

        }

    }

    public boolean canTp() {
        return hasTP;
    }

    public void setHasTP(boolean hasTP) {
        this.hasTP = hasTP;
    }

    public void registerRecipes(){ }

}
