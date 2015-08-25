package net.mtcoster.minecraftar.common;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.mtcoster.minecraftar.common.items.ItemGoggles;

/**
 * Created by MTCoster and MJHutchinson. Created August 2015
 */

public class CommonProxy {

    public void setupItems(){
        MinecraftAR.itemGoggles = new ItemGoggles();
        GameRegistry.registerItem(MinecraftAR.itemGoggles, ModInfo.ItemUnlocalizedNames.GOGGLE_ITEM_NAME);
    }

    public void setupRenderers(){}

    public void registerHandlers() {

    }
}
