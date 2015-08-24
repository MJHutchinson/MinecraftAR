package net.mtcoster.minecraftar.render.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.mtcoster.minecraftar.ModInfo;
import net.mtcoster.minecraftar.items.ModItems;

/**
 * Created by Michael Hutchinson on 23/08/2015 at 23:36.
 */
public class ItemRenderRegister {

    public static void registerItemRenderer(){

//    reg(ModItems.arGogglesItem);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ModItems.arGogglesItem, 0, new ModelResourceLocation("minecraftar:argoggles_item", "inventory"));

    }


    public static void reg(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(ModInfo.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

}
