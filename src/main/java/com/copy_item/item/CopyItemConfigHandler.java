package com.copy_item.item;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;

public class CopyItemConfigHandler {

    @SubscribeEvent
    public void onConfigReloading(ModConfigEvent.Reloading event) {
        if (event.getConfig().getModId().equals("copyitem")) {
        }
    }
}
