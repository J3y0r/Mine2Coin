package me.jeyor.mine2coin.reward

import org.bukkit.Material

class BlockRewardService(rewards: Map<Material, Double>) {

    private var rewards: Map<Material, Double> = rewards

    fun rewardFor(material: Material): Double? = rewards[material]

    fun reload(rewards: Map<Material, Double>) {
        this.rewards = rewards
    }
}
