package me.jeyor.mine2coin.command

import me.jeyor.mine2coin.Mine2CoinPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class ReloadCommand(
    private val plugin: Mine2CoinPlugin,
) : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>,
    ): Boolean {
        if (!sender.hasPermission("mine2coin.reload")) {
            sender.sendMessage("You do not have permission to reload Mine2Coin.")
            return true
        }
        if (args.isEmpty() || !args[0].equals("reload", ignoreCase = true)) {
            return false
        }
        plugin.reloadRewards()
        sender.sendMessage("Mine2Coin configuration reloaded.")
        return true
    }
}
