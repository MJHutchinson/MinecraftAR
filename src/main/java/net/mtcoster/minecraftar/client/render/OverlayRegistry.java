package net.mtcoster.minecraftar.client.render;

import net.minecraft.block.Block;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.mtcoster.minecraftar.client.render.type.Overlay;

import java.util.HashMap;

@SideOnly(Side.CLIENT)
public class OverlayRegistry {
    private static HashMap<Class<? extends Block>, Overlay> registry = new HashMap<>();

    public static void registerOverlayForBlock(Class<? extends Block> block, Overlay overlay) {
        if (!registry.containsKey(block)) {
            registry.put(block, overlay);
        }
    }

    public static Overlay getOverlayForBlock(Class<? extends Block> block) {
        return registry.get(block);
    }

    public static boolean containsBlock(Class<? extends Block> block) {
        return getOverlayForBlock(block) != null;
    }
}
