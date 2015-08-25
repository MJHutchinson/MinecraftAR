package net.mtcoster.minecraftar.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.WeightedRandom;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.mtcoster.minecraftar.common.MinecraftAR;
import net.mtcoster.minecraftar.common.CommonProxy;

/**
 * Created by MTCoster and MJHutchinson. Created August 2015
 */

public class ClientProxy extends CommonProxy {

    @Override
    public void setupRenderers(){
        registerRenderer(MinecraftAR.itemGoggles);
    }

    public void registerRenderer(Item item){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation("MinecraftAR:goggles", "inventory"));
    }


}
