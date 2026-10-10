package org.lushplugins.regrowthessentials.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;
import revxrsal.commands.bukkit.annotation.CommandPermission;

@SuppressWarnings("unused")
public class TeleportCommands {

    @Command("top")
    @CommandPermission("regrowthessentials.top")
    public void top(BukkitCommandActor actor) {
        Player player = actor.requirePlayer();
        Location currentLocation = player.getLocation();
        Location highestBlock = player.getWorld().getHighestBlockAt(currentLocation).getLocation();

        player.teleportAsync(new Location(
            currentLocation.getWorld(),
            highestBlock.x() + 0.5,
            highestBlock.y() + 1,
            highestBlock.z() + 0.5,
            currentLocation.getYaw(),
            currentLocation.getPitch()
        ));

        player.sendMessage(Component.text()
            .content("You have been teleported to the top of the world")
            .color(TextColor.fromHexString("#b7faa2"))
            .build());
    }
}
