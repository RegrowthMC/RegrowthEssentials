package org.lushplugins.regrowthessentials.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Range;
import revxrsal.commands.annotation.Suggest;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;
import revxrsal.commands.bukkit.annotation.CommandPermission;

import java.util.Collection;
import java.util.Objects;

@SuppressWarnings("unused")
public class PlayerCommands {

    @Command("feed")
    @CommandPermission("regrowthessentials.feed")
    public void feed(BukkitCommandActor actor) {
        Player player = actor.requirePlayer();
        player.setFoodLevel(20);
        player.setSaturation(20);

        player.sendMessage(Component.text()
            .content("You have been fed")
            .color(TextColor.fromHexString("#b7faa2"))
            .build());
    }

    @Command("fly")
    @CommandPermission("regrowthessentials.fly")
    public void fly(BukkitCommandActor actor) {
        Player player = actor.requirePlayer();
        boolean allowFlight = !player.getAllowFlight();
        player.setAllowFlight(allowFlight);
        player.setFlying(allowFlight);

        if (allowFlight) {
            player.sendMessage(Component.text()
                .content("Flight has been enabled")
                .color(TextColor.fromHexString("#b7faa2"))
                .build());
        } else {
            player.sendMessage(Component.text()
                .content("Flight has been disabled")
                .color(TextColor.fromHexString("#b7faa2"))
                .build());
        }
    }

    @Command("flyspeed")
    @CommandPermission("regrowthessentials.flyspeed")
    public void flySpeed(BukkitCommandActor actor, @Range(min = -1, max = 1) @Suggest("0.1") float speed) {
        Player player = actor.requirePlayer();
        player.setFlySpeed(speed);

        player.sendMessage(Component.text()
            .content("Your fly speed has been changed to ")
            .color(TextColor.fromHexString("#b7faa2"))
                .append(Component.text()
                    .content(String.valueOf(speed))
                    .color(TextColor.fromHexString("#66b04f")))
            .build());
    }

    @Command("hat")
    @CommandPermission("regrowthessentials.hat")
    public void hat(BukkitCommandActor actor) {
        Player player = actor.requirePlayer();
        PlayerInventory inventory = player.getInventory();

        ItemStack heldItem = inventory.getItemInMainHand();
        ItemStack headItem = inventory.getItem(EquipmentSlot.HEAD);

        inventory.setItemInMainHand(headItem);
        inventory.setItem(EquipmentSlot.HEAD, heldItem);

        player.sendMessage(Component.text()
            .content("Your held item has been put on your head")
            .color(TextColor.fromHexString("#b7faa2"))
            .build());
    }

    @Command("heal")
    @CommandPermission("regrowthessentials.heal")
    public void heal(BukkitCommandActor actor) {
        Player player = actor.requirePlayer();
        AttributeInstance attribute = player.getAttribute(Attribute.MAX_HEALTH);
        player.setHealth(attribute != null ? attribute.getValue() : 20);

        player.sendMessage(Component.text()
            .content("You have been healed")
            .color(TextColor.fromHexString("#b7faa2"))
            .build());
    }

    @Command("near")
    @CommandPermission("regrowthessentials.near")
    public void near(BukkitCommandActor actor) {
        Player player = actor.requirePlayer();
        Collection<Player> nearbyPlayers = player.getWorld().getNearbyPlayers(player.getLocation(), 100, (otherPlayer) -> !Objects.equals(player, otherPlayer));
        if (nearbyPlayers.isEmpty()) {
            player.sendMessage(Component.text()
                .content("There are no players within 100 blocks")
                .color(TextColor.fromHexString("#b7faa2"))
                .build());
            return;
        }

        TextComponent.Builder list = Component.text()
            .content("Players within 100 blocks: ")
            .color(TextColor.fromHexString("#b7faa2"))
            .appendNewline();

        boolean first = true;
        for (Player otherPlayer : nearbyPlayers) {
            if (!first) {
                list.append(Component.text()
                    .content(", ")
                    .color(TextColor.fromHexString("#b7faa2")));
            } else {
                first = false;
            }

            list.append(Component.text()
                .content(player.getName())
                .color(TextColor.fromHexString("#b7faa2")));
        }

        player.sendMessage(list);
    }

    @Command("speed")
    @CommandPermission("regrowthessentials.speed")
    public void speed(BukkitCommandActor actor, @Range(min = -1, max = 1) @Suggest("0.2") float speed) {
        Player player = actor.requirePlayer();
        player.setWalkSpeed(speed);

        player.sendMessage(Component.text()
            .content("Your walk speed has been changed to ")
            .color(TextColor.fromHexString("#b7faa2"))
            .append(Component.text()
                .content(String.valueOf(speed))
                .color(TextColor.fromHexString("#66b04f")))
            .build());
    }

    @Command("sudo")
    @CommandPermission("regrowthessentials.sudo")
    public void sudo(BukkitCommandActor actor, Player target, String command) {
        Bukkit.dispatchCommand(target, command);

        actor.sender().sendMessage(Component.text()
            .content("Ran command on behalf of ")
            .color(TextColor.fromHexString("#b7faa2"))
            .append(Component.text()
                .content(target.getName())
                .color(TextColor.fromHexString("#66b04f")))
            .build());
    }
}
