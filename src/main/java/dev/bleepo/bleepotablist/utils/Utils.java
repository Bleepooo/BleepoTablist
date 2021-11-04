package dev.bleepo.bleepotablist.utils;

import dev.bleepo.bleepotablist.Instance;
import dev.bleepo.bleepotablist.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class Utils implements Instance {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static int getPing(Player player) {
        try {
            String a = Bukkit.getServer().getClass().getPackage().getName().substring(23);
            Class<?> b = Class.forName("org.bukkit.craftbukkit." + a + ".entity.CraftPlayer");
            Object c = b.getMethod("getHandle", new Class[0]).invoke(player);
            return (int) (Integer) c.getClass().getDeclaredField("ping").get(c);
        } catch (Exception e) {
            return 0;
        }
    }

    public static String getTps() {
        double tps = Math.max(Bukkit.getTPS()[0], 20);
        return df.format(tps);
    }

    public static String getUptime() {
        long currentTime = System.currentTimeMillis();
        long timeDifference = currentTime - Main.timeEnabled;
        return getFormattedInterval(timeDifference);
    }

    public static int getPlayerCount() {
        return Bukkit.getOnlinePlayers().size();
    }

    public static String getFormattedInterval(long ms) {
        long seconds = ms / 1000L % 60L;
        long minutes = ms / 60000L % 60L;
        long hours = ms / 3600000L % 24L;
        long days = ms / 86400000L;
        return String.format("%dd %02dh %02dm %02ds", days, hours, minutes, seconds);
    }

    public static ChatColor getTPSColor(String input) {
        if (!input.equals("*20")) {
            String toDouble = input.split("\\.")[0];
            double tps = Double.parseDouble(toDouble);
            if (tps >= 18.0D) {
                return ChatColor.GREEN;
            } else {
                return tps >= 13.0D && tps < 18.0D ? ChatColor.YELLOW : ChatColor.RED;
            }
        } else {
            return ChatColor.GREEN;
        }
    }

    public static String formatTab(Player player, String text) {
        text = ChatColor.translateAlternateColorCodes('&', text);
        return text.replaceAll(
                "%ping%", String.valueOf(getPing(player))).replaceAll(
                "%tps%", getTPSColor(getTps()) + getTps()).replaceAll(
                "%uptime%", getUptime()).replaceAll(
                "%players%", String.valueOf(getPlayerCount()).replaceAll("&", "§"));
    }
}
