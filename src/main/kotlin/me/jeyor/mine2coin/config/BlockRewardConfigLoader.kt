package me.jeyor.mine2coin.config

import org.bukkit.Material
import org.bukkit.configuration.file.FileConfiguration

class BlockRewardConfigLoader {

    fun load(config: FileConfiguration): Map<Material, Double> {
        return config.getKeys(false)
            .mapNotNull { key -> loadReward(config, key) }
            .toMap()
    }

    private fun loadReward(config: FileConfiguration, key: String): Pair<Material, Double>? {
        val material = Material.matchMaterial(key) ?: return null
        if (!config.isDouble(key) && !config.isInt(key)) return null

        val amount = config.getDouble(key)
        if (amount <= 0.0) return null

        return material to amount
    }
}
