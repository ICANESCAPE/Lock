package org.sct.lock.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.sct.lock.data.LockData;
import org.sct.lock.enumeration.ConfigType;
import org.sct.lock.file.Config;

/**
 * @author icestar
 */
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

    /**
     * @param block 牌子
     * @return Player 牌子拥有者
     */
    public static Player getOwner(Block block) {

        for (String  materialString : BasicUtil.convertMaterial(Config.getStringList(ConfigType.SETTING_SIGNTYPE))) {
            Material material = Material.getMaterial(materialString);
            if (!block.getType().equals(material)) {
                return null;
            }
        }

        Sign sign = (Sign) block.getState();
        return Bukkit.getPlayer(sign.getLine(3).replace("§l",""));

    }


}
