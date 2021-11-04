package dev.bleepo.bleepotablist;

import org.bukkit.configuration.file.FileConfiguration;

public interface Instance {
    Main plugin = Main.getPlugin();
    FileConfiguration config = plugin.getConfig();
}
