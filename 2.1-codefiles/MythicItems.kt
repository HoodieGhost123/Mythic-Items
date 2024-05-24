package me.hoodieghost.MythicItems


import me.hoodieghost.MythicItems.commands.*
import me.hoodieghost.MythicItems.listeners.EatJesusPotatoListener
import me.hoodieghost.MythicItems.listeners.InfernoBladeListener
import me.hoodieghost.MythicItems.listeners.LifeStealerListener
import me.hoodieghost.MythicItems.listeners.ShootBowListener
import org.bukkit.plugin.java.JavaPlugin

class MythicItems : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        logger.info("The plug has been inserted")

        registerCommands()
        registerListeners()
        server.pluginManager.registerEvents(InfernoBladeListener(),this)
        logger.info("InfernobladeListener registered succesfully")
    }

    private fun registerCommands(){
        getCommand("jump")?.setExecutor(JumpCommand())
        getCommand("ender-perl-bow")?.setExecutor(enderperlbowcommand())
        getCommand("jesuspotato")?.setExecutor(jesuspotatocommand())
        getCommand("infernoblade")?.setExecutor(infernobladecommand())
        getCommand("lifestealer")?.setExecutor(lifestealercommand())
        logger.info("registered commands")
    }
    private fun registerListeners(){
        server.pluginManager.registerEvents(ShootBowListener(),this)
        server.pluginManager.registerEvents(EatJesusPotatoListener(),this)
        server.pluginManager.registerEvents(LifeStealerListener(),this)
        logger.info("registered listeners")
    }




    override fun onDisable() {
        // Plugin shutdown logic

    }
}
