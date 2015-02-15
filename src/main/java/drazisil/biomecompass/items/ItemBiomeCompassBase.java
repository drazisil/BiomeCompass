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

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import drazisil.biomecompass.BiomeCompass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemBiomeCompassBase extends Item
{

    protected static final Logger logger = LogManager.getLogger("BiomeCompass");

    // This gets overridden in implementations
    private int scanRadius = 1;

    private boolean hasTP = false;

    public ItemBiomeCompassBase()
    {
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
            if (!equippedItemStack.getDisplayName().equals(equippedItemStack.getItem().getItemStackDisplayName(equippedItemStack))){
                // Item has metadata, so it was probably renamed
                String currentEquippedItemName = equippedItemStack.getDisplayName();

                if (!world.isRemote){
                    if (isValidBiomeName(currentEquippedItemName)){
                        // Valid biome name
                        // Search for biome matching name
                        if ((scanForBiomeMatch(player, getScanRadius(), currentEquippedItemName))
                                && (equippedItemStack.getItem() instanceof ItemBiomeCompassEnhanced)){
                            // if this is a single-use compass, return vanilla compass
                            ItemBiomeCompassBasic itemBiomeCompass1 = new ItemBiomeCompassBasic();
                            return new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, itemBiomeCompass1.getUnlocalizedName())).setStackDisplayName(currentEquippedItemName);
                        }
                    }
                    else
                    {
                        // Invalid Biome name
                        String msgBiomeCompassInvalidBiomeName = StatCollector.translateToLocalFormatted("strInvalidBiomeName", currentEquippedItemName) ;

                        player.addChatMessage(new ChatComponentText(msgBiomeCompassInvalidBiomeName));
                    }
                }
            }
            else
            {
                // Does not have a custom name, so 'not activated'
                player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("strBiomeCompassNotActivated")));
            }
        }
        return equippedItemStack;
    }

    public int getScanRadius() {
        return scanRadius;
    }

    public ItemBiomeCompassBase setScanRadius(int scanRadius)
    {
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

        for (BiomeGenBase allBiome : allBiomes)
        {
            if (allBiome != null && allBiome != BiomeGenBase.hell)
            {
                String biomeNameI = allBiome.biomeName.toLowerCase();
                if (biomeNameI.equals(biomeName))
                {
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
    protected boolean scanForBiomeMatch(EntityPlayer player, int scanRadius, String requestedBiomeName)
    {
        int chunkSize = 16;
        World world = player.getEntityWorld();
        int centerX = (int) player.posX;
        int centerZ = (int) player.posZ;

        for(int i=(centerX - (scanRadius * chunkSize)); i<(centerZ + (scanRadius * chunkSize)); i += chunkSize)
        {
            for(int j=(centerZ - (scanRadius * chunkSize)); j<(centerZ + (scanRadius * chunkSize)); j += chunkSize)
            {
                String biomeName = world.getBiomeGenForCoords(i, j).biomeName;
                if (biomeName.toLowerCase().equals(requestedBiomeName))
                {
                    player.addChatMessage(new ChatComponentText(StatCollector.translateToLocalFormatted("strBiomeLocated", biomeName, i, j)));
                    if (canTp() && player.isSneaking())
                    {
                        // Compass can teleport and player is sneaking
                        teleportPlayerToBiome(player, i, j);
                    }
                    return true;
                }
            }
        }
        player.addChatMessage(new ChatComponentText(StatCollector.translateToLocalFormatted("strBiomeNotLocated", requestedBiomeName)));
        return false;

    }

    /**
     * Teleports (player) to a safe Y at (X/Z)
     * @param player EntityPlayer
     * @param x int
     * @param z int
     */
    protected void teleportPlayerToBiome(EntityPlayer player, int x, int z)
    {
        int safeY = player.getEntityWorld().getTopSolidOrLiquidBlock(x, z);
        if (player.worldObj.getBlock(x, safeY, z).isAir(player.worldObj, x, safeY, z))
        {
            // A safe air block was found
            player.addChatMessage(new ChatComponentText(StatCollector.translateToLocalFormatted("strSafeLocation", safeY)));
            if (!player.getEntityWorld().isRemote)
            {
                player.setPositionAndUpdate(x, safeY, z);
            }

        }
        else
        {
            // A safe Y location was not found
            player.addChatMessage(new ChatComponentText(StatCollector.translateToLocalFormatted("strUnsafeLocation", safeY)));
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
