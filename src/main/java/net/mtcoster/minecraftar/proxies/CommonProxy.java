package net.mtcoster.minecraftar.proxies;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.mtcoster.minecraftar.MinecraftAR;
import net.mtcoster.minecraftar.items.ItemGoggles;

/**
 * Created by MTCoster and MJHutchinson. Created August 2015
 */

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e){
        MinecraftAR.itemGoggles = new ItemGoggles();
        GameRegistry.registerItem(MinecraftAR.itemGoggles, "goggles");
    }

    public void init(FMLInitializationEvent e){}

    public void postInit(FMLPostInitializationEvent e){}

}
