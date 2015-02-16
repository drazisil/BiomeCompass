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
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ItemAmalgamPearl extends Item {

    public IIcon[] icons = new IIcon[4];

    protected static final Logger logger = LogManager.getLogger("BiomeCompass");

    public ItemAmalgamPearl() {
        super();
        this.setHasSubtypes(true);
        setUnlocalizedName(BiomeCompass.MODID + "_amalgampearl");
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */

    public ItemStack onItemRightClick(ItemStack equippedItemStack, World world, EntityPlayer player) {

        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            return equippedItemStack;
        }

        /*
            Only run on server
             */
        if (player.getCurrentEquippedItem() != null && FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            return equippedItemStack;
        }
        return equippedItemStack;
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }

    public void registerRecipes() {
        ItemStack stackEnderPearl = new ItemStack(Items.ender_pearl);
        ItemStack stackJungleSapling = new ItemStack(Blocks.sapling, 1, 3);
        ItemStack stackSunflower = new ItemStack(Blocks.double_plant);
        ItemStack stackAcaciaSapling = new ItemStack(Blocks.sapling, 1, 4);
        ItemStack stackCactus = new ItemStack(Blocks.cactus);
        ItemStack stackWatermelonBlock = new ItemStack(Blocks.melon_block);
        ItemStack stackDarkOakSapling = new ItemStack(Blocks.sapling, 1, 5);
        ItemStack stackLilyPad = new ItemStack(Blocks.waterlily);
        ItemStack stackSpruceSapling = new ItemStack(Blocks.sapling, 1, 1);

        GameRegistry.addRecipe(new ItemStack(GameRegistry.findItem(BiomeCompass.MODID, getUnlocalizedName()), 1, 0),
                "abc",
                "def",
                "ghi",
                'a', stackJungleSapling,
                'b', stackSunflower,
                'c', stackAcaciaSapling,
                'd', stackCactus,
                'e', stackEnderPearl,
                'f', stackWatermelonBlock,
                'g', stackDarkOakSapling,
                'h', stackLilyPad,
                'i', stackSpruceSapling);
    }

    @Override
    public void registerIcons(IIconRegister reg) {
        for (int i = 0; i < 4; i++) {
            this.icons[i] = reg.registerIcon(BiomeCompass.MODID + ":amalgampearl_" + i);
        }

    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     *
     * @param item Item
     * @param tab CreativeTab
     * @param list List
     */
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 4; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    /**
     * Gets an icon index based on an item's damage value
     *
     * @param meta ingt
     */
    @Override
    public IIcon getIconFromDamage(int meta) {
        if (meta > 5)
            meta = 0;

        return this.icons[meta];
    }

    /**
     * Sets the unlocalized name of this item to the string passed as the parameter, prefixed by "item."
     *
     * @param stack ItemStack
     */
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        switch (stack.getItemDamage()) {
            case 0:
                return this.getUnlocalizedName() + "_flora";
            case 1:
                return this.getUnlocalizedName() + "_fauna";
            case 2:
                return this.getUnlocalizedName() + "_earthen";
            case 3:
                return this.getUnlocalizedName() + "_precious";
            default:
                return this.getUnlocalizedName();
        }

    }
}


