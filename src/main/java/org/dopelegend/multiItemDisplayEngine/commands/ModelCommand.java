package org.dopelegend.multiItemDisplayEngine.commands;

import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;
import org.dopelegend.multiItemDisplayEngine.itemDisplay.utils.itemDisplayGroups.ItemDisplayGroup;

public class ModelCommand {
    public static int spawnModelByNameCommand(CommandContext<CommandSourceStack> ctx) {
        if (!(ctx.getSource().getSender() instanceof Player)){
            ctx.getSource().getSender().sendRichMessage("<red> <bold> Only players can execute this command");
            return 0;
        }

        ItemDisplayGroup itemDisplayGroup = new ItemDisplayGroup(ctx.getSource().getLocation(), ctx.getArgument("model name", String.class));
        itemDisplayGroup.Spawn();
        return 1;
    }
}
