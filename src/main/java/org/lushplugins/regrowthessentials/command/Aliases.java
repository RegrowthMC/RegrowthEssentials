package org.lushplugins.regrowthessentials.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.annotation.CommandPermission;

@SuppressWarnings("unused")
public class Aliases {

    @Command("gmc")
    @CommandPermission("minecraft.command.gamemode")
    public void gmc(CommandSender sender) {
        Bukkit.dispatchCommand(sender, "gamemode creative");
    }

    @Command("gms")
    @CommandPermission("minecraft.command.gamemode")
    public void gms(CommandSender sender) {
        Bukkit.dispatchCommand(sender, "gamemode survival");
    }

    @Command("gma")
    @CommandPermission("minecraft.command.gamemode")
    public void gma(CommandSender sender) {
        Bukkit.dispatchCommand(sender, "gamemode adventure");
    }

    @Command("gmsp")
    @CommandPermission("minecraft.command.gamemode")
    public void gmsp(CommandSender sender) {
        Bukkit.dispatchCommand(sender, "gamemode spectator");
    }
}
