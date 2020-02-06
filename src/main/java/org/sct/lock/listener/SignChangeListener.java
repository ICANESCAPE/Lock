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
import org.sct.lock.util.LocationUtil;


/**
 * @author icestar
 * @since 2019/12/4 23:03
 */
public class SignChangeListener implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        Location lt = e.getBlock().getLocation();
        boolean cancel = true;
        for (String doors : Config.getStringList(ConfigType.SETTING_DOORTYPE)) {
            if (LockData.getPlayerLocation().get(e.getPlayer()).getBlock().getType() == Material.getMaterial(doors)) {
                cancel = false;
            }
        }

        if (cancel) {
            return;
        }

        if (e.getPlayer() == LockData.getLocationPlayer().get(lt)) {
            if (e.getLine(0).equalsIgnoreCase(Config.getString(ConfigType.SETTING_LOCKSYMBOL)) && !e.getLine(1).equalsIgnoreCase("")) {

                /*如果世界不匹配,返回*/
                if (!LocationUtil.checkWorld(e.getBlock().getLocation())) {
                    return;
                }
                /*替换第一行Symbol*/
                e.setLine(0, BasicUtil.convert(Config.getString(ConfigType.SETTING_SYMBOLREPLACE)));

                /*替换第四行为玩家名*/
                e.setLine(3,"§l" + e.getPlayer().getName() + "\n测试第四行");
                if (Integer.getInteger(e.getLine(1)) != null) {
                    int money = Integer.getInteger(e.getLine(2));
                }

                /*替换第二行价格*/
                e.setLine(1,BasicUtil.convert(Config.getString(ConfigType.SETTING_CHARGE) + e.getLine(1)));

                StringBuffer buffer = new StringBuffer();
                String line = e.getLine(2);
                boolean direction = false;
                if (line.contains(Config.getString(ConfigType.SETTING_FLAGENTER))) {
                    buffer.append(BasicUtil.convert(Config.getString(ConfigType.SETTING_ENTERREPLACE)));
                    direction = true;
                }

                if (line.contains(Config.getString(ConfigType.SETTING_FLAGLEAVE))) {
                    buffer.append(BasicUtil.convert(Config.getString(ConfigType.SETTING_LEAVEREPLACE)));
                    direction = true;
                }

                /*开启默认双向*/
                if (!direction) {
                    buffer.append(BasicUtil.convert(Config.getString(ConfigType.SETTING_ENTERREPLACE) + Config.getString(ConfigType.SETTING_LEAVEREPLACE)));
                }

                /*-----------------------------------------------------*/
                if (line.contains(Config.getString(ConfigType.SETTING_FLAGEMPTY))) {
                   buffer.append(BasicUtil.convert(Config.getString(ConfigType.SETTING_EMPTYREPLACE)));
                }

                if (line.contains(Config.getString(ConfigType.SETTING_FLAGMONEY))) {
                    buffer.append(BasicUtil.convert(Config.getString(ConfigType.SETTING_MONEYREPLACE)));
                }

                if (line.contains(Config.getString(ConfigType.SETTING_FLAGEFFECT))) {
                    buffer.append(BasicUtil.convert(Config.getString(ConfigType.SETTING_EFFECTREPLACE)));
                }

                e.setLine(2, buffer.toString());
            }
            LockData.getLocationPlayer().remove(lt);
            LockData.getPlayerLocation().remove(e.getPlayer());
        }
    }
}
