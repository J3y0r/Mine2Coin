package me.jeyor.mine2coin.listener

import me.jeyor.mine2coin.economy.EconomyService
import me.jeyor.mine2coin.reward.BlockRewardService
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class MiningListener(
    private val rewards: BlockRewardService,
    private val economy: EconomyService,
) : Listener {

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val reward = rewards.rewardFor(event.block.type) ?: return
        event.isDropItems = false

        economy.deposit(event.player, reward)
        event.player.playSound(event.player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f)
    }
}
