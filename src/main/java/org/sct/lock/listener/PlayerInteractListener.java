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
import org.sct.lock.util.LockUtil;

import java.util.HashMap;
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
        Material mr[] =  {};
        if (e.hasItem()) {
            for (Material material : mr) {
                if (e.getItem().getType() == material) {
                    Bukkit.getScheduler().runTaskLater(getInstance(),()->{
                        LockUtil.getLocation(e);
                    },1L);
                }
            }
        }
    }


}
