package org.sct.lock.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import org.sct.lock.data.LockData;
import org.sct.lock.enumeration.ConfigType;
import org.sct.lock.file.Config;

public class CheckUtil {

    private static int x,y,z;

    /**
     * @param world 门所处世界
     * @param player 交互玩家(如果单纯检测而不存进map则保持为null)
     * @param block 门
     * @return 是否为收费门牌子
     */
    public static boolean CheckSign(World world, Player player, Block block) {
        Location BlockLocation = block.getLocation();
        int X = BlockLocation.getBlockX();
        int Y = BlockLocation.getBlockY();
        int Z = BlockLocation.getBlockZ();
        boolean Found = false;
        Found = findSign(world,Found,X-1,Y+1,Z);
        Found = findSign(world,Found,X+1,Y+1,Z);
        Found = findSign(world,Found,X,Y+1,Z+1);
        Found = findSign(world,Found,X,Y+1,Z-1);
        Found = findSign(world,Found,X-1,Y+2,Z);
        Found = findSign(world,Found,X+1,Y+2,Z);
        Found = findSign(world,Found,X,Y+2,Z+1);
        Found = findSign(world,Found,X,Y+2,Z-1);
        if (Found) {
            if (player != null) {
                storeData(player, block);
            }
        }
        return Found;
    }

    private static boolean findSign(World world, boolean Found, int X, int Y, int Z) {
        if (Found) {
            return true;
        }
        for (String sign : BasicUtil.convertMaterial(Config.getStringList(ConfigType.SETTING_SIGNTYPE))) {
            Material signMaterial = Material.getMaterial(sign);
            if (new Location(world,X,Y,Z).getBlock().getType() == signMaterial) {
                Sign Sign = (Sign) new Location(world,X,Y,Z).getBlock().getState();
                if (!Sign.getLine(0).equalsIgnoreCase(BasicUtil.convert(Config.getString(ConfigType.SETTING_SYMBOLREPLACE)))) {
                    continue;
                }
                x = X;
                y = Y;
                z = Z;

                return true;
            }
        }
        return false;
    }

    private static void storeData(Player player, Block block) {

        //玩家交互的门上方的方块(忽视高度)
        if (player != null) {
            LockData.getPlayerBlock().put(player,block);
        }

        //存入玩家交互的门上方的牌子
        if (player != null) {
            LockData.getPlayerSign().put(player,new Location(player.getWorld(),x,y,z).getBlock());
        }
    }
}
