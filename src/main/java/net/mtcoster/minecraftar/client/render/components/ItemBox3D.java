package net.mtcoster.minecraftar.client.render.components;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import org.lwjgl.opengl.GL11;

public class ItemBox3D extends ItemBox2D {
    public ItemBox3D(float x, float y) {
        super(x, y);
    }

    @Override
    public void render() {
        super.render();
        GL11.glTranslated(0, 0, 0.15f);
        super.render();
        GL11.glTranslated(0, 0, -0.15f);

        WorldRenderer r = Tessellator.getInstance().getWorldRenderer();

        r.addVertex(0.00f, 0.00f, 0.00f);
        r.addVertex(0.00f, 0.00f, 0.03f);
        r.addVertex(0.00f, 0.00f, 0.12f);
        r.addVertex(0.00f, 0.00f, 0.15f);

        r.addVertex(0.15f, 0.00f, 0.00f);
        r.addVertex(0.15f, 0.00f, 0.03f);
        r.addVertex(0.15f, 0.00f, 0.12f);
        r.addVertex(0.15f, 0.00f, 0.15f);

        r.addVertex(0.15f, 0.15f, 0.00f);
        r.addVertex(0.15f, 0.15f, 0.03f);
        r.addVertex(0.15f, 0.15f, 0.12f);
        r.addVertex(0.15f, 0.15f, 0.15f);

        r.addVertex(0.00f, 0.15f, 0.00f);
        r.addVertex(0.00f, 0.15f, 0.03f);
        r.addVertex(0.00f, 0.15f, 0.12f);
        r.addVertex(0.00f, 0.15f, 0.15f);
    }
}
