package io.github.apjifengc.uhcplus;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.apjifengc.uhcplus.command.MainCommand;
import io.github.apjifengc.uhcplus.listener.SwordListener;
import lombok.Getter;

public final class UhcPlus extends JavaPlugin {

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
        Config2.loadConfig2(getConfig());
        new SwordListener(this);
        new MainCommand(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
