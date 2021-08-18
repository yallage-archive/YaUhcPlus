package io.github.apjifengc.uhcplus.listener;

import io.github.apjifengc.uhcplus.Config;
import io.github.apjifengc.uhcplus.UhcPlus;
import io.github.apjifengc.uhcplus.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class SwordListener implements Listener {
    private UhcPlus plugin;
    private Map<Player, BukkitTask> taskMap = new HashMap<>();

    public SwordListener(UhcPlus plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            var from = event.getDamager();
            if (!(from instanceof Player)) return;
            var to = event.getEntity();
            if (taskMap.containsKey(from)) return;
            if (ItemUtil.isSimilar(Config.SWORD, from.getInventory().getItemInMainHand())) {
                event.setDamage(event.getDamage() + Config.addDamage);
                from.getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
                to.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1,0));
                taskMap.put(from, new BukkitRunnable() {
                    @Override
                    public void run() {
                        taskMap.remove(from);
                    }
                }.runTaskLater(plugin, (long) (Config.cd * 20L)));
            }
        }
    }
}
