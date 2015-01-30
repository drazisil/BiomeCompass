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
         * Jump with compass
		 */
        if (e.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || e.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
        {
            if (e.entityPlayer.getCurrentEquippedItem() != null && FMLCommonHandler.instance().getEffectiveSide().isServer())
            {
                if (e.entityPlayer.getCurrentEquippedItem().getItem() instanceof ItemBiomeCompass)
                {
                    logger.info("Clicked with Biome Compass");
                }
            }
        }
    }

}
