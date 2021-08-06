package io.github.apjifengc.uhcplus.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {
    public static boolean isSimilar(ItemStack stack1, ItemStack stack2) {
        if (stack1.getType() != stack2.getType()) return false;
        if (stack1.hasItemMeta() != stack2.hasItemMeta()) return false;
        if (stack1.hasItemMeta() && stack2.hasItemMeta()) {
            ItemMeta meta1 = stack1.getItemMeta(), meta2 = stack2.getItemMeta();
            return meta1.getLore().equals(meta2.getLore());
        }
        return true;
    }
}
