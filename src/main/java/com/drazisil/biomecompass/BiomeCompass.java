package com.drazisil.biomecompass;

import com.drazisil.biomecompass.commands.BiomeCompassCommand;
import com.drazisil.biomecompass.proxy.BCCommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = BiomeCompass.MODID, version = BiomeCompass.VERSION)
public class BiomeCompass
{
    public static final String MODID = "BiomeCompass";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "com.drazisil.biomecompass.proxy.BCClientProxy", serverSide = "com.drazisil.biomecompass.proxy.BCDedicatedServerProxy")
    public static BCCommonProxy proxy;


    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new BiomeCompassCommand());
    }
}
