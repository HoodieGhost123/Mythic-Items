package me.hoodieghost.MythicItems.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack


class jesuspotatocommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return if (sender is Player && sender.isOp) {
            val player = sender
            val potato = createJesusPotato()
            player.inventory.addItem(potato)
            player.sendMessage(Component.text("You have been given a Jesus Potato!").color(TextColor.color(0,120,140)).decoration(TextDecoration.BOLD, true ))
            true
        } else {
            sender.sendMessage(Component.text("This command can only be run by an op player.").color(TextColor.color(255,0,0)))
            true
        }
    }

    private fun createJesusPotato(): ItemStack {
        val potato = ItemStack(Material.POTATO)
        val meta = potato.itemMeta
        meta.displayName(Component.text("Jesus Potato").color(TextColor.color(255,223,0)).decoration(TextDecoration.BOLD,true) )
        meta.setCustomModelData(594236)
        val lore = listOf(
            "Infinitely replenishing food source",
            "§cEXCEPT FOR WHEN YOU FEED IT TO A PIG"
        )
        meta.lore = lore

        meta?.addEnchant(Enchantment.ARROW_INFINITE, 1,true)

        meta?.addItemFlags(ItemFlag.HIDE_ENCHANTS)

        potato.itemMeta = meta
        return potato
    }
}