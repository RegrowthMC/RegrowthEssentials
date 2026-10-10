package org.lushplugins.regrowthessentials;

import org.bukkit.plugin.java.JavaPlugin;

public final class RegrowthEssentials extends JavaPlugin {
    private static RegrowthEssentials plugin;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        // Enable implementation
    }

    @Override
    public void onDisable() {
        // Disable implementation
    }

    public static RegrowthEssentials getInstance() {
        return plugin;
    }
}
