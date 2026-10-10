package org.lushplugins.regrowthessentials.command;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;
import revxrsal.commands.bukkit.annotation.CommandPermission;

@SuppressWarnings("unused")
public class ChatCommands {

    @Command("broadcast")
    @CommandPermission("regrowthessentials.broadcast")
    public void broadcast(BukkitCommandActor actor, String message) {
        Bukkit.broadcast(MiniMessage.miniMessage().deserialize(message));
    }
}
