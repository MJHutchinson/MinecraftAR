package net.mtcoster.minecraftar.client.render.type;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.mtcoster.minecraftar.client.render.OverlayRenderHelper;
import net.mtcoster.minecraftar.client.render.components.RenderableComponent;
import net.mtcoster.minecraftar.common.MinecraftAR;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public abstract class Overlay {
    private ArrayList<RenderableComponent> components = new ArrayList<>();

    public Vec3i overlayBaseColor() {
        return new Vec3i(0, 255, 0);
    }

    public void addComponent(RenderableComponent component) {
        components.add(component);
    }

    public void renderOverlayOnBlock(BlockPos pos, float partialTicks) {
        WorldRenderer r = Tessellator.getInstance().getWorldRenderer();

        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();

        OverlayRenderHelper.zeroToWorld(partialTicks);
        GL11.glTranslated(pos.getX(), pos.getY(), pos.getZ());
        rotateToOverlayFace(getFaceToOverlayOn(MinecraftAR.mc.theWorld.getBlockState(pos)));
        GL11.glTranslated(0.0f, 0.0f, 0.55f);

        if (shouldRenderOverlayCorners()) {
            for (int i = 0; i < 4; i++) {
                OverlayRenderHelper.flushRenderQueue(GL11.GL_QUADS);
                r.setColorOpaque(overlayBaseColor().getX(), overlayBaseColor().getY(), overlayBaseColor().getZ());
                GL11.glDisable(GL11.GL_TEXTURE_2D);

                r.addVertex(0.30f, 0.50f, 0.00f);
                r.addVertex(0.30f, 0.45f, 0.00f);
                r.addVertex(0.45f, 0.45f, 0.00f);
                r.addVertex(0.45f, 0.50f, 0.00f);

                r.addVertex(0.45f, 0.50f, 0.00f);
                r.addVertex(0.45f, 0.30f, 0.00f);
                r.addVertex(0.50f, 0.30f, 0.00f);
                r.addVertex(0.50f, 0.50f, 0.00f);

                GL11.glRotated(90, 0.0f, 0.0f, 1.0f);
            }

            OverlayRenderHelper.flushRenderQueue(GL11.GL_QUADS);
        }

        GL11.glTranslated(-0.5f, -0.5f, 0.0f);

        for (RenderableComponent comp : components) {
            r.setColorOpaque(overlayBaseColor().getX(), overlayBaseColor().getY(), overlayBaseColor().getZ());
            GL11.glTranslated(comp.getX(), comp.getY(), 0.0f);
            comp.render();
            GL11.glTranslated(-comp.getX(), -comp.getY(), 0.0f);
        }

        Tessellator.getInstance().draw();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }

    protected boolean shouldRenderOverlayCorners() {
        return true;
    }

    abstract protected EnumFacing getFaceToOverlayOn(IBlockState block);

    protected void rotateToOverlayFace(EnumFacing face) {
        switch (face) {
            case DOWN:
                GL11.glRotated(270, 1.0f, 0.0f, 0.0f);
                break;
            case UP:
                GL11.glRotated(90, 1.0f, 0.0f, 0.0f);
                break;
            case NORTH:
                GL11.glRotated(180, 0.0f, 1.0f, 0.0f);
                break;
            case SOUTH:
                // No rotation necessary
                break;
            case WEST:
                GL11.glRotated(270, 0.0f, 1.0f, 0.0f);
                break;
            case EAST:
                GL11.glRotated(90, 0.0f, 1.0f, 0.0f);
                break;
            default:
                // Whelp!
        }
    }
}
