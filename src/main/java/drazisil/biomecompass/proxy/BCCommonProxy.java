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

package drazisil.biomecompass.proxy;

import drazisil.biomecompass.items.ItemRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.common.config.Configuration;

public class BCCommonProxy {

    public boolean isClient() {
        return false;
    }

    public int getScanRange() { return scanRange; }

    public void setScanRange(int scanRange) { this.scanRange = scanRange; }

    private int scanRange;

    public boolean isDebugging() {
        return debugging;
    }

    public void setDebugging(boolean debugging) {
        this.debugging = debugging;
    }

    private boolean debugging;

    public void preInit(FMLPreInitializationEvent event) {
        // Not currently using events at the global level, but leaving the code for reference
        //FMLCommonHandler.instance().bus().register(events);
        //MinecraftForge.EVENT_BUS.register(events);

        // Get the configuration
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());

        config.load();

        setScanRange(config.get("general", "scanRange", 25).getInt(25));
        setDebugging(config.get("general", "debugging", false).getBoolean(false));
        config.save();

        ItemRegistry itemRegistry = new ItemRegistry();
        itemRegistry.registerItems();
        itemRegistry.registerRecipes();
    }

    public void init(FMLInitializationEvent event) { }

    public void postInit(FMLPostInitializationEvent event) { }

    public void serverLoad(FMLServerStartingEvent event) { }

}
