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

package drazisil.biomecompass;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import drazisil.biomecompass.proxy.BCCommonProxy;

@Mod(modid = BiomeCompass.MODID, name = BiomeCompass.NAME, version = BiomeCompass.VERSION)
public class BiomeCompass
{
    public static final String MODID = "biomecompass";
    public static final String NAME = "Biome Compass";
    public static final String VERSION = "1.6.1";

    @SidedProxy(clientSide = "drazisil.biomecompass.proxy.BCClientProxy", serverSide = "drazisil.biomecompass.proxy.BCDedicatedServerProxy")
    public static BCCommonProxy proxy;

    //BiomeCompassEventHandler events = new BiomeCompassEventHandler();

    @EventHandler
    public void preInitialization(FMLPreInitializationEvent event){
        proxy.preInit(event);
    }


    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        proxy.serverLoad(event);
    }



}
