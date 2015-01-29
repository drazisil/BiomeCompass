package com.drazisil.biomecompass.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

/**
 * Created by joseph on 8/11/2014.
 */
public class BCEventHandler {
    public void register() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void OnEntityJoinWorld(EntityJoinWorldEvent event) {
    }
}
