package io.github.apjifengc.uhcplus;

import java.util.HashMap;
import java.util.Map;

import com.gmail.val59000mc.exceptions.ParseException;
import com.gmail.val59000mc.utils.JsonItemStack;
import com.gmail.val59000mc.utils.JsonItemUtils;

import io.github.apjifengc.uhcplus.command.MainCommand;
import io.github.apjifengc.uhcplus.listener.SwordListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class UhcPlus extends JavaPlugin {
    private final Map<Player, Integer> tasksMap = new HashMap<>();
    private final FileConfiguration config = getConfig();

    @Getter
    private static UhcPlus instance;
    public UhcPlus() {
        instance = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        Config.loadConfig(getConfig());

        new MainCommand(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
