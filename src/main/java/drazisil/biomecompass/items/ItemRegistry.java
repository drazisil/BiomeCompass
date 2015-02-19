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

import cpw.mods.fml.common.registry.GameRegistry;
import drazisil.biomecompass.BiomeCompass;

public class ItemRegistry
{

    public void registerItems()
    {

        int scanRange = BiomeCompass.proxy.getScanRange();

        GameRegistry.registerItem(new ItemBiomeCompassBasic().setScanRadius(scanRange), new ItemBiomeCompassBasic().getUnlocalizedName());
        GameRegistry.registerItem(new ItemBiomeCompassEnhanced().setScanRadius(scanRange), new ItemBiomeCompassEnhanced().getUnlocalizedName());
        GameRegistry.registerItem(new ItemBiomeCompassElite().setScanRadius(scanRange), new ItemBiomeCompassElite().getUnlocalizedName());

        /* Registering the amalgam pearls */
        GameRegistry.registerItem(new ItemAmalgamPearl(), new ItemAmalgamPearl().getUnlocalizedName());
    }

    public void registerRecipes()
    {

        int scanRange = BiomeCompass.proxy.getScanRange();

        // Register the compasses
        ItemBiomeCompassBase itemBiomeCompassBasic = new ItemBiomeCompassBasic().setScanRadius(scanRange);
        itemBiomeCompassBasic.registerRecipes();

        ItemBiomeCompassBase itemBiomeCompassEnhanced = new ItemBiomeCompassEnhanced().setScanRadius(scanRange);
        itemBiomeCompassEnhanced.registerRecipes();

        ItemBiomeCompassBase itemBiomeCompassElite = new ItemBiomeCompassElite().setScanRadius(scanRange);
        itemBiomeCompassElite.registerRecipes();

        // Register the pearls
        ItemAmalgamPearl itemAmalgamPearl = new ItemAmalgamPearl();
        itemAmalgamPearl.registerRecipes();
    }
}
