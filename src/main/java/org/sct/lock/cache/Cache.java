package org.sct.lock.cache;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

/**
 * @author icestar
 */
public class Cache {

    @Getter private static Map<Location, Player> LocationPlayer;
    @Getter private static Map<Player, Location> PlayerLocation;
    //玩家潜行的状态
    @Getter private static Map<Player, Boolean> PlayerisSneak;
    //玩家交互的门上方的牌子
    @Getter private static Map<Player, Block> PlayerSign;
    //玩家交互的门上方的方块(忽视高度)
    @Getter private static Map<Player, Block> PlayerBlock;
    static {
        LocationPlayer = new HashMap<>();
        PlayerLocation = new HashMap<>();
        PlayerisSneak = new HashMap<>();
        PlayerSign = new HashMap<>();
        PlayerBlock = new HashMap<>();
    }


}
