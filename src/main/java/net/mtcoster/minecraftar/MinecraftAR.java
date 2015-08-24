package net.mtcoster.minecraftar;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.mtcoster.minecraftar.proxies.CommonProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by MTCoster and MJHutchinson. Created August 2015
 */

@Mod(
    modid = "MinecraftAR",
    version = "0.1.0",
    name = "MinecraftAR",
    dependencies = "required-after:Forge@[11.14.1,);"
)

public class MinecraftAR {
    public static final Logger log = LogManager.getLogger("MinecraftAR");

    public static Item itemGoggles;

    @SidedProxy(
            clientSide = "net.mtcoster.minecraftar.proxies.ClientProxy",
            serverSide = "net.mtcoster.minecraftar.proxies.CommonProxy"
    )
    public static CommonProxy proxy;

    @Instance("MinecraftAR")
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
