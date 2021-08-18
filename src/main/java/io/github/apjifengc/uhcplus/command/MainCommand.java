package io.github.apjifengc.uhcplus.command;

import io.github.apjifengc.uhcplus.Config;
import io.github.apjifengc.uhcplus.UhcPlus;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Collections;
import java.util.List;

public class MainCommand implements TabCompleter, CommandExecutor {
    private final UhcPlus plugin;

    public MainCommand(UhcPlus plugin) {
        this.plugin = plugin;
        Bukkit.getPluginCommand("uhcplus").setExecutor(this);
        Bukkit.getPluginCommand("uhcplus").setTabCompleter(this);
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) return Collections.emptyList();
        return Collections.singletonList("reload");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1 && strings[0].equals("reload")) {
            plugin.reloadConfig();
            Config.loadConfig(plugin.getConfig());
            commandSender.sendMessage("Reload complete.");
            return true;
        }
        return false;
    }
}
