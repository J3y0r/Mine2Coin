package me.jeyor.mine2coin.reward

import org.bukkit.Material
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BlockRewardServiceTest {

    @Test
    fun `returns configured reward for material`() {
        val service = BlockRewardService(mapOf(Material.COAL_ORE to 1.0))

        assertEquals(1.0, service.rewardFor(Material.COAL_ORE))
    }

    @Test
    fun `returns null for unconfigured material`() {
        val service = BlockRewardService(mapOf(Material.COAL_ORE to 1.0))

        assertNull(service.rewardFor(Material.DIAMOND_ORE))
    }

    @Test
    fun `reload replaces configured rewards`() {
        val service = BlockRewardService(mapOf(Material.COAL_ORE to 1.0))

        service.reload(mapOf(Material.GOLD_ORE to 3.0))

        assertNull(service.rewardFor(Material.COAL_ORE))
        assertEquals(3.0, service.rewardFor(Material.GOLD_ORE))
    }
}
