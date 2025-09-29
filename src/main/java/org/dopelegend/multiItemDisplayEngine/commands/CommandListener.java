package org.dopelegend.multiItemDisplayEngine.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.Plugin;

public class CommandListener {

    public CommandListener(Plugin plugin) {
        LifecycleEventManager<Plugin> lifecycleEventManager = plugin.getLifecycleManager();

        lifecycleEventManager.registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(modelCommand());
        });
    }

    private LiteralCommandNode<CommandSourceStack> modelCommand(){
        return Commands.literal("model")
                .then(Commands.literal("spawn")
                        .then(Commands.argument("model name", StringArgumentType.greedyString()).executes(ModelCommand::spawnModelByNameCommand)
                        )
                ).build();
    }
}
