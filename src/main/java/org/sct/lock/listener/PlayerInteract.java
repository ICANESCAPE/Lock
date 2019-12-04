package org.sct.lock.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import java.util.HashMap;

import static org.sct.lock.Lock.*;

/**
  @author icestar
  @since 2019/12/4 23:01
 */
public class PlayerInteract implements Listener {
    private HashMap<Location, Player> lt_py = cache.getlt_py();
    private HashMap<Player,Location> py_lt = cache.getpy_lt();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Material mr[] =  {};
        if (e.hasItem()) {
            for (Material material : mr) {
                if (e.getItem().getType() == material) {
                    Bukkit.getScheduler().runTaskLater(getInstance(),()->{
                        getLocation(e);
                    },1L);
                }
            }
        }
    }

    public void getLocation(PlayerInteractEvent e) {
        Location lt = null;
        World world = e.getPlayer().getWorld();
        if (e.getClickedBlock() != null) {
            double X = e.getClickedBlock().getX();
            double Y = e.getClickedBlock().getY();
            double Z = e.getClickedBlock().getZ();
            double LowY = e.getClickedBlock().getY()-1;
            //e.getPlayer().sendMessage("X: " + X + " LowY: " + LowY + " Z: " + Z);
            //e.getPlayer().sendMessage(String.valueOf(new Location(e.getPlayer().getWorld(),X,LowY,Z).getBlock().getType()));
            if (e.getBlockFace() == BlockFace.NORTH) {
                lt = new Location(world,X,Y,Z-1);
            }
            if (e.getBlockFace() == BlockFace.SOUTH) {
                lt = new Location(world,X,Y,Z+1);
            }
            if (e.getBlockFace() == BlockFace.WEST) {
                lt = new Location(world,X-1,Y,Z);
            }
            if (e.getBlockFace() == BlockFace.EAST) {
                lt = new Location(world,X+1,Y,Z);
            }
            lt_py.putIfAbsent(lt,e.getPlayer());
            py_lt.putIfAbsent(e.getPlayer(),new Location(e.getPlayer().getWorld(),X,LowY,Z));
        }
    }
}
