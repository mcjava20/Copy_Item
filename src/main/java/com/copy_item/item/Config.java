package com.copy_item.item;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue COPY_AMOUNT = BUILDER
            .comment("The amount of items to copy when the machine is activated")
            .defineInRange("copyAmount", 64, 1, 64);

    static final ModConfigSpec SPEC = BUILDER.build();
}
