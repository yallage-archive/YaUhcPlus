package io.github.apjifengc.uhcplus;

import com.gmail.val59000mc.configuration.YamlFile;
import com.gmail.val59000mc.exceptions.ParseException;
import com.gmail.val59000mc.utils.JsonItemStack;
import com.gmail.val59000mc.utils.JsonItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Config {
    public static JsonItemStack SWORD;
    public static int addDamage;
    public static List<PotionEffect> holdEffects;
    public static double cd;
    public static boolean isAddDamage;

    public static void loadConfig(FileConfiguration config) {
        Bukkit.getLogger().info("[UhcPlus] Loading ultimates");
        YamlFile cfg = null;
        ConfigurationSection customUltimateSection = cfg.getConfigurationSection("custom-ultimates");
        Set<String> ultimateKeys = customUltimateSection.getKeys(false);
        for(String name : ultimateKeys) {
            try{
                Bukkit.getLogger().info("[UhcPlus] Loading ultimate "+name);

                SWORD = JsonItemUtils.getItemFromJson(Objects.requireNonNull(config.getString("sword"),
                        "The config 'sword' doesn't exist!"));
                addDamage = config.getInt("addDamage");
                holdEffects = config.getMapList("holdEffects")
                        .stream().map(map -> new PotionEffect(
                                PotionEffectType.getByName((String) map.get("type")),
                                Integer.MAX_VALUE,
                                ((Integer) map.get("level")) - 1)
                        ).collect(Collectors.toList());
                cd = config.getDouble("cd");
                isAddDamage = config.getBoolean("isAddDamage");
            } catch (Exception e) {
                UhcPlus.getInstance().getLogger().warning(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
