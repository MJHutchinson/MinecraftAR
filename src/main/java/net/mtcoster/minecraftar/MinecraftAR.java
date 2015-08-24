package net.mtcoster.minecraftar;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.mtcoster.minecraftar.proxies.CommonProxy;

/**
 * Created by MTCoster and MJHutchinson. Created August 2015
 */

@Mod(
    modid = ModInfo.MOD_ID,
    version = ModInfo.MOD_VERSION,
    name = ModInfo.MOD_NAME,
    dependencies = "required-after:Forge@[11.14.1,);"
)

public class MinecraftAR {

    @SidedProxy(
            clientSide = "net.mtcoster.minecraftar.proxies.ClientProxy",
            serverSide = "net.mtcoster.minecraftar.proxies.CommonProxy"
    )
    public static CommonProxy proxy;

    @Instance(ModInfo.MOD_ID)
    public static MinecraftAR instance;

    public MinecraftAR(){}

    @EventHandler
    public void preInit(FMLPreInitializationEvent e){
        proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e){
        proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e){
        proxy.postInit(e);
    }

}
