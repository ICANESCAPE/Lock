package org.sct.lock.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.sct.lock.data.LockData;
import org.sct.lock.enumeration.ConfigType;
import org.sct.lock.enumeration.LangType;
import org.sct.lock.file.Config;
import org.sct.lock.file.Lang;
import org.sct.lock.util.BasicUtil;
import org.sct.lock.util.CheckUtil;
import org.sct.lock.util.LockUtil;

import java.util.List;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        if (e.getPlayer().isOp()) {
            return;
        }

        Block block = e.getBlock();

        int  BlockX = block.getLocation().getBlockX();
        int  BlockY = block.getLocation().getBlockY();
        int  BlockZ = block.getLocation().getBlockZ();
        Block doorAbove = new Location(e.getPlayer().getWorld(), BlockX, BlockY - 1, BlockZ).getBlock();
        Block doorBelow = new Location(e.getPlayer().getWorld(), BlockX, BlockY + 1, BlockZ).getBlock();

        if (checkDoor(e, block) || checkDoor(e, doorAbove) || checkDoor(e, doorBelow) || checkSign(e, e.getBlock())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(Lang.getString(LangType.LANG_DENYBREAK));
        }
    }

    private boolean checkDoor(BlockBreakEvent e, Block doorBlock) {
        List<String> doorList = Config.getStringList(ConfigType.SETTING_DOORTYPE);

        /*判断是否符合门类型*/
        for (String door : doorList) {
            /*如果破坏的门符合类型*/
            if (doorBlock.getType() == Material.getMaterial(door)) {
                /*如果是自动收费门 */
                if (CheckUtil.CheckSign(doorBlock.getWorld(), e.getPlayer(), doorBlock)) {
                    OfflinePlayer owner = LockUtil.getOwner(LockData.getPlayerSign().get(e.getPlayer()));
                    if (!owner.getName().equals(e.getPlayer().getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkSign(BlockBreakEvent e, Block signBlock) {
        List<String> signList = Config.getStringList(ConfigType.SETTING_SIGNTYPE);

        /*判断是否符合牌子类型*/
        for (String sign : BasicUtil.convertMaterial(signList)) {
            /*如果破坏的门符合类型*/
            if (signBlock.getType() == Material.getMaterial(sign)) {
                /*如果是自动收费门 */
                if (CheckUtil.findSign(e.getPlayer().getWorld(), false, signBlock.getLocation().getBlockX(), signBlock.getLocation().getBlockY(), signBlock.getLocation().getBlockZ())) {
                    OfflinePlayer owner = LockUtil.getOwner(signBlock);
                    if (!owner.getName().equals(e.getPlayer().getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
