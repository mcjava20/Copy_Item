package com.copy_item.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

public class CopyItemBlockEntity extends BlockEntity {

    private static final List<CopyItemBlockEntity> INSTANCES = new CopyOnWriteArrayList<>();
    private final ItemStackHandler itemHandler = new ItemStackHandler(1);

    public CopyItemBlockEntity(BlockPos pos, BlockState state) {
        super(CopyItem.BLOCK_ENTITY.get(), pos, state);
        INSTANCES.add(this);
    }

    public static List<CopyItemBlockEntity> getInstances() {
        return List.copyOf(INSTANCES);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        INSTANCES.remove(this);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        tag.put("Inventory", itemHandler.serializeNBT(provider));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        itemHandler.deserializeNBT(provider, tag.getCompound("Inventory"));
    }

    public IItemHandler getItemHandler() {
        return itemHandler;
    }

    public void tick() {
        if (level == null || level.isClientSide) {
            return;
        }

        boolean hasRedstoneSignal = level.hasNeighborSignal(worldPosition);
        
        if (!hasRedstoneSignal) {
            return;
        }

        ItemStack inputStack = itemHandler.getStackInSlot(0);
        if (inputStack.isEmpty()) {
            return;
        }

        BlockPos abovePos = worldPosition.relative(Direction.UP);
        BlockState aboveBlock = level.getBlockState(abovePos);
        boolean isIronBlockAbove = aboveBlock.is(Blocks.IRON_BLOCK);
        
        int copyAmount = Config.COPY_AMOUNT.get();
        int outputCount = isIronBlockAbove ? Math.max(1, copyAmount - 1) : copyAmount;
        ItemStack outputStack = inputStack.copyWithCount(outputCount);
        
        BlockState state = getBlockState();
        Direction facing = state.getValue(CopyItemBlock.FACING);
        
        BlockPos outputPos = worldPosition.relative(facing);
        IItemHandler outputHandler = level.getCapability(Capabilities.ItemHandler.BLOCK, outputPos, facing.getOpposite());

        if (outputHandler == null) {
            return;
        }

        for (int slot = 0; slot < outputHandler.getSlots(); slot++) {
            ItemStack remaining = outputHandler.insertItem(slot, outputStack, false);
            
            if (remaining.isEmpty()) {
                if (!isIronBlockAbove) {
                    itemHandler.extractItem(0, 1, false);
                }
                setChanged();
                return;
            }

            if (remaining.getCount() < outputStack.getCount()) {
                if (!isIronBlockAbove) {
                    itemHandler.extractItem(0, 1, false);
                }
                setChanged();
                return;
            }
        }
    }
}
