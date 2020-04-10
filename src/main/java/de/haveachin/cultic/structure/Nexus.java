package de.haveachin.cultic.structure;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class Nexus implements Listener {
    private static Plugin plugin;

    private Nexus() {}

    public static void register(final Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new Nexus(), plugin);
    }

    public void onCreate(final PlayerInteractEvent event) {
        final Block obsidian = event.getClickedBlock();
        if (obsidian.getType() != Material.OBSIDIAN) return;

        for (int x = -1; x <= 1; x += 2) {
            for (int z = -1; z <= 1; z += 2) {
                final Block seaLantern = obsidian.getRelative(x, 0, z);
                if (seaLantern.getType() != Material.SEA_LANTERN) return;
            }
        }

        // Check for purpur block
        /*  -1  0
             0  1
             0 -1
             1  0 */
    }


    private static boolean activate(final Location obsidianLocation) {
        final World world = obsidianLocation.getWorld();
        if (world == null) {
            return false;
        }

        obsidianLocation.getWorld().spawnEntity(obsidianLocation, EntityType.ENDER_CRYSTAL);
        return true;
    }
}
