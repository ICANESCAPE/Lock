package org.sct.lock.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.sct.lock.data.LockData;
import org.sct.lock.enumeration.ConfigType;
import org.sct.lock.file.Config;

public class CheckUtil {

    public static boolean CheckSign(Player player,Block block) {
        BlockFace playerFacing = player.getFacing();
        Location BlockLocation = block.getLocation();
        int X = BlockLocation.getBlockX();
        int Y = BlockLocation.getBlockY();
        int Z = BlockLocation.getBlockZ();
        boolean Found = false;
        Found = findSign(player,Found,X-1,Y+1,Z);
        Found = findSign(player,Found,X+1,Y+1,Z);
        Found = findSign(player,Found,X,Y+1,Z+1);
        Found = findSign(player,Found,X,Y+1,Z-1);
        Found = findSign(player,Found,X-1,Y+2,Z);
        Found = findSign(player,Found,X+1,Y+2,Z);
        Found = findSign(player,Found,X,Y+2,Z+1);
        Found = findSign(player,Found,X,Y+2,Z-1);
        if (Found) {
            //玩家交互的门上方的方块(忽视高度)
            LockData.getPlayerBlock().put(player,block);
        }
        return Found;
    }

    private static boolean findSign(Player player,boolean Found,int X,int Y,int Z) {
        if (Found) {
            return true;
        }
        for (String sign : BasicUtil.convertMaterial(Config.getStringList(ConfigType.SETTING_SIGNTYPE))) {
            Material signMaterial = Material.getMaterial(sign);
            if (new Location(player.getWorld(),X,Y,Z).getBlock().getType() == signMaterial) {

                //存入玩家交互的门上方的牌子
                LockData.getPlayerSign().put(player,new Location(player.getWorld(),X,Y,Z).getBlock());

                return true;
            }
        }
        return false;
    }
}
