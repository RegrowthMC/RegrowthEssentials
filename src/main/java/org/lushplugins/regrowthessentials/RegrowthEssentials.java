package org.lushplugins.regrowthessentials;

import org.bukkit.plugin.java.JavaPlugin;
import org.lushplugins.regrowthessentials.command.*;
import revxrsal.commands.bukkit.BukkitLamp;

public final class RegrowthEssentials extends JavaPlugin {

    @Override
    public void onEnable() {
        BukkitLamp.builder(this)
            .build()
            .register(
                new Aliases(),
                new ChatCommands(),
                new ContainerCommands(),
                new ItemCommands(),
                new PlayerCommands(),
                new TeleportCommands()
            );
    }
}
