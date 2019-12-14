package org.sct.lock.data;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author icestar
 */
public class LockData {

    static {
        LocationPlayer = new HashMap<>();
        PlayerLocation = new HashMap<>();
        PlayerisSneak = new HashMap<>();
        PlayerSign = new HashMap<>();
        PlayerBlock = new HashMap<>();
        inhibition = new HashMap<>();
        pool = Executors.newFixedThreadPool(5);
        scheduledpool = Executors.newScheduledThreadPool(1);
    }

    @Getter private static Map<Location, Player> LocationPlayer;

    @Getter private static Map<Player, Location> PlayerLocation;

    /*玩家潜行的状态*/
    @Getter private static Map<Player, Boolean> PlayerisSneak;

    /*玩家交互的门上方的牌子*/
    @Getter private static Map<Player, Block> PlayerSign;

    /*玩家交互的门上方的方块(忽视高度)*/
    @Getter private static Map<Player, Block> PlayerBlock;

    /*插件专用线程池*/
    @Getter private static ExecutorService pool;

    /*交互事件抑制器*/
    @Getter private static Map<Player, Boolean> inhibition;

    /*插件专用计划线程池*/
    @Getter private static ScheduledExecutorService scheduledpool;
}
