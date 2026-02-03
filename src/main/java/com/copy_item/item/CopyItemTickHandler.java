package com.copy_item.item;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

public class CopyItemTickHandler {
    
    private static int tickCounter = 0;
    private static final int TICK_INTERVAL = 20;
    
    @SubscribeEvent
    public void onServerTick(ServerTickEvent.Post event) {
        tickCounter++;
        if (tickCounter < TICK_INTERVAL) {
            return;
        }
        tickCounter = 0;
        
        for (CopyItemBlockEntity entity : CopyItemBlockEntity.getInstances()) {
            entity.tick();
        }
    }
}
