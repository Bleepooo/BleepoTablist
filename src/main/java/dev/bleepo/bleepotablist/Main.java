package dev.bleepo.bleepotablist;

import dev.bleepo.bleepotablist.runnables.Tablist;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import static dev.bleepo.bleepotablist.Instance.plugin;

public final class Main extends JavaPlugin {
    public static Long timeEnabled;

    public static Main getPlugin() {
        return getPlugin(Main.class);
    }

    @Override
    public void onEnable() {
        timeEnabled = System.currentTimeMillis();
        saveDefaultConfig();
        Bukkit.getScheduler().runTaskTimer(plugin, new Tablist(), 0L, 20L);
        getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6[&bBleepo&3Tablist&6] Loaded and Enabled."));

    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6[&bBleepo&3Tablist&6] Unloaded and Disabled."));
    }
}
