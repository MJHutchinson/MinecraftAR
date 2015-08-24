package net.mtcoster.minecraftar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
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
import net.mtcoster.minecraftar.proxies.CommonProxy;
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
        MinecraftForge.EVENT_BUS.register(this);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e){
        proxy.postInit(e);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void worldDidLastRender(RenderWorldLastEvent e) {
        double dx = mc.thePlayer.posX - 0.5;
        double dy = mc.thePlayer.posY + 0.5;
        double dz = mc.thePlayer.posZ - 0.5;

        float mx = 9.0f;
        float my = 55.0f;
        float mz = 9.0f;

        GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
            GL11.glTranslated(-dx, -dy, -dz);
            GL11.glColor3b((byte) 255, (byte) 127, (byte) 0);
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex3f(mx + 0.4f, my + 0.4f, mz);
            GL11.glVertex3f(mx + 0.4f, my - 0.4f, mz);
            GL11.glVertex3f(mx - 0.4f, my - 0.4f, mz);
            GL11.glVertex3f(mx - 0.4f, my + 0.4f, mz);
            /*
            GL11.glBegin(GL11.GL_LINES);
            GL11.glVertex3f(mx + 0.4f, my, mz + 0.4f);
            GL11.glVertex3f(mx - 0.4f, my, mz - 0.4f);
            GL11.glVertex3f(mx + 0.4f, my, mz - 0.4f);
            GL11.glVertex3f(mx - 0.4f, my, mz + 0.4f);
            */
            GL11.glEnd();
            GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

}
