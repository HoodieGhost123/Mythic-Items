package me.hoodieghost.MythicItems.commands

import me.hoodieghost.MythicItems.commands.Metaldetectorcommand.MetalDetectorItem.createMetalDetector
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class Metaldetectorcommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) {
            sender.sendMessage("This command can only be executed by a player")
            return true
        }
        if (!sender.isOp) {
            sender.sendMessage("this can only be run by an op player")
            return true
        }
        val player = sender as Player
        val metalDetectorItem = MetalDetectorItem.createMetalDetector()
        player.inventory.addItem(metalDetectorItem)
        player.sendMessage("${ChatColor.GREEN}Metal Detector added to your inventory.")
        return true
    }
    object MetalDetectorItem {

        fun createMetalDetector(): ItemStack {
            val metalDetector = ItemStack(Material.STICK)
            val meta: ItemMeta = metalDetector.itemMeta


            meta.setDisplayName("${ChatColor.AQUA}${ChatColor.BOLD}Metal Detector")
            meta.setCustomModelData(10552010)
            val lore = listOf(
                "${ChatColor.GRAY}Right-click to scan player's inventory."
            )
            meta.lore = lore
            metalDetector.itemMeta = meta

            return metalDetector
        }
    }


}