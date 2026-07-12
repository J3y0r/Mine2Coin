package me.jeyor.mine2coin

import me.jeyor.mine2coin.config.BlockRewardConfigLoader
import me.jeyor.mine2coin.economy.VaultEconomyService
import me.jeyor.mine2coin.listener.MiningListener
import me.jeyor.mine2coin.reward.BlockRewardService
import net.milkbowl.vault.economy.Economy
import org.bukkit.plugin.java.JavaPlugin

class Mine2CoinPlugin : JavaPlugin() {

    override fun onEnable() {
        saveDefaultConfig()

        val economy = setupEconomy()
        if (economy == null) {
            logger.severe("Vault economy provider not found.")
            server.pluginManager.disablePlugin(this)
            return
        }

        val rewardService = BlockRewardService(BlockRewardConfigLoader().load(config))
        server.pluginManager.registerEvents(
            MiningListener(rewardService, VaultEconomyService(economy)),
            this,
        )
    }

    private fun setupEconomy(): Economy? {
        val registration = server.servicesManager.getRegistration(Economy::class.java) ?: return null
        return registration.provider
    }
}
