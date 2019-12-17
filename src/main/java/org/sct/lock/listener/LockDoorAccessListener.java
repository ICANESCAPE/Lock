package org.sct.lock.listener;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.sct.lock.enumeration.ConfigType;
import org.sct.lock.enumeration.LangType;
import org.sct.lock.event.PlayerAccessLockDoorEvent;
import org.sct.lock.file.Config;
import org.sct.lock.file.Lang;
import org.sct.lock.util.BasicUtil;
import org.sct.lock.util.EcoUtil;
import org.sct.lock.util.LockUtil;
import org.sct.lock.util.TeleportUtil;

/**
 * @author alchemy
 * @date 2019-12-09 19:24
 */

public class LockDoorAccessListener implements Listener {

    @EventHandler
    public void onAccess(PlayerAccessLockDoorEvent e) {

        Sign sign = (Sign) e.getBlock().getState();
        int charge = BasicUtil.ExtraceInt(sign.getLine(1).trim());
        if (!EcoUtil.has(e.getPayer(), charge)) {
            e.getPayer().sendMessage(Lang.getString(LangType.LANG_NOTENOUGHMONEY));
            return;
        }

        if (Config.getString(ConfigType.SETTING_VIPALLOWED) != null) {
            if (e.getPayer().hasPermission(Config.getString(ConfigType.SETTING_VIPALLOWED))) {
                EcoUtil.take(e.getPayer(), charge);
                e.getPayer().sendMessage(BasicUtil.replace(Lang.getString(LangType.LANG_ENTER),"%charge", charge));
            } else {
                EcoUtil.take(e.getPayer(), charge * (1 + Config.getInteger(ConfigType.SETTING_TAXPERCENT)));
                e.getPayer().sendMessage(BasicUtil.replace(Lang.getString(LangType.LANG_ENTER),"%charge", charge));
            }
            if (LockUtil.getOwner(e.getBlock()).hasPermission(Config.getString(ConfigType.SETTING_VIPALLOWED))) {
                EcoUtil.give(LockUtil.getOwner(e.getBlock()), charge);
            } else {
                EcoUtil.take(e.getPayer(), charge * Config.getInteger(ConfigType.SETTING_TAXPERCENT));
            }
        }
        TeleportUtil.enterTp(e.getPayer());

    }

}
