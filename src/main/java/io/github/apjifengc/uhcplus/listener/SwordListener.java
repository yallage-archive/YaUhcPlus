package io.github.apjifengc.uhcplus.listener;

import io.github.apjifengc.uhcplus.Config;
import io.github.apjifengc.uhcplus.Config2;
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
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(SwordListener.this::detectSword);
            }
        }.runTaskTimer(plugin, 1L, 0L);
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player damager = (Player) event.getDamager();
            if (taskMap.containsKey(damager)) return;
            if (ItemUtil.isSimilar(Config2.SWORD, damager.getInventory().getItemInMainHand())) {
                event.setDamage(event.getDamage() + Config2.addDamage);
                damager.getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
                taskMap.put(damager, new BukkitRunnable() {
                    @Override
                    public void run() {
                        taskMap.remove(damager);
                    }
                }.runTaskLater(plugin, (long) (Config2.cd * 20L)));
            }
        }
    }

    private void detectSword(Player player) {
        if (ItemUtil.isSimilar(player.getInventory().getItemInMainHand(), Config.SWORD)) {
            player.addPotionEffects(Config.holdEffects);
        }
        else {
            Config.holdEffects.forEach(e -> player.removePotionEffect(e.getType()));
        }
    }
}
