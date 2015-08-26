package net.mtcoster.minecraftar.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.mtcoster.minecraftar.common.MinecraftAR;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class OverlayRenderHelper {
    private Tessellator t;
    private WorldRenderer r;
    private Entity rve;
    private RenderWorldLastEvent e;

    private double[] pos;

    public static final int X = 0;
    public static final int Y = 1;
    public static final int Z = 2;

    public OverlayRenderHelper(RenderWorldLastEvent e) {
        t = Tessellator.getInstance();
        r = t.getWorldRenderer();
        rve = MinecraftAR.mc.getRenderViewEntity();
        this.e = e;

        pos = new double[]{
            rve.prevPosX - 0.5 + (rve.posX - rve.prevPosX) * e.partialTicks,
            rve.prevPosY + 0.5 + (rve.posY - rve.prevPosY) * e.partialTicks,
            rve.prevPosZ - 0.5 + (rve.posZ - rve.prevPosZ) * e.partialTicks
        };

    }

    public void staticOverlays(ArrayList<Class<? extends Block>> renderedOn) {
        Chunk c = MinecraftAR.mc.theWorld.getChunkFromChunkCoords(rve.chunkCoordX, rve.chunkCoordZ);
        ExtendedBlockStorage[] bsa = c.getBlockStorageArray();

        for (ExtendedBlockStorage ebs : bsa) {
            if (ebs != null) {
                for (int z = 0; z < 15; z++) {
                    for (int y = 0; y < 15; y++) {
                        for (int x = 0; x < 15; x++) {
                            if (OverlayRegistry.containsBlock(ebs.getBlockByExtId(x, y, z).getClass())) {
                                BlockPos pos = new BlockPos(x + (c.xPosition << 4), y + ebs.getYLocation(), z + (c.zPosition << 4));
                                OverlayRegistry.getOverlayForBlock(getBlockAt(pos).getClass()).renderOverlayOnBlock(pos, e.partialTicks);
//                                    blockFaceOverlay(xpos, ypos, zpos, EnumFacing.NORTH);
                            }
                        }
                    }
                }
            }
        }
    }

    public void testQuad(double x, double y, double z, boolean transparent) {
        initGL(GL11.GL_QUADS);
        ignoreLighting();

        if (transparent) GL11.glEnable(GL11.GL_BLEND);

        GL11.glDisable(GL11.GL_TEXTURE_2D);

        r.setColorRGBA(0, 127, 255, 191);

        GL11.glTranslated(x - pos[X], 0.00f - pos[Y], z - pos[Z]);

        double a = Math.atan((pos[X] - x) / (pos[Z] - z)) / Math.PI * 180 + 45;
        if (pos[Z] - z < 0) a += 180;
        if (a > 360) a -= 360;
        GL11.glRotated(a, 0.0f, 1.0f, 0.0f);

        r.addVertex(0.4f, y + 0.4f, 0.4f);
        r.addVertex(-0.4f, y + 0.4f, -0.4f);
        r.addVertex(-0.4f, y - 0.4f, -0.4f);
        r.addVertex(0.4f, y - 0.4f, 0.4f);

        resetGL();
    }

    private void blockFaceOverlay(float x, float y, float z, EnumFacing side) {
        Block block = getBlockAt(x, y, z);

        initGL(GL11.GL_QUADS);
        ignoreLighting();

        GL11.glDisable(GL11.GL_TEXTURE_2D);

        r.setColorOpaque(0, 255, 0);

        GL11.glTranslated(x - 0.5f - pos[X], y + 0.5f - pos[Y], z - 1.5f - pos[Z]);

        switch (side) {
            case DOWN:
                r.addVertex(0, 0, 1);
                r.addVertex(1, 0, 1);
                r.addVertex(1, 0, 0);
                r.addVertex(0, 0, 0);
                break;
            case UP:
                r.addVertex(0, 1, 0);
                r.addVertex(1, 1, 0);
                r.addVertex(1, 1, 1);
                r.addVertex(0, 1, 1);
                break;
            case NORTH:
                r.addVertex(0, 1, 1);
                r.addVertex(1, 1, 1);
                r.addVertex(1, 0, 1);
                r.addVertex(0, 0, 1);
                break;
            case SOUTH:
                r.addVertex(1, 1, 0);
                r.addVertex(0, 1, 0);
                r.addVertex(0, 0, 0);
                r.addVertex(1, 0, 0);
                break;
            case WEST:
                r.addVertex(0, 1, 0);
                r.addVertex(0, 1, 1);
                r.addVertex(0, 0, 1);
                r.addVertex(0, 0, 0);
                break;
            case EAST:
                r.addVertex(1, 1, 1);
                r.addVertex(1, 1, 0);
                r.addVertex(1, 0, 0);
                r.addVertex(1, 0, 1);
                break;
            default:
                // Do nothing
        }

        resetGL();
    }

    public void initGL(int type) {
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
        r.startDrawing(type);
    }

    public void resetGL() {
        t.draw();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }

    public void ignoreLighting() {
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
        r.setBrightness(15 << 20 | 15 << 4); // 15,728,880
    }

    private Block getBlockAt(BlockPos pos) {
        return MinecraftAR.mc.theWorld.getBlockState(pos).getBlock();
    }

    private Block getBlockAt(float x, float y, float z) {
        return getBlockAt(new BlockPos(x, y, z));
    }

    public static void flushRenderQueue(int newRenderType) {
        try {
            Tessellator.getInstance().getWorldRenderer().startDrawing(newRenderType);
        } catch (IllegalStateException e) {
            Tessellator.getInstance().draw();
            Tessellator.getInstance().getWorldRenderer().startDrawing(newRenderType);
        }
    }

    public static void zeroToWorld(float partialTicks) {
        Entity rve = MinecraftAR.mc.getRenderViewEntity();
        double px = rve.prevPosX - 0.5 + (rve.posX - rve.prevPosX) * partialTicks;
        double py = rve.prevPosY - 0.5 + (rve.posY - rve.prevPosY) * partialTicks;
        double pz = rve.prevPosZ - 0.5 + (rve.posZ - rve.prevPosZ) * partialTicks;
        GL11.glTranslated(-px, -py, -pz);
    }
}
