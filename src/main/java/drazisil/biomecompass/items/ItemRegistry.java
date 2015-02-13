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

public class ItemRegistry {

    public void registerItems(){

        int scanRange = BiomeCompass.proxy.getScanRange();

        GameRegistry.registerItem(new ItemBiomeCompass1().setScanRadius(scanRange), new ItemBiomeCompass1().getUnlocalizedName());
        GameRegistry.registerItem(new ItemBiomeCompass2().setScanRadius(scanRange), new ItemBiomeCompass2().getUnlocalizedName());

        /* Registering the amalgam pearls */
        GameRegistry.registerItem(new ItemAmalgamPearlFlora(), new ItemAmalgamPearlFlora().getUnlocalizedName());
        GameRegistry.registerItem(new ItemAmalgamPearlFauna(), new ItemAmalgamPearlFauna().getUnlocalizedName());
        GameRegistry.registerItem(new ItemAmalgamPearlEarthen(), new ItemAmalgamPearlEarthen().getUnlocalizedName());
        GameRegistry.registerItem(new ItemAmalgamPearlPrecious(), new ItemAmalgamPearlPrecious().getUnlocalizedName());
    }

    public void registerRecipes(){

        int scanRange = BiomeCompass.proxy.getScanRange();

        // Register the compasses
        ItemBiomeCompassBase itemBiomeCompass1 = new ItemBiomeCompass1().setScanRadius(scanRange);
        itemBiomeCompass1.registerRecipes();

        ItemBiomeCompassBase itemBiomeCompass2 = new ItemBiomeCompass2().setScanRadius(scanRange);
        itemBiomeCompass2.registerRecipes();


        // Register the pearls
        ItemAmalgamPearlFlora itemAmalgamPearlFlora = new ItemAmalgamPearlFlora();
        itemAmalgamPearlFlora.registerRecipes();

        ItemAmalgamPearlFauna itemAmalgamPearlFauna = new ItemAmalgamPearlFauna();
        itemAmalgamPearlFauna.registerRecipes();

        ItemAmalgamPearlEarthen itemAmalgamPearlEarthen = new ItemAmalgamPearlEarthen();
        itemAmalgamPearlEarthen.registerRecipes();

        ItemAmalgamPearlPrecious itemAmalgamPearlPrecious = new ItemAmalgamPearlPrecious();
        itemAmalgamPearlPrecious.registerRecipes();

    }
}
