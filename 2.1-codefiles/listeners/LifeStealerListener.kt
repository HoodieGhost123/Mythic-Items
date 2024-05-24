package me.hoodieghost.MythicItems.listeners

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerItemHeldEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType


class LifeStealerListener: Listener {
    @EventHandler
    fun onEntityDamageByEntity(event: EntityDamageByEntityEvent) {
        val damager = event.damager
        if (damager is Player) {
            val item = damager.inventory.itemInMainHand
            if (item.hasItemMeta() && item.itemMeta?.customModelData == 1252937) {
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
        val item = player.inventory.getItem(event.newSlot)
        if (item != null && item.hasItemMeta() && item.itemMeta?.customModelData == 1252937) {
            player.addPotionEffect(PotionEffect(PotionEffectType.HUNGER, 999999,7,true,false))
        } else {
            player.removePotionEffect(PotionEffectType.HUNGER)
        }
    }

}