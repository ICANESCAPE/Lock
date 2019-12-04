package org.sct.lock.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import java.util.HashMap;

import static org.sct.lock.Lock.*;

/**
 * @author icestar
 * @since 2019/12/4 23:03
 */
public class SignChange implements Listener {
    private HashMap<Player, Location> lt_py = cache.getlt_py();
    private HashMap<Player,Location> py_lt = cache.getpy_lt();

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        HashMap<Player,Boolean> py_bool = new HashMap<>();
        py_bool.put(e.getPlayer(),true);
        Location lt = e.getBlock().getLocation();
        e.getPlayer().sendMessage(String.valueOf(py_lt.get(e.getPlayer()).getBlock().getType()));
        for (String doors : getInstance().getConfig().getStringList("doors")) {
            //e.getPlayer().sendMessage("允许的门类型: " + doors);
            //e.getPlayer().sendMessage("对应门的坐标: " + py_lt.get(e.getPlayer()));
            if (py_lt.get(e.getPlayer()).getBlock().getType() == Material.getMaterial(doors)) py_bool.put(e.getPlayer(),false);
        }
        if (py_bool.get(e.getPlayer())) return;
        if (e.getPlayer() == lt_py.get(lt)) {
            if (e.getLine(0).equalsIgnoreCase(getInstance().getConfig().getString("lock")) && e.getLine(1) != null) {
                e.setLine(0,"§c[自动收费门]");
                e.setLine(3,e.getPlayer().getName());
                if (Integer.getInteger(e.getLine(1)) != null) {
                    int money = Integer.getInteger(e.getLine(2));
                }
                e.setLine(1,"§b§l花费: §e§l" + e.getLine(1));
                if (e.getLine(2).equalsIgnoreCase(getInstance().getConfig().getString("flagEnter"))) {
                    e.setLine(2,"§a[进]");
                } else if (e.getLine(2).equalsIgnoreCase(getInstance().getConfig().getString("flagLeave"))) {
                    e.setLine(2,"§a[出]");
                } else e.setLine(2,"§a[进][出]");

            }
            lt_py.remove(lt);
            py_lt.remove(e.getPlayer());
            py_bool.remove(e.getPlayer());
        }
    }
}
