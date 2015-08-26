package net.mtcoster.minecraftar.client.render.type.vanilla;

import net.minecraft.block.BlockFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.mtcoster.minecraftar.client.render.components.Item3D;
import net.mtcoster.minecraftar.client.render.type.Overlay;

@SideOnly(Side.CLIENT)
public class OverlayFurnace extends Overlay {
    public OverlayFurnace() {
        addComponent(new Item3D(0.0f, 0.0f));
    }

    @Override
    protected EnumFacing getFaceToOverlayOn(IBlockState block) {
        return (EnumFacing) block.getValue(BlockFurnace.FACING);
    }
}
