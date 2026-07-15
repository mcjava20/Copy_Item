package com.copy_item.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = CopyItem.MODID, name = "Copy Chest", version = "1.0")
public class CopyItem {
    public static final String MODID = "copyitem";

    public static Item COPYITEM_BLOCK_ITEM;
    public static net.minecraft.block.Block COPYITEM_BLOCK;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        COPYITEM_BLOCK = new CopyItemBlock();
        ForgeRegistries.BLOCKS.register(COPYITEM_BLOCK);

        COPYITEM_BLOCK_ITEM = new ItemBlock(COPYITEM_BLOCK);
        COPYITEM_BLOCK_ITEM.setRegistryName(COPYITEM_BLOCK.getRegistryName());
        ForgeRegistries.ITEMS.register(COPYITEM_BLOCK_ITEM);

        GameRegistry.registerTileEntity(CopyItemBlockEntity.class, MODID + "_copyitem_tileentity");

        if (event.getSide() == Side.CLIENT) {
            net.minecraftforge.client.model.ModelLoader.setCustomModelResourceLocation(COPYITEM_BLOCK_ITEM, 0,
                    new net.minecraft.client.renderer.block.model.ModelResourceLocation(COPYITEM_BLOCK_ITEM.getRegistryName(), "inventory"));

            net.minecraft.client.renderer.tileentity.TileEntityChestRenderer renderer = new net.minecraft.client.renderer.tileentity.TileEntityChestRenderer();
            net.minecraftforge.fml.client.registry.ClientRegistry.bindTileEntitySpecialRenderer(CopyItemBlockEntity.class, renderer);
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }
}