package net.mtcoster.minecraftar.client.render;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        RenderHelper renderer = new RenderHelper(e);
        renderer.renderTestQuad(9.0f, 55.0f, 9.0f);
    }
}
