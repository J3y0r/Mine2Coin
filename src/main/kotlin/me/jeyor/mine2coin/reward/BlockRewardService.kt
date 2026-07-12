package me.jeyor.mine2coin.reward

import org.bukkit.Material

class BlockRewardService(
    private val rewards: Map<Material, Double>,
) {

    fun rewardFor(material: Material): Double? = rewards[material]
}
