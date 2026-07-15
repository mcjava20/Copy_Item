package com.copy_item.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CopyItemCreativeTab extends CreativeTabs {
    public CopyItemCreativeTab() {
        super(CopyItem.MODID);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(CopyItem.COPYITEM_BLOCK);
    }
}