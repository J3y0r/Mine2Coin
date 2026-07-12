package me.jeyor.mine2coin.config

import org.bukkit.Material
import org.bukkit.configuration.file.YamlConfiguration
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class BlockRewardConfigLoaderTest {

    @Test
    fun `loads valid material rewards from root keys`() {
        val config = YamlConfiguration()
        config.set("coal_ore", 1.0)
        config.set("gold_ore", 3)

        val rewards = BlockRewardConfigLoader().load(config)

        assertEquals(1.0, rewards[Material.COAL_ORE])
        assertEquals(3.0, rewards[Material.GOLD_ORE])
    }

    @Test
    fun `ignores root keys that are not material names`() {
        val config = YamlConfiguration()
        config.set("coal_ore", 1.0)
        config.set("plugin-enabled", true)

        val rewards = BlockRewardConfigLoader().load(config)

        assertEquals(mapOf(Material.COAL_ORE to 1.0), rewards)
    }

    @Test
    fun `ignores non numeric and non positive reward values`() {
        val config = YamlConfiguration()
        config.set("coal_ore", "abc")
        config.set("gold_ore", 0.0)
        config.set("diamond_ore", -1.0)
        config.set("emerald_ore", 2.5)

        val rewards = BlockRewardConfigLoader().load(config)

        assertFalse(Material.COAL_ORE in rewards)
        assertFalse(Material.GOLD_ORE in rewards)
        assertFalse(Material.DIAMOND_ORE in rewards)
        assertEquals(2.5, rewards[Material.EMERALD_ORE])
    }
}
