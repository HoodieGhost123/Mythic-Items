package me.hoodieghost.MythicItems.listeners

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import java.util.EventListener

class InfernoBladeListener: Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    fun onEntityDamageByEntity(event: EntityDamageByEntityEvent) {
        val attacker = event.damager
        if(attacker is Player){
           val weapon = attacker.inventory.itemInMainHand

            if(isInfernoBlade(weapon)){
                event.damage *= 1.5
                attacker.fireTicks += 80

            }
        }
    }

    private fun isInfernoBlade(item: ItemStack): Boolean {
        if (item.type != Material.NETHERITE_SWORD) {
            return false
        }
        val meta = item.itemMeta
        if (meta == null) {
            return false
        }
        if (!meta.hasDisplayName()) {
            return false
        }


        val expectedDisplayName = "${ChatColor.GOLD}${ChatColor.BOLD}Inferno Blade"
        if (meta.displayName != expectedDisplayName) {
            return false
        }
        return true
    }




}