package net.mtcoster.minecraftar.client.render;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

public class RenderEventHandler {
    private static final RenderEventHandler instance = new RenderEventHandler();

    public static RenderEventHandler getInstance() {
        return instance;
    }

    public void register() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void worldDidLastRender(RenderWorldLastEvent e) {
        OverlayRenderHelper renderer = new OverlayRenderHelper(e);
        renderer.testQuad(9.0f, 55.0f, 9.0f, false);
        ArrayList<Class<? extends Block>> renderedOn = new ArrayList<>();
        renderedOn.add(BlockFurnace.class);
        renderer.staticOverlays(renderedOn);
    }
}
