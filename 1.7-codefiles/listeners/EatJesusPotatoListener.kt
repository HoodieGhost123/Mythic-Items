package me.hoodieghost.MythicItems.listeners

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemConsumeEvent
import kotlin.math.min

class EatJesusPotatoListener : Listener {

    @EventHandler
    fun onPlayerConsume(event: PlayerItemConsumeEvent) {
        val item = event.item

        // Check if the item is a "Jesus Potato"
        val displayName = Component.text("Jesus Potato")
                .color(TextColor.color(255, 223, 0)) // RGB for a gold color
                .decoration(TextDecoration.BOLD, true)

        if (item.type == Material.POTATO && item.itemMeta?.displayName()?.equals(displayName) == true) {
            event.isCancelled = true  // Cancel the consumption event
            val player = event.player

            val slot = player.inventory.first(item)
            player.inventory.setItem(slot,null)
            player.inventory.setItem(slot,item)

            // Optionally, you can give the player the saturation effect if desired
            player.foodLevel = min(player.foodLevel + 4, 20)  // Potatoes give 4 food points
            player.saturation = min(player.saturation + 2.4f, 20.0f)  // Potatoes give 2.4 saturation
        }
    }
}


