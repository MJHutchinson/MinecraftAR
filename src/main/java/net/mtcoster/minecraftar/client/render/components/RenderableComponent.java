package net.mtcoster.minecraftar.client.render.components;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class RenderableComponent {
    protected float x;
    protected float y;
    protected float height;

    public RenderableComponent(float x, float y) {
        this(x, y, 0.0f);
    }

    public RenderableComponent(float x, float y, float height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getHeight() {
        return height;
    }

    abstract public void render();
}
