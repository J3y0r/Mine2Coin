package me.jeyor.mine2coin.economy

import net.milkbowl.vault.economy.Economy
import org.bukkit.OfflinePlayer

class VaultEconomyService(
    private val economy: Economy,
) : EconomyService {

    override fun deposit(player: OfflinePlayer, amount: Double) {
        economy.depositPlayer(player, amount)
    }
}
