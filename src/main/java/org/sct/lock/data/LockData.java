package org.sct.lock.data;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        pool = Executors.newFixedThreadPool(5);
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


}
