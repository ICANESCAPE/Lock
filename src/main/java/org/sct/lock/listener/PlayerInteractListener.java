package org.sct.lock.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.sct.lock.cache.Cache;
import org.sct.lock.enumeration.ConfigType;
import org.sct.lock.file.Config;
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

    private Map<Location, Player> lt_py = Cache.getLt_py();
    private Map<Player,Location> py_lt = Cache.getPy_lt();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        List<String> doorList = Config.getStringList(ConfigType.SETTING_SIGNTYPE);
        if (e.hasItem()) {//如果玩家手持物品
            for (String materialString : doorList) {
                Material material = Material.getMaterial(materialString);
                if (e.getItem().getType() == material) {
                    //System.out.println("牌子类型: " + material);//输出符合的牌子类型
                    Bukkit.getScheduler().runTaskLater(getInstance(),()->{
                        LockUtil.getLocation(e);
                    },1L);
                }
            }
        }
    }


}
