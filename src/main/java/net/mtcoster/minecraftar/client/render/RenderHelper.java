package net.mtcoster.minecraftar.client.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.mtcoster.minecraftar.common.MinecraftAR;
import org.lwjgl.opengl.GL11;

public class RenderHelper {
    private Tessellator t;
    private WorldRenderer renderer;
    private Entity rve;
    private RenderWorldLastEvent e;

    public RenderHelper(RenderWorldLastEvent e) {
        t = Tessellator.getInstance();
        renderer = t.getWorldRenderer();
        rve = MinecraftAR.mc.getRenderViewEntity();
        this.e = e;
    }

    public void renderTestQuad(double mx, double my, double mz) {
        double px = rve.prevPosX - 0.5 + (rve.posX - rve.prevPosX) * e.partialTicks;
        double py = rve.prevPosY + 0.5 + (rve.posY - rve.prevPosY) * e.partialTicks;
        double pz = rve.prevPosZ - 0.5 + (rve.posZ - rve.prevPosZ) * e.partialTicks;

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

        t.draw();

        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
}
