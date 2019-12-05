package org.sct.lock.cache;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

/**
 * @author icestar
 */
public class Cache {

    @Getter private static Map<Location, Player> LocationPlayer;
    @Getter private static Map<Player, Location> PlayerLocation;
    @Getter private static Map<Player, Location> DoorLocation;
    @Getter private static Map<Player, Boolean> PlayerisSneak;

    static {
        LocationPlayer = new HashMap<>();
        PlayerLocation = new HashMap<>();
        DoorLocation = new HashMap<>();
        PlayerisSneak = new HashMap<>();
    }


}
