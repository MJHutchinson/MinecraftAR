package net.mtcoster.minecraftar.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.mtcoster.minecraftar.ModInfo;

/**
 * Created by Michael Hutchinson on 23/08/2015 at 23:10.
 */
public class ARGogglesItem extends Item {

    public ARGogglesItem(){
        super();

        this.setUnlocalizedName(ModInfo.ModItemUnlocalizedNames.ARGOGGLES_ITEM_NAME);
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

}
