package org.sct.lock.listener;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.inventory.ItemStack;
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

        /*设置状态数据*/
        TeleportUtil.getData(e.getPayer());

        /*如果执行传送并返回进出状态，以此来进行扣费操作*/
        String status = TeleportUtil.getFace(e.getPayer());

        /*收费门指定允许的方向*/
        String direction = LockUtil.getDirection(e.getBlock());

        if (!direction.equals("double")) {
            if (!status.equals(direction)) {
                e.getPayer().sendMessage(BasicUtil.convert(Lang.getString(LangType.LANG_DENYDIRECTION)));
                return;
            }
        }

        String restriction = LockUtil.getRestriction(e.getBlock());

        System.out.println(restriction);
        if (!restriction.isEmpty()) {
            if (restriction.contains("1")) {


            }

            if (restriction.contains("2")) {

            }

            if (restriction.contains("3")) {
                if (!e.getPayer().getActivePotionEffects().isEmpty()) {
                    System.out.println("含有药水效果");
                    return;
                }
            }
        }

        if ("enter".equalsIgnoreCase(status)) {
            /*如果余额不足*/
            if (!EcoUtil.has(e.getPayer(), charge)) {
                e.getPayer().sendMessage(Lang.getString(LangType.LANG_NOTENOUGHMONEY));
                return;
            }

            TeleportUtil.Tp(status, e.getPayer());
            /*payer付钱部分*/

            /*如果owner是vip或权限未设置*/
            if (!"".equalsIgnoreCase(Config.getString(ConfigType.SETTING_VIPALLOWED)) || ((Player) LockUtil.getOwner(e.getBlock())).hasPermission(Config.getString(ConfigType.SETTING_VIPALLOWED))) {
                EcoUtil.take(e.getPayer(), charge);
                EcoUtil.give(LockUtil.getOwner(e.getBlock()), charge);
            } else {
                /*owner不是vip,扣税*/
                EcoUtil.take(e.getPayer(), charge);
                charge = (1 - Config.getInteger(ConfigType.SETTING_TAXPERCENT)) * charge;
                EcoUtil.give(LockUtil.getOwner(e.getBlock()), charge);
            }

            e.getPayer().sendMessage(BasicUtil.replace(Lang.getString(LangType.LANG_ENTER),"%charge", charge));
        } else {
            TeleportUtil.Tp("leave", e.getPayer());
        }

    }

}
