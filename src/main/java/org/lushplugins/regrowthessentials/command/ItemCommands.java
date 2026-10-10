package org.lushplugins.regrowthessentials.command;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ResolvableProfile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;
import revxrsal.commands.bukkit.annotation.CommandPermission;

@SuppressWarnings("unused")
public class ItemCommands {

    @Command("rename")
    @CommandPermission("regrowthessentials.rename")
    public void rename(BukkitCommandActor actor, String name) {
        Player player = actor.requirePlayer();
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = heldItem.getItemMeta();
        if (itemMeta != null) {
            Component customName = MiniMessage.miniMessage().deserialize(name)
                .decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE);
            itemMeta.customName(customName);
            heldItem.setItemMeta(itemMeta);

            player.sendMessage(Component.text()
                .content("Your held item has been renamed to ")
                .color(TextColor.fromHexString("#b7faa2"))
                    .append(customName)
                .build());
        } else {
            player.sendMessage(Component.text()
                .content("Your held item could not be renamed")
                .color(TextColor.fromHexString("#ff6969"))
                .build());
        }
    }

    @Command("repair")
    @CommandPermission("regrowthessentials.repair")
    public void repair(BukkitCommandActor actor) {
        Player player = actor.requirePlayer();
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        if (heldItem.getItemMeta() instanceof Damageable itemMeta && itemMeta.hasDamage()) {
            itemMeta.resetDamage();
            heldItem.setItemMeta(itemMeta);

            player.sendMessage(Component.text()
                .content("Your held item has been repaired")
                .color(TextColor.fromHexString("#b7faa2"))
                .build());
        } else {
            player.sendMessage(Component.text()
                .content("Your held item cannot be repaired")
                .color(TextColor.fromHexString("#ff6969"))
                .build());
        }
    }

    @Command("repairall")
    @CommandPermission("regrowthessentials.repairall")
    public void repairAll(BukkitCommandActor actor) {
        Player player = actor.requirePlayer();
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getItemMeta() instanceof Damageable itemMeta && itemMeta.hasDamage()) {
                itemMeta.resetDamage();
                item.setItemMeta(itemMeta);
            }
        }

        player.sendMessage(Component.text()
            .content("All items in your inventory have been repaired")
            .color(TextColor.fromHexString("#b7faa2"))
            .build());
    }

    @Command("skull")
    @CommandPermission("regrowthessentials.skull")
    public void skull(BukkitCommandActor actor) {
        Player player = actor.requirePlayer();

        ItemStack item = ItemStack.of(Material.PLAYER_HEAD);
        item.setData(DataComponentTypes.PROFILE, ResolvableProfile.resolvableProfile()
            .addProperties(player.getPlayerProfile().getProperties())
            .build());

        player.give(item);

        player.sendMessage(Component.text()
            .content("You have received your head")
            .color(TextColor.fromHexString("#b7faa2"))
            .build());
    }

    @Command("skull")
    @CommandPermission("regrowthessentials.skull.other")
    public void skullOther(BukkitCommandActor actor, String target) {
        Player player = actor.requirePlayer();

        player.sendMessage(Component.text()
            .content("Loading player head...")
            .color(TextColor.fromHexString("#b7faa2"))
            .build());

        ResolvableProfile.resolvableProfile()
            .name(target)
            .build()
            .resolve()
            .thenAccept((profile) -> {
                ItemStack item = ItemStack.of(Material.PLAYER_HEAD);
                item.setData(DataComponentTypes.PROFILE, ResolvableProfile.resolvableProfile()
                    .addProperties(profile.getProperties())
                    .build());

                player.give(item);

                player.sendMessage(Component.text()
                    .content("You have received ")
                    .color(TextColor.fromHexString("#b7faa2"))
                    .append(Component.text()
                        .content(target)
                        .color(TextColor.fromHexString("#66b04f"))
                        .append(Component.text()
                            .content("'s head")
                            .color(TextColor.fromHexString("#b7faa2"))))
                    .build());
            });
    }
}
