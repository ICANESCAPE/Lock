package org.sct.lock.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.sct.lock.cache.Cache;
import org.sct.lock.enumeration.ConfigType;
import org.sct.lock.file.Config;
import org.sct.lock.util.CheckUtil;
import org.sct.lock.util.LockUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.sct.lock.Lock.*;

/**
  @author icestar
  @since 2019/12/4 23:01
 */
public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        List<String> signList = Config.getStringList(ConfigType.SETTING_SIGNTYPE);
        List<String> doorList = Config.getStringList(ConfigType.SETTING_DOORTYPE);
        //如果玩家右键方块
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            //如果玩家手持物品
            if (e.hasItem()) {
                for (String sign : signList) {
                    Material material = Material.getMaterial(sign);
                    if (e.getItem().getType() == material) {
                        //System.out.println("牌子类型: " + material);//输出符合的牌子类型
                        Bukkit.getScheduler().runTaskLater(getInstance(),()->{
                            LockUtil.getLocation(e);
                        },1L);
                    }
                }
            }

            for (String door : doorList) {
                //如果右键的门符合类型
                if (Cache.getPlayerisSneak().get(e.getPlayer()) == null || !Cache.getPlayerisSneak().get(e.getPlayer())) {
                    return;
                }
                if (e.getClickedBlock().getType() == Material.getMaterial(door)) {
                    Cache.getDoorLocation().put(e.getPlayer(),e.getClickedBlock().getLocation());
                    CheckUtil.CheckSign(e.getPlayer(),e.getClickedBlock());
                }
            }



        }



    }


}
