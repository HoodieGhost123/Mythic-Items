package me.hoodieghost.MythicItems.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

class infernobladecommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("Only players can execute this command.")
            return true
        } else if(!sender.isOp) {
            sender.sendMessage("You do not have permission to run this command")
            return true
        }

        val player = sender
        val infernoBlade = ItemStack(Material.NETHERITE_SWORD)
        val meta: ItemMeta? = infernoBlade.itemMeta

        if (meta != null) {
            meta.setDisplayName("${ChatColor.GOLD}${ChatColor.BOLD}Inferno Blade")
            // Set custom model data
            meta.setCustomModelData(1252935)
            val lore = listOf(
                "§cBuff: §7Deals 50% more damage",
                "§cDebuff: §7 Deals fire tick damage when an entity is hit"
            )
            meta.lore = lore
            infernoBlade.itemMeta = meta

        }
        player.inventory.addItem(infernoBlade)
        player.sendMessage("You've received the Inferno Blade!")
        return true


    }}
