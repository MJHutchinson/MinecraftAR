package net.mtcoster.minecraftar.client.render.components;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Item3D extends RenderableComponent {
    private ItemBox3D box;

    public Item3D(float x, float y) {
        this(x, y, true);
    }

    public Item3D(float x, float y, boolean hasBox) {
        super(x, y);
        box = hasBox ? new ItemBox3D(x, y) : null;
    }

    public Item3D(float x, float y, float height) {
        this(x, y, height, true);
    }

    public Item3D(float x, float y, float height, boolean hasBox) {
        super(x, y, height);
        box = hasBox ? new ItemBox3D(x, y) : null;
    }

    @Override
    public void render() {
        if (box != null) box.render();

        // Scale: 0.08f


    }
}
