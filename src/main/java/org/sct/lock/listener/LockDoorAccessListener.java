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

        /*如果执行传送并返回进出状态，以此来进行扣费操作*/
        String status = TeleportUtil.Tp(e.getPayer());

        if ("enter".equalsIgnoreCase(status)) {
            /*如果余额不足*/
            if (!EcoUtil.has(e.getPayer(), charge)) {
                e.getPayer().sendMessage(Lang.getString(LangType.LANG_NOTENOUGHMONEY));
                return;
            }

            /*payer付钱部分*/
            EcoUtil.take(e.getPayer(), charge);
            e.getPayer().sendMessage(BasicUtil.replace(Lang.getString(LangType.LANG_ENTER),"%charge", charge));

            /*如果owner是vip或权限未设置*/
            if (!"".equalsIgnoreCase(Config.getString(ConfigType.SETTING_VIPALLOWED)) || LockUtil.getOwner(e.getBlock()).hasPermission(Config.getString(ConfigType.SETTING_VIPALLOWED))) {
                EcoUtil.give(LockUtil.getOwner(e.getBlock()), charge);
            } else {
                /*owner不是vip,扣税*/
                EcoUtil.give(LockUtil.getOwner(e.getBlock()), (1 - Config.getInteger(ConfigType.SETTING_TAXPERCENT)) * charge);
            }

        }

    }

}
