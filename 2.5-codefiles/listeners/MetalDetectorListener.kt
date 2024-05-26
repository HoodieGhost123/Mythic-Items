package me.hoodieghost.MythicItems.listeners

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionType
import java.util.*
import kotlin.collections.HashMap

class MetalDetectorListener: Listener {

    private val cooldown: MutableMap<UUID, Long> = HashMap()
    private val cooldownTime = 2000 // 2 seconds in milliseconds

    @EventHandler
    fun onPlayerInteractEntity(event:PlayerInteractEntityEvent) {
        val player = event.player
        val target = event.rightClicked

        if(target is Player) {
            val item = player.inventory.itemInMainHand

            if (isMetalDetector(item)) {
                val now = System.currentTimeMillis()
                val lastUsed = cooldown[player.uniqueId]
                if (lastUsed != null && now - lastUsed < cooldownTime) {
                    return // Still in cooldown, do nothing
                }
                cooldown[player.uniqueId] = now

                scanInventory(player, target)
            }
        }
    }
    private fun isMetalDetector(item:ItemStack): Boolean {
        if (item.type != Material.STICK) return false
        val meta = item.itemMeta ?: return false
        return meta.displayName == "${ChatColor.AQUA}${ChatColor.BOLD}Metal Detector"
    }

    private fun scanInventory(player:Player, target: Player) {
        val inventory = target.inventory
        val foundItems = mutableListOf<String>()

        val itemTypes = mapOf(
            "Sword" to listOf(Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD),
            "Bow" to listOf(Material.BOW),
            "Axe" to listOf(Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE),
            "Pickaxe" to listOf(Material.WOODEN_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE, Material.NETHERITE_PICKAXE),
            "Elytra" to listOf(Material.ELYTRA),
            "Basic Block" to listOf(Material.DIRT, Material.STONE, Material.COBBLESTONE, Material.SAND, Material.GRAVEL, Material.OAK_LOG, Material.SPRUCE_LOG, Material.BIRCH_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG, Material.DARK_OAK_LOG), // Add more as needed
            "Ender Pearl" to listOf(Material.ENDER_PEARL),
            "Totem" to listOf(Material.TOTEM_OF_UNDYING),
            "Shovel" to listOf(Material.WOODEN_SHOVEL, Material.STONE_SHOVEL, Material.IRON_SHOVEL, Material.GOLDEN_SHOVEL,Material.DIAMOND_SHOVEL,Material.NETHERITE_SHOVEL),

        )
        for ((name,materials) in itemTypes) {
            for (material in materials) {
                if (inventory.contains(material)) {
                    foundItems.add(name)
                    break
                }
            }
        }
        val potionTypes = mapOf(
            "Healing Pot" to PotionType.INSTANT_HEAL,
            "Strength Pot" to PotionType.STRENGTH,
            "Invis Pot" to PotionType.INVISIBILITY
        )

        for ((name,potionType) in potionTypes) {
            for (item in inventory) {
                if (item?.type == Material.POTION || item?.type == Material.SPLASH_POTION || item?.type == Material.LINGERING_POTION) {
                    val potionMeta = item.itemMeta as? PotionMeta
                    if (potionMeta?.basePotionData?.type == potionType) {
                        foundItems.add(name)
                        break
                    }
                }
            }
        }

        val message = if (foundItems.isNotEmpty()) {
            "${ChatColor.GREEN}${target.name} has the following items: ${foundItems.joinToString(", ")}"
        } else {
            "${ChatColor.RED}${target.name} does not have any of the specified items."
        }

        player.sendMessage(message)
    }
}