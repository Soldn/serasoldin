package com.soldin.orangeore.commands;

import com.soldin.orangeore.OrangeOrePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class OreCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§e/orangenew give <игрок> <ore|ingot> <кол-во>");
            sender.sendMessage("§e/orangenew reload");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            OrangeOrePlugin.getInstance().reloadConfig();
            sender.sendMessage("§aКонфиг перезагружен!");
            return true;
        }

        if (args[0].equalsIgnoreCase("give")) {
            if (args.length < 4) {
                sender.sendMessage("§cИспользование: /orangenew give <игрок> <ore|ingot> <кол-во>");
                return true;
            }
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage("§cИгрок не найден!");
                return true;
            }
            Material mat;
            if (args[2].equalsIgnoreCase("ore")) mat = Material.IRON_ORE;
            else if (args[2].equalsIgnoreCase("ingot")) mat = Material.IRON_INGOT;
            else {
                sender.sendMessage("§cНеверный тип предмета!");
                return true;
            }
            int amount = Integer.parseInt(args[3]);
            target.getInventory().addItem(new ItemStack(mat, amount));
            sender.sendMessage("§aВыдано!");
        }
        return true;
    }
}
