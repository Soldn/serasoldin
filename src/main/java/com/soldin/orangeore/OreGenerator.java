package com.soldin.orangeore;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Random;

public class OreGenerator {

    private final Random random = new Random();

    public void spawnAllDeposits() {
        String worldName = OrangeOrePlugin.getInstance().getConfig().getString("respawn-world", "world");
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            OrangeOrePlugin.getInstance().getLogger().warning("Мир " + worldName + " не найден!");
            return;
        }

        int spawnRate = OrangeOrePlugin.getInstance().getConfig().getInt("ore.spawn-rate");
        int clusterMin = OrangeOrePlugin.getInstance().getConfig().getInt("ore.cluster-min");
        int clusterMax = OrangeOrePlugin.getInstance().getConfig().getInt("ore.cluster-max");

        int radius = 20;
        for (int cx = -radius; cx <= radius; cx++) {
            for (int cz = -radius; cz <= radius; cz++) {
                for (int i = 0; i < spawnRate; i++) {
                    int x = cx * 16 + random.nextInt(16);
                    int z = cz * 16 + random.nextInt(16);
                    int y = world.getHighestBlockYAt(x, z);

                    int clusterSize = clusterMin + random.nextInt(clusterMax - clusterMin + 1);
                    for (int j = 0; j < clusterSize; j++) {
                        Block block = world.getBlockAt(x + random.nextInt(2), y, z + random.nextInt(2));
                        if (block.getType() == Material.GRASS_BLOCK || block.getType() == Material.STONE) {
                            block.setType(Material.IRON_ORE);
                        }
                    }
                }
            }
        }
    }
}
