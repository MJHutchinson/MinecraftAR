package net.mtcoster.minecraftar.common;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    public static final Minecraft mc = Minecraft.getMinecraft();
    public static final Logger log = LogManager.getLogger(ModInfo.MOD_NAME);

    public static Item itemGoggles;

    @SidedProxy(
            clientSide = "net.mtcoster.minecraftar.client.ClientProxy",
            serverSide = "net.mtcoster.minecraftar.common.CommonProxy"
    )
    public static CommonProxy proxy;

    @Instance(ModInfo.MOD_NAME)
    public static MinecraftAR instance;

    public MinecraftAR(){}

    @EventHandler
    public void preInit(FMLPreInitializationEvent e){
        proxy.setupItems();
    }

    @EventHandler
    public void init(FMLInitializationEvent e){
        proxy.setupRenderers();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e){
    }

}
