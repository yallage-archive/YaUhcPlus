package io.github.apjifengc.uhcplus;

import com.gmail.val59000mc.utils.JsonItemStack;
import com.gmail.val59000mc.utils.JsonItemUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Config2 {
    public static JsonItemStack SWORD;
    public static int addDamage;
    public static List<PotionEffect> holdEffects;
    public static double cd;

    public static void loadConfig2(FileConfiguration config2) {
        try {
            SWORD = JsonItemUtils.getItemFromJson(Objects.requireNonNull(config2.getString("sword"),
                    "The config2 'sword' doesn't exist!"));
            addDamage = config2.getInt("addDamage");
            holdEffects = config2.getMapList("holdEffects")
                    .stream().map(map -> new PotionEffect(
                            PotionEffectType.getByName((String) map.get("type")),
                            Integer.MAX_VALUE,
                            ((Integer) map.get("level")) - 1)
                    ).collect(Collectors.toList());
            cd = config2.getDouble("cd");
        } catch (Exception e) {
            UhcPlus.getInstance().getLogger().warning(e.getMessage());
            e.printStackTrace();
        }
    }
}
