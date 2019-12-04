package org.sct.lock.cache;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.HashMap;

public class Cache {
    private HashMap<Location, Player> lt_py = new HashMap<>();
    private HashMap<Player, Location> py_lt = new HashMap<>();


    public HashMap getlt_py() {
        return lt_py;
    }

    public HashMap getpy_lt() {
        return py_lt;
    }
}
