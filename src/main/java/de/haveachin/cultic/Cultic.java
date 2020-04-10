package de.haveachin.cultic;

import de.haveachin.cultic.structure.Nexus;
import org.bukkit.plugin.java.JavaPlugin;

public class Cultic extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("May the gods have mercy upon your soul!");

        Nexus.register(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("All our gods have abandoned us.");
    }
}
