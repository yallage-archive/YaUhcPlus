package io.github.apjifengc.uhcplus.listener;

import io.github.apjifengc.uhcplus.Config;
import io.github.apjifengc.uhcplus.UhcPlus;
import io.github.apjifengc.uhcplus.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
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
            Player damager = (Player) event.getDamager();
            Player damagee = (Player) event.getEntity();
            if (taskMap.containsKey(damager)) return;
            if (ItemUtil.isSimilar(Config.SWORD, damager.getInventory().getItemInMainHand())) {
                event.setDamage(event.getDamage() + Config.addDamage);
                damager.getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
                damagee.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1,0));
                taskMap.put(damager, new BukkitRunnable() {
                    @Override
                    public void run() {
                        taskMap.remove(damager);
                    }
                }.runTaskLater(plugin, (long) (Config.cd * 20L)));
            }
        }
    }
}
