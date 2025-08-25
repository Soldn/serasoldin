package com.soldin.orangeore.commands;

import com.soldin.orangeore.OrangeOrePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RespawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("respawn")) {
            OrangeOrePlugin.getInstance().getGenerator().spawnAllDeposits();
            sender.sendMessage("§aЗалежи руды сгенерированы заново!");
            return true;
        }
        sender.sendMessage("§eИспользование: /soldinsera respawn");
        return true;
    }
}
