package com.soldin.orangeore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.soldin.orangeore.commands.OreCommand;
import com.soldin.orangeore.commands.RespawnCommand;

public class OrangeOrePlugin extends JavaPlugin {

    private static OrangeOrePlugin instance;
    private OreGenerator generator;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        // Регистрируем команды
        getCommand("orangenew").setExecutor(new OreCommand());
        getCommand("soldinsera").setExecutor(new RespawnCommand());

        generator = new OreGenerator();

        // Респавн при рестарте
        if (getConfig().getBoolean("respawn-on-restart")) {
            Bukkit.getScheduler().runTaskLater(this, () -> {
                generator.spawnAllDeposits();
                getLogger().info("Залежи руды сгенерированы при старте!");
            }, 20L);
        }

        getLogger().info("OrangeOre запущен! Разработчик: Soldi_n");
    }

    @Override
    public void onDisable() {
        getLogger().info("OrangeOre выключен!");
    }

    public static OrangeOrePlugin getInstance() {
        return instance;
    }

    public OreGenerator getGenerator() {
        return generator;
    }
}
