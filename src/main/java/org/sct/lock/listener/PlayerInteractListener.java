package org.sct.lock.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import org.sct.lock.data.LockData;
import org.sct.lock.enumeration.ConfigType;
import org.sct.lock.event.PlayerAccessLockDoorEvent;
import org.sct.lock.file.Config;
import org.sct.lock.util.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.sct.lock.Lock.*;

/**
  @author icestar
  @since 2019/12/4 23:01
 */
public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        List<String> signList = Config.getStringList(ConfigType.SETTING_SIGNTYPE);
        List<String> doorList = Config.getStringList(ConfigType.SETTING_DOORTYPE);

        //如果玩家右键方块
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            /*事件抑制*/
            if (LockData.getInhibition().get(e.getPlayer()) != null) {
                return;
            } else {
                LockData.getInhibition().put(e.getPlayer(),true);
                LockData.getScheduledpool().schedule(() -> {
                    LockData.getInhibition().remove(e.getPlayer());
                }, Config.getInteger(ConfigType.SETTING_ENTERDELAY), TimeUnit.MILLISECONDS);
            }

            //如果玩家手持物品
            if (e.hasItem()) {
                for (String sign : signList) {
                    Material material = Material.getMaterial(sign);
                    if (e.getItem().getType() == material) {
                        //System.out.println("牌子类型: " + material);//输出符合的牌子类型
                        Bukkit.getScheduler().runTaskLater(getInstance(),()->{
                            LockUtil.getLocation(e);
                        },1L);
                    }
                }
            }

            for (String door : doorList) {
                //如果玩家正在潜行
                if (LockData.getPlayerisSneak().get(e.getPlayer()) == null || !LockData.getPlayerisSneak().get(e.getPlayer())) {
                    return;
                }
                //如果右键的门符合类型
                if (e.getClickedBlock().getType() == Material.getMaterial(door)) {
                    //如果门的上方有自动收费门的牌子,在CheckUtil内存入牌子和方块的位置
                    if (CheckUtil.CheckSign(e.getPlayer(), e.getClickedBlock())) {
                        /**
                         * 判断如果为进入，则触发自定义事件
                         */
                        if (TeleportUtil.isEntering(e.getPlayer())) {
                            Bukkit.getPluginManager().callEvent(new PlayerAccessLockDoorEvent(e.getPlayer(), LockUtil.getOwner(LockData.getPlayerSign().get(e.getPlayer())), LockData.getPlayerSign().get(e.getPlayer())));
                        }
                    }
                }

            }

        }

    }

}
