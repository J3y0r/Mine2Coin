package me.jeyor.mine2coin.economy

import org.bukkit.OfflinePlayer

interface EconomyService {

    fun deposit(player: OfflinePlayer, amount: Double)
}
