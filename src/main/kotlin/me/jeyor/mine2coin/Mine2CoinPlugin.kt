package me.jeyor.mine2coin

import me.jeyor.mine2coin.command.ReloadCommand
import me.jeyor.mine2coin.config.BlockRewardConfigLoader
import me.jeyor.mine2coin.economy.VaultEconomyService
import me.jeyor.mine2coin.listener.MiningListener
import me.jeyor.mine2coin.reward.BlockRewardService
import net.milkbowl.vault.economy.Economy
import org.bukkit.Material
import org.bukkit.plugin.java.JavaPlugin

class Mine2CoinPlugin : JavaPlugin() {

    private lateinit var rewardService: BlockRewardService

    override fun onEnable() {
        saveDefaultConfig()

        val economy = setupEconomy()
        if (economy == null) {
            logger.severe("Vault economy provider not found.")
            server.pluginManager.disablePlugin(this)
            return
        }

        rewardService = BlockRewardService(loadRewards())
        server.pluginManager.registerEvents(
            MiningListener(rewardService, VaultEconomyService(economy)),
            this,
        )

        val command = getCommand("mine2coin")
        if (command == null) {
            logger.warning("Command 'mine2coin' missing from plugin.yml")
        } else {
            command.setExecutor(ReloadCommand(this))
        }
    }

    fun reloadRewards() {
        reloadConfig()
        rewardService.reload(loadRewards())
    }

    private fun loadRewards(): Map<Material, Double> =
        BlockRewardConfigLoader().load(config)

    private fun setupEconomy(): Economy? {
        val registration = server.servicesManager.getRegistration(Economy::class.java) ?: return null
        return registration.provider
    }
}
