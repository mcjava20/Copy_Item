package com.copy_item.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class CopyItemBlockEntity extends TileEntityChest {
    private ItemStack templateItem = ItemStack.EMPTY;

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (!stack.isEmpty()) {
            if (templateItem.isEmpty() || !ItemStack.areItemsEqual(stack, templateItem)) {
                templateItem = stack.copy();
                templateItem.setCount(templateItem.getMaxStackSize());
                for (int i = 0; i < this.getSizeInventory(); i++) {
                    super.setInventorySlotContents(i, templateItem.copy());
                }
            } else {
                super.setInventorySlotContents(index, templateItem.copy());
            }
        } else {
            if (!templateItem.isEmpty()) {
                super.setInventorySlotContents(index, templateItem.copy());
            } else {
                super.setInventorySlotContents(index, stack);
            }
        }
        this.markDirty();
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack result = super.decrStackSize(index, count);
        if (!result.isEmpty() && !templateItem.isEmpty()) {
            if (this.getStackInSlot(index).isEmpty()) {
                super.setInventorySlotContents(index, templateItem.copy());
                this.markDirty();
            }
        }
        return result;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack result = super.removeStackFromSlot(index);
        if (!result.isEmpty() && !templateItem.isEmpty()) {
            super.setInventorySlotContents(index, templateItem.copy());
            this.markDirty();
        }
        return result;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("templateItem")) {
            templateItem = new ItemStack(compound.getCompoundTag("templateItem"));
        } else {
            templateItem = ItemStack.EMPTY;
        }
        if (!templateItem.isEmpty()) {
            for (int i = 0; i < this.getSizeInventory(); i++) {
                super.setInventorySlotContents(i, templateItem.copy());
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = super.writeToNBT(compound);
        if (!templateItem.isEmpty()) {
            compound.setTag("templateItem", templateItem.writeToNBT(new NBTTagCompound()));
        }
        return compound;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString("复制箱子");
    }
}