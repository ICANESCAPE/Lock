package org.sct.lock.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.sct.lock.cache.Cache;

public class PlayerToggleSneakListener implements Listener {

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent e) {
        //如果玩家正在前行
        if (e.isSneaking()) {
            Cache.getPlayerisSneak().put(e.getPlayer(),true);
        } else {
            Cache.getPlayerisSneak().remove(e.getPlayer());
        }
    }
}
