package org.sct.lock.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.sct.lock.enumeration.ConfigType;
import org.sct.lock.file.Config;
import org.sct.lock.util.CheckUtil;

public class BlockRedstone implements Listener {
    @EventHandler
    public void onBlockRedstone(BlockRedstoneEvent e) {

        if (!Config.getBoolean(ConfigType.SETTING_BANREDSTONEACTIVE)) {
            return;
        }

        if (CheckUtil.CheckSign(e.getBlock().getWorld(), null, e.getBlock())) {
            e.setNewCurrent(0);
        }
    }
}
