package org.sct.lock.util.player;

import org.apache.commons.lang.ArrayUtils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * @author alchemy
 * @date 2020-02-07 12:31
 */
public class InventoryUtil {

    /* 是否需要护甲为空你自己判断 */
    private static boolean isAmorEmpty = false;

    /**
     * 判断指定玩家背包是否为空
     *
     * @param player 指定玩家
     * @return true为空，false为不空
     */
    public static boolean isInvEmpty(Player player) {
        PlayerInventory inventory = player.getInventory();
        ItemStack[] checkList = inventory.getContents();
        if (isAmorEmpty) {
            checkList = (ItemStack[]) ArrayUtils.addAll(checkList, player.getInventory().getArmorContents());
        }
        for (ItemStack item : checkList) {
            if (item != null || item.hasItemMeta() || item.getItemMeta().equals(Material.AIR)) {
                return false;
            }
        }
        return true;
    }
}
