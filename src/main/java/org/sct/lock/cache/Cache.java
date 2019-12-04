package org.sct.lock.cache;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

public class Cache {

    @Getter private static Map<Location, Player> lt_py;
    @Getter private static Map<Player, Location> py_lt;

    static {
        lt_py = new HashMap<>();
        py_lt = new HashMap<>();
    }


}
