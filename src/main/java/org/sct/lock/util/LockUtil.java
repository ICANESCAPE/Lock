package org.sct.lock.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.event.player.PlayerInteractEvent;

import org.sct.lock.data.LockData;

public class LockUtil {

    public static void getLocation(PlayerInteractEvent e) {
        Location lt = null;
        World world = e.getPlayer().getWorld();
        if (e.getClickedBlock() != null) {
            double X = e.getClickedBlock().getX();
            double Y = e.getClickedBlock().getY();
            double Z = e.getClickedBlock().getZ();
            double LowY = e.getClickedBlock().getY()-1;
            //e.getPlayer().sendMessage("X: " + X + " LowY: " + LowY + " Z: " + Z); //输出门所在坐标
            //e.getPlayer().sendMessage(String.valueOf(new Location(e.getPlayer().getWorld(),X,LowY,Z).getBlock().getType()));//输出门所在坐标的门类型
            //lt为牌子坐标
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
            LockData.getLocationPlayer().put(lt, e.getPlayer());
            LockData.getPlayerLocation().put(e.getPlayer(), new Location(e.getPlayer().getWorld(),X,LowY,Z));
        }
    }

}
