package net.mtcoster.minecraftar.proxies;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.mtcoster.minecraftar.ModInfo;
import net.mtcoster.minecraftar.items.ModItems;

/**
 * Created by MTCoster and MJHutchinson. Created August 2015
 */

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e){
        ModItems.createItems();
    }

    public void init(FMLInitializationEvent e){}

    public void postInit(FMLPostInitializationEvent e){}

}
