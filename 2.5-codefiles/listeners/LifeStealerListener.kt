package me.hoodieghost.MythicItems.listeners

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerItemHeldEvent
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType


class LifeStealerListener: Listener {
    @EventHandler
    fun onEntityDamageByEntity(event: EntityDamageByEntityEvent) {
        val damager = event.damager
        if (damager is Player) {
            val item = damager.inventory.itemInMainHand
            val meta: ItemMeta? = item.itemMeta
            if (meta != null && meta.hasCustomModelData() && meta.customModelData == 1252937) {
                val damage = event.damage
                val lifesteal = damage * 0.25

                val newHealth = Math.min(damager.health + lifesteal, damager.maxHealth)
                damager.health = newHealth
            }
        }
    }
    @EventHandler
    fun onPlayerItemHeld(event: PlayerItemHeldEvent) {
        val player = event.player
        val newItem = player.inventory.getItem(event.newSlot)
        val oldItem = player.inventory.getItem(event.previousSlot)

        if (newItem != null) {
            val newMeta = newItem.itemMeta
            if (newMeta != null && newMeta.hasCustomModelData() && newMeta.customModelData == 1252937) {
                player.addPotionEffect(PotionEffect(PotionEffectType.HUNGER, Int.MAX_VALUE, 7, true, false))
            }
        }

        if (oldItem != null) {
            val oldMeta = oldItem.itemMeta
            if (oldMeta != null && oldMeta.hasCustomModelData() && oldMeta.customModelData == 1252937) {
                player.removePotionEffect(PotionEffectType.HUNGER)
            }
        }
    }
}