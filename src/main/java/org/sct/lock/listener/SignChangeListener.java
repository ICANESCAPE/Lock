package org.sct.lock.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import org.sct.lock.data.LockData;
import org.sct.lock.enumeration.ConfigType;
import org.sct.lock.file.Config;
import org.sct.lock.util.BasicUtil;


/**
 * @author icestar
 * @since 2019/12/4 23:03
 */
public class SignChangeListener implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        Location lt = e.getBlock().getLocation();
        boolean cancel = true;
        //e.getPlayer().sendMessage("改变的牌子的位置: " + lt);
        for (String doors : Config.getStringList(ConfigType.SETTING_DOORTYPE)) {
            if (LockData.getPlayerLocation().get(e.getPlayer()).getBlock().getType() == Material.getMaterial(doors)) {
                cancel = false;
            }
        }

        if (cancel) {
            return;
        }

        //e.getPlayer().sendMessage("门类型匹配");//门类型匹配，替换信息
        if (e.getPlayer() == LockData.getLocationPlayer().get(lt)) {
            if (e.getLine(0).equalsIgnoreCase(Config.getString(ConfigType.SETTING_LOCKSYMBOL)) && !e.getLine(1).equalsIgnoreCase("")) {
                //e.getPlayer().sendMessage("门Symbol信息匹配");//门Symbol信息匹配,价格存在，替换信息
                //替换第一行Symbol
                e.setLine(0, BasicUtil.convert(Config.getString(ConfigType.SETTING_SYMBOLREPLACE)));
                //替换第四行为玩家名
                e.setLine(3,"§l" + e.getPlayer().getName());
                if (Integer.getInteger(e.getLine(1)) != null) {
                    int money = Integer.getInteger(e.getLine(2));
                }
                //替换第二行价格
                e.setLine(1,BasicUtil.convert(Config.getString(ConfigType.SETTING_CHARGE) + e.getLine(1)));
                if (e.getLine(2).equalsIgnoreCase(Config.getString(ConfigType.SETTING_FLAGENTER))) {
                    //替换第三行进
                    e.setLine(2,BasicUtil.convert(Config.getString(ConfigType.SETTING_ENTERREPLACE)));
                } else if (e.getLine(2).equalsIgnoreCase(Config.getString(ConfigType.SETTING_FLAGLEAVE))) {
                    //替换第三行出
                    e.setLine(2,BasicUtil.convert(Config.getString(ConfigType.SETTING_LEAVEREPLACE)));
                } else {
                    //替换第三行进出
                    e.setLine(2,BasicUtil.convert(Config.getString(ConfigType.SETTING_ENTERREPLACE) + Config.getString(ConfigType.SETTING_LEAVEREPLACE)));
                }
            }
            LockData.getLocationPlayer().remove(lt);
            LockData.getPlayerLocation().remove(e.getPlayer());
        }
    }
}
