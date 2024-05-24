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
import org.bukkit.inventory.meta.ItemMeta

class lifestealercommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if(sender !is Player) return false
        if(!sender.isOp) {
            sender.sendMessage(Component.text("You do not have permission to use this command").color(TextColor.color(255,0,0)))
            return false
        }
        val player = sender
        player.inventory.addItem(createLifeStealerItem())
        player.sendMessage("You have received a Life Stealer Blade")

        return true
    }

    private fun createLifeStealerItem(): ItemStack {
        val item = ItemStack(Material.DIAMOND_SWORD)
        val meta:ItemMeta? = item.itemMeta
        if (meta != null) {
            meta.setDisplayName("§c§lLifestealer")
            val lore = listOf(
                "§cBuff: §7Deals 25% of damage as life steal.",
                "§cDebuff: §7Constantly inflicts Hunger II on the player when held."
            )
            meta.lore = lore
            meta.setCustomModelData(1252937)
            item.itemMeta = meta
        }
        return  item
    }

}