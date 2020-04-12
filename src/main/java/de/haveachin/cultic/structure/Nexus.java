package de.haveachin.cultic.structure;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Nexus implements Listener {
    private static Plugin plugin;

    private Nexus() {}

    public static void register(final Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new Nexus(), plugin);
    }

    @EventHandler
    public void onCreate(final PlayerInteractEvent event) {
        final ItemStack netherStar = event.getItem();
        if (netherStar == null) return;
        if (netherStar.getType() != Material.NETHER_STAR) return;

        final Block obsidian = event.getClickedBlock();
        if (obsidian == null) return;
        if (obsidian.getType() != Material.OBSIDIAN) return;

        for (int x = -1; x <= 1; x += 2) {
            for (int z = -1; z <= 1; z += 2) {
                final Block seaLantern = obsidian.getRelative(x, 0, z);
                if (seaLantern.getType() != Material.SEA_LANTERN) return;
                final Block purpurBlock = obsidian.getRelative(
                        (int)(0.5 * x - 0.5 * z),
                        0,
                        (int)(0.5 * x + 0.5 * z));
                if (purpurBlock.getType() != Material.PURPUR_BLOCK) return;
            }
        }

        final boolean success = activate(obsidian.getLocation());
        if (!success) return;

        netherStar.setAmount(netherStar.getAmount() - 1);
    }

    private static boolean activate(final Location obsidianLocation) {
        final World world = obsidianLocation.getWorld();
        if (world == null) return false;

        world.playSound(obsidianLocation.add(0.5, 1, 0.5), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 3.0f, 1.0f);
        world.spawnEntity(obsidianLocation, EntityType.ENDER_CRYSTAL);
        return true;
    }
}
