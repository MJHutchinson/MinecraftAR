package net.mtcoster.minecraftar.proxies;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.mtcoster.minecraftar.MinecraftAR;

/**
 * Created by MTCoster and MJHutchinson. Created August 2015
 */

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(MinecraftAR.itemGoggles, 0, new ModelResourceLocation("MinecraftAR:goggles", "inventory"));
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }

}
