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

package com.drazisil.biomecompass;

import com.drazisil.biomecompass.client.items.ItemBiomeCompass1;
import com.drazisil.biomecompass.client.items.ItemBiomeCompassBase;
import com.drazisil.biomecompass.proxy.BCCommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = BiomeCompass.MODID, name = BiomeCompass.NAME, version = BiomeCompass.VERSION)
public class BiomeCompass
{
    public static final String MODID = "biomecompass";
    public static final String NAME = "Biome Compass";
    public static final String VERSION = "1.0.1";

    @SidedProxy(clientSide = "com.drazisil.biomecompass.proxy.BCClientProxy", serverSide = "com.drazisil.biomecompass.proxy.BCDedicatedServerProxy")
    public static BCCommonProxy proxy;

    //BiomeCompassEventHandler events = new BiomeCompassEventHandler();

    @EventHandler
    public void preInitialization(FMLPreInitializationEvent event){
        /*
        Not currently using events at the global level, but leaving the code for reference
         */
        //FMLCommonHandler.instance().bus().register(events);
        //MinecraftForge.EVENT_BUS.register(events);

        /*
        Get the configuration
         */
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());

        config.load();
        int scanRange_1 = config.get("general", "scanRange_1", 25).getInt(25);
        config.save();

        /*
        Registering the item
         */
        ItemBiomeCompassBase biomeCompassItem1 = new ItemBiomeCompass1(scanRange_1);
        String itemBiomeCompassName1 = BiomeCompass.MODID + "_biomeCompass1";
        GameRegistry.registerItem(biomeCompassItem1, itemBiomeCompassName1);

        /*
         Register the recipe
          */
        ItemStack compassStack = new ItemStack(Items.compass);
        ItemStack mapStack = new ItemStack(Items.map);

        GameRegistry.addRecipe(new ItemStack(biomeCompassItem1), " x ", "xyx", " x ",
                'x', mapStack, 'y', compassStack);

    }


    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		/*
		Not currently using
		 */

    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
		/*
		Not currently using
		 */
    }



}
