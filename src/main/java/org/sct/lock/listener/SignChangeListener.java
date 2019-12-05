package org.sct.lock.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.sct.lock.cache.Cache;
import org.sct.lock.enumeration.ConfigType;
import org.sct.lock.file.Config;
import org.sct.lock.util.BasicUtil;

import java.util.HashMap;


import static org.sct.lock.Lock.*;

/**
 * @author icestar
 * @since 2019/12/4 23:03
 */
public class SignChangeListener implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        HashMap<Player,Boolean> py_bool = new HashMap<>();
        py_bool.put(e.getPlayer(),true);
        Location lt = e.getBlock().getLocation();
        //e.getPlayer().sendMessage("改变的牌子的位置: " + lt);
        for (String doors : Config.getStringList(ConfigType.SETTING_DOORTYPE)) {
            if (Cache.getPy_lt().get(e.getPlayer()).getBlock().getType() == Material.getMaterial(doors)) py_bool.put(e.getPlayer(),false);
        }
        if (py_bool.get(e.getPlayer())) return;
        //e.getPlayer().sendMessage("门类型匹配");//门类型匹配，替换信息
        if (e.getPlayer() == Cache.getLt_py().get(lt)) {
            if (e.getLine(0).equalsIgnoreCase(Config.getString(ConfigType.SETTING_LOCKSYMBOL)) && e.getLine(1) != null) {
                //e.getPlayer().sendMessage("门Symbol信息匹配");//门Symbol信息匹配,价格存在，替换信息
                e.setLine(0, BasicUtil.convert(Config.getString(ConfigType.SETTING_SYMBOLREPLACE)));//替换第一行Symbol
                e.setLine(3,"§l" + e.getPlayer().getName());//替换第四行为玩家名
                if (Integer.getInteger(e.getLine(1)) != null) {
                    int money = Integer.getInteger(e.getLine(2));
                }
                e.setLine(1,BasicUtil.convert(Config.getString(ConfigType.SETTING_CHARGE) + e.getLine(1)));//替换第二行价格
                if (e.getLine(2).equalsIgnoreCase(Config.getString(ConfigType.SETTING_FLAGENTER))) {
                    e.setLine(2,BasicUtil.convert(Config.getString(ConfigType.SETTING_ENTERREPLACE)));//替换第三行进
                } else if (e.getLine(2).equalsIgnoreCase(Config.getString(ConfigType.SETTING_FLAGENTER))) {
                    e.setLine(2,BasicUtil.convert(Config.getString(ConfigType.SETTING_LEAVEREPLACE)));//替换第三行出
                } else e.setLine(2,BasicUtil.convert(Config.getString(ConfigType.SETTING_ENTERREPLACE) + Config.getString(ConfigType.SETTING_LEAVEREPLACE)));//替换第三行进出

            }
            Cache.getLt_py().remove(lt);
            Cache.getPy_lt().remove(e.getPlayer());
            py_bool.remove(e.getPlayer());
        }
    }
}
