package com.copy_item.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

public class CopyItemBlockEventHandler {

    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        if (event.getPosition().isEmpty()) return;
        
        BlockPos pos = event.getPosition().get();
        BlockState state = player.level().getBlockState(pos);
        
        if (state.getBlock() == CopyItem.COPYITEM_BLOCK.get()) {
            if (player.getAbilities().instabuild) {
                return;
            }
            
            ItemStack tool = player.getMainHandItem();
            
            if (tool.isEmpty()) {
                event.setNewSpeed(1.0f / 15.0f);
                return;
            }
            
            if (!(tool.getItem() instanceof PickaxeItem)) {
                event.setNewSpeed(0.001f);
                return;
            }
            
            if (tool.getItem() instanceof TieredItem tieredItem) {
                net.minecraft.world.item.Tier tier = tieredItem.getTier();
                
                if (tier == Tiers.WOOD) {
                    event.setNewSpeed(1.0f / 7.5f);
                } else if (tier == Tiers.STONE) {
                    event.setNewSpeed(1.0f / 1.15f);
                } else if (tier == Tiers.IRON) {
                    event.setNewSpeed(1.0f / 0.75f);
                } else if (tier == Tiers.DIAMOND) {
                    event.setNewSpeed(1.0f / 0.6f);
                } else if (tier == Tiers.NETHERITE) {
                    event.setNewSpeed(1.0f / 0.6f);
                } else {
                    event.setNewSpeed(0.001f);
                }
            } else {
                event.setNewSpeed(0.001f);
            }
        }
    }
    
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
    }
    
    private boolean isIronPickaxeOrBetter(ItemStack tool) {
        if (tool.isEmpty()) {
            return false;
        }
        
        if (!(tool.getItem() instanceof PickaxeItem)) {
            return false;
        }
        
        if (tool.getItem() instanceof TieredItem tieredItem) {
            net.minecraft.world.item.Tier tier = tieredItem.getTier();
            
            return tier == Tiers.IRON || 
                   tier == Tiers.DIAMOND || 
                   tier == Tiers.NETHERITE;
        }
        
        return false;
    }
}
