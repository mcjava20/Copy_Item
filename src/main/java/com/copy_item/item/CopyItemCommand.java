package com.copy_item.item;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class CopyItemCommand {

    public static void register(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(Commands.literal("copyitem")
                .then(Commands.literal("reset")
                    .executes(CopyItemCommand::resetConfig)
                )
        );
    }

    private static int resetConfig(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSystemMessage(Component.literal("Copy item config reloaded"));
        return 1;
    }
}
