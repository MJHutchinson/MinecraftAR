package net.mtcoster.minecraftar.client.render.components;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.mtcoster.minecraftar.client.render.OverlayRenderHelper;
import org.lwjgl.opengl.GL11;

public class ItemBox2D extends RenderableComponent {
    public ItemBox2D(float x, float y) {
        super(x, y);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getHeight() {
        return 0.0f;
    }

    @Override
    public void render() {
        WorldRenderer r = Tessellator.getInstance().getWorldRenderer();

        OverlayRenderHelper.flushRenderQueue(GL11.GL_LINES);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        r.setColorOpaque(0, 255, 0);

        r.addVertex(0.00f, 0.00f, 0.00f);
        r.addVertex(0.03f, 0.00f, 0.00f);
        r.addVertex(0.12f, 0.00f, 0.00f);
        r.addVertex(0.15f, 0.00f, 0.00f);

        r.addVertex(0.15f, 0.00f, 0.00f);
        r.addVertex(0.15f, 0.03f, 0.00f);
        r.addVertex(0.15f, 0.12f, 0.00f);
        r.addVertex(0.15f, 0.15f, 0.00f);

        r.addVertex(0.15f, 0.15f, 0.00f);
        r.addVertex(0.12f, 0.15f, 0.00f);
        r.addVertex(0.03f, 0.15f, 0.00f);
        r.addVertex(0.00f, 0.15f, 0.00f);

        r.addVertex(0.00f, 0.15f, 0.00f);
        r.addVertex(0.00f, 0.12f, 0.00f);
        r.addVertex(0.00f, 0.03f, 0.00f);
        r.addVertex(0.00f, 0.00f, 0.00f);
    }
}
