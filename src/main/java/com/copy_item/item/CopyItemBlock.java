package com.copy_item.item;

import net.minecraft.block.BlockChest;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CopyItemBlock extends BlockChest {
    public CopyItemBlock() {
        super(Type.BASIC);
        this.setUnlocalizedName("copyitem");
        this.setRegistryName(new ResourceLocation(CopyItem.MODID, "copyitem"));
        this.setCreativeTab(new CopyItemCreativeTab());
        this.setHardness(2.5F);
        this.setResistance(12.5F);
        this.setSoundType(SoundType.WOOD);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new CopyItemBlockEntity();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
}