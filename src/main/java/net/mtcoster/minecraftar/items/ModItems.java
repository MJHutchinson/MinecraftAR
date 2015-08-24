package net.mtcoster.minecraftar.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.mtcoster.minecraftar.ModInfo;

/**
 * Created by Michael Hutchinson on 23/08/2015 at 23:09.
 */
public class ModItems {

    public static Item arGogglesItem;

    public static void createItems(){

        GameRegistry.registerItem(arGogglesItem = new ARGogglesItem(), ModInfo.ModItemUnlocalizedNames.ARGOGGLES_ITEM_NAME);

    }

}
