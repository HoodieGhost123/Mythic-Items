package me.hoodieghost.MythicItems


import me.hoodieghost.MythicItems.commands.JumpCommand
import me.hoodieghost.MythicItems.commands.enderperlbowcommand
import me.hoodieghost.MythicItems.commands.infernobladecommand
import me.hoodieghost.MythicItems.commands.jesuspotatocommand
import me.hoodieghost.MythicItems.listeners.EatJesusPotatoListener
import me.hoodieghost.MythicItems.listeners.InfernoBladeListener
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
        logger.info("registered commands")
    }
    private fun registerListeners(){
        server.pluginManager.registerEvents(ShootBowListener(),this)
        server.pluginManager.registerEvents(EatJesusPotatoListener(),this)
        logger.info("registered listeners")
    }




    override fun onDisable() {
        // Plugin shutdown logic

    }
}
