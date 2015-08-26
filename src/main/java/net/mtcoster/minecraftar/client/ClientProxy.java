package net.mtcoster.minecraftar.client;

import net.minecraft.block.BlockFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.mtcoster.minecraftar.client.render.OverlayRegistry;
import net.mtcoster.minecraftar.client.render.RenderEventHandler;
import net.mtcoster.minecraftar.client.render.type.vanilla.OverlayFurnace;
import net.mtcoster.minecraftar.common.ModInfo;
import net.mtcoster.minecraftar.common.CommonProxy;
import net.mtcoster.minecraftar.common.MinecraftAR;

/**
 * Created by MTCoster and MJHutchinson. Created August 2015
 */

public class ClientProxy extends CommonProxy {

    @Override
    public void setupRenderers(){
        registerRenderer(MinecraftAR.itemGoggles);
    }

    @Override
    public void registerHandlers() {
        RenderEventHandler.getInstance().register();
    }

    private void registerRenderer(Item item){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(ModInfo.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

    @Override
    public void registerOverlays() {
        OverlayRegistry.registerOverlayForBlock(BlockFurnace.class, new OverlayFurnace());
    }
}
