package org.lushplugins.regrowthessentials.command;

import org.bukkit.entity.Player;
import org.bukkit.inventory.MenuType;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;
import revxrsal.commands.bukkit.annotation.CommandPermission;

import java.util.*;

@SuppressWarnings("unused")
public class ContainerCommands {

    private void openInventory(BukkitCommandActor actor, MenuType.Typed<?, ?> menuType) {
        Player player = actor.requirePlayer();
        player.openInventory(menuType.create(player));
    }

    @Command("anvil")
    @CommandPermission("regrowthessentials.anvil")
    public void anvil(BukkitCommandActor actor) {
        openInventory(actor, MenuType.ANVIL);
    }

    @Command({"craft", "workbench", "wb"})
    @CommandPermission("regrowthessentials.workbench")
    public void craftingTable(BukkitCommandActor actor) {
        openInventory(actor, MenuType.CRAFTING);
    }

    @Command("enchanting")
    @CommandPermission("regrowthessentials.enchanting")
    public void enchantingTable(BukkitCommandActor actor) {
        openInventory(actor, MenuType.ENCHANTMENT);
    }

    @Command({"enderchest", "ec"})
    @CommandPermission("regrowthessentials.enderchest")
    public void enderChest(BukkitCommandActor actor) {
        Player player = actor.requirePlayer();
        player.openInventory(player.getEnderChest());
    }

    @Command("grindstone")
    @CommandPermission("regrowthessentials.grindstone")
    public void grindstone(BukkitCommandActor actor) {
        openInventory(actor, MenuType.GRINDSTONE);
    }

    @Command("loom")
    @CommandPermission("regrowthessentials.loom")
    public void loom(BukkitCommandActor actor) {
        openInventory(actor, MenuType.LOOM);
    }

    @Command("smithing")
    @CommandPermission("regrowthessentials.smithing")
    public void smithingTable(BukkitCommandActor actor) {
        openInventory(actor, MenuType.SMITHING);
    }

    @Command("cartography")
    @CommandPermission("regrowthessentials.cartography")
    public void cartographyTable(BukkitCommandActor actor) {
        openInventory(actor, MenuType.CARTOGRAPHY_TABLE);
    }

    @Command("stonecutter")
    @CommandPermission("regrowthessentials.stonecutter")
    public void stonecutter(BukkitCommandActor actor) {
        openInventory(actor, MenuType.STONECUTTER);
    }
}
