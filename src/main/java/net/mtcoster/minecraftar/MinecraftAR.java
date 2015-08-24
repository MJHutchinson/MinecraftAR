package net.mtcoster.minecraftar;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.mtcoster.minecraftar.proxies.CommonProxy;

/**
 * Created by MTCoster and MJHutchinson. Created August 2015
 */

@Mod(modid = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION, name = ModInfo.MOD_ID, clientSideOnly = true)
public class MinecraftAR {

    @Mod.Instance
    public static MinecraftAR instance = new MinecraftAR();

    @SidedProxy(clientSide = "net.mtcoster.minecraftar.proxies.ClientProxy", serverSide = "net.mtcoster.minecraftar.proxies.ServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){
        proxy.postInit(e);
    }

}
