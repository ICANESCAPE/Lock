package org.sct.lock.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BanCommand implements Listener {
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().contains("sethome")) {
            e.getPlayer().sendMessage("");
            e.setCancelled(true);
        }
    }
}
