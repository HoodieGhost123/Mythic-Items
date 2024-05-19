package me.hoodieghost.MythicItems.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class enderperlbowcommand :CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if(sender !is Player) return false
        if(!sender.isOp) {
            sender.sendMessage(Component.text("You do not have permission to use this command").color(TextColor.color(255,0,0)))
            return false
        }
        val enderperlbowItem: ItemStack = ItemStack(Material.BOW)
        val itemMeta = enderperlbowItem.itemMeta

        itemMeta.displayName(Component.text("Ender Perl Bow").color(TextColor.color(68,46,165)).decorate(TextDecoration.BOLD))
        itemMeta.setCustomModelData(1234567)

        enderperlbowItem.itemMeta = itemMeta

        sender.inventory.addItem(enderperlbowItem)

        return false
    }
}