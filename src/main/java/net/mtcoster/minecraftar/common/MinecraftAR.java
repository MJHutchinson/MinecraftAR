package net.mtcoster.minecraftar.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

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
    public static final Minecraft mc = Minecraft.getMinecraft();
    public static final Logger log = LogManager.getLogger("MinecraftAR");

    public static Item itemGoggles;

    @SidedProxy(
            clientSide = "net.mtcoster.minecraftar.client.ClientProxy",
            serverSide = "net.mtcoster.minecraftar.common.CommonProxy"
    )
    public static CommonProxy proxy;

    @Instance("MinecraftAR")
    public static MinecraftAR instance;

    public MinecraftAR(){}

    @EventHandler
    public void preInit(FMLPreInitializationEvent e){
        proxy.setupItems();
    }

    @EventHandler
    public void init(FMLInitializationEvent e){
        MinecraftForge.EVENT_BUS.register(this);
        proxy.setupRenderers();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e){
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void worldDidLastRender(RenderWorldLastEvent e) {
        Entity rve = mc.getRenderViewEntity();

        double px = rve.prevPosX - 0.5 + (rve.posX - rve.prevPosX) * e.partialTicks;
        double py = rve.prevPosY + 0.5 + (rve.posY - rve.prevPosY) * e.partialTicks;
        double pz = rve.prevPosZ - 0.5 + (rve.posZ - rve.prevPosZ) * e.partialTicks;

        float mx = 9.0f;
        float my = 55.0f;
        float mz = 9.0f;

        Tessellator t = Tessellator.getInstance();
        WorldRenderer renderer = t.getWorldRenderer();

        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);

        renderer.startDrawing(GL11.GL_QUADS);

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
        renderer.setBrightness(15 << 20 | 15 << 4); // 15,728,880
        renderer.setColorRGBA(0, 127, 255, 191);

        GL11.glTranslated(mx - px, 0.00f - py, mz - pz);

        double a = Math.atan((px - mx) / (pz - mz)) / Math.PI * 180 + 45;
        if (pz - mz < 0) a += 180;
        if (a > 360) a -= 360;
        GL11.glRotated(a, 0.0f, 1.0f, 0.0f);

        renderer.addVertex( 0.4f, my + 0.4f,  0.4f);
        renderer.addVertex(-0.4f, my + 0.4f, -0.4f);
        renderer.addVertex(-0.4f, my - 0.4f, -0.4f);
        renderer.addVertex( 0.4f, my - 0.4f,  0.4f);

//        renderer.addVertex(mx - 0.4f, my + 0.4f, mz - 0.01f);
//        renderer.addVertex(mx + 0.4f, my + 0.4f, mz - 0.01f);
//        renderer.addVertex(mx + 0.4f, my - 0.4f, mz - 0.01f);
//        renderer.addVertex(mx - 0.4f, my - 0.4f, mz - 0.01f);
//        renderer.addVertex(mx + 0.4f, my + 0.4f, mz + 0.01f);
//        renderer.addVertex(mx - 0.4f, my + 0.4f, mz + 0.01f);
//        renderer.addVertex(mx - 0.4f, my - 0.4f, mz + 0.01f);
//        renderer.addVertex(mx + 0.4f, my - 0.4f, mz + 0.01f);

        t.draw();

//        GL11.glDisable(GL11.GL_EMISSION);
//        GL11.glEnable(GL11.GL_TEXTURE_2D);
//        GL11.glEnable(GL11.GL_LIGHTING);
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }

}
