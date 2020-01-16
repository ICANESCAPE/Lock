package org.sct.lock.util;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import org.sct.lock.data.LockData;


/**
 * @author icestar
 */
public class TeleportUtil {
    private static Block sign,block;
    private static Sign Sign;
    private static int charge;
    private static double PlayerX,PlayerY,PlayerZ,BlockX,BlockZ;
    private static String blockFace;

    /**
     * @param player 被tp的玩家(payer)
     * @return 进出状态
     */
    public static String getFace(Player player) {

        /*判断进出状态*/
        String status = null;
        if (blockFace.equalsIgnoreCase("north")) {
            if (PlayerZ < BlockZ) {
                status = "enter";
            } else {
                status = "leave";
            }
        } else if (blockFace.equalsIgnoreCase("south")) {
            if (PlayerZ > BlockZ) {
                status = "enter";
            } else {
                status = "leave";
            }
        } else if (blockFace.equalsIgnoreCase("west")) {
            if (PlayerX < BlockX) {
                status = "enter";
            } else {
                status = "leave";
            }
        } else if (blockFace.equalsIgnoreCase("east")) {
            if (PlayerX > BlockX) {
                status = "enter";
            } else {
                status = "leave";
            }
        }


        return status;
    }

    public static void Tp(String status, Player player) {
        /*传送部分*/
        if (status.equalsIgnoreCase("enter")) {
            if (blockFace.equalsIgnoreCase("north")) {
                BlockZ += 1.5;
                BlockX += 0.5;
            }
            if (blockFace.equalsIgnoreCase("south")) {
                BlockZ -= 0.5;
                BlockX += 0.5;
            }
            if (blockFace.equalsIgnoreCase("west")) {
                BlockX += 1.5;
                BlockZ += 0.5;
            }
            if (blockFace.equalsIgnoreCase("east")) {
                BlockX -= 0.5;
                BlockZ += 0.5;
            }
            player.teleport(new Location(player.getWorld(), BlockX, PlayerY, BlockZ, player.getLocation().getYaw(), player.getLocation().getPitch()));

        } else if (status.equalsIgnoreCase("leave")) {
            if (blockFace.equalsIgnoreCase("north")) {
                BlockZ -= 0.5;
                BlockX += 0.5;
            }
            if (blockFace.equalsIgnoreCase("south")) {
                BlockZ += 1.5;
                BlockX += 0.5;
            }
            if (blockFace.equalsIgnoreCase("west")) {
                BlockX -= 0.5;
                BlockZ += 0.5;
            }
            if (blockFace.equalsIgnoreCase("east")) {
                BlockX += 1.5;
                BlockZ += 0.5;
            }
            player.teleport(new Location(player.getWorld(), BlockX, PlayerY, BlockZ, player.getLocation().getYaw(), player.getLocation().getPitch()));

        }
    }

    public static void getData(Player player) {
        sign = LockData.getPlayerSign().get(player);
        Sign = (Sign) sign.getState();
        charge = BasicUtil.ExtraceInt(Sign.getLine(1).trim());
        block = LockData.getPlayerBlock().get(player);

        /*通过blockdata获取牌子朝向*/
        blockFace = sign.getBlockData().getAsString().split(",")[0].split("=")[1];
        PlayerX = player.getLocation().getBlockX();
        PlayerY = player.getLocation().getBlockY();
        PlayerZ = player.getLocation().getBlockZ();
        BlockX = block.getLocation().getBlockX();
        BlockZ = block.getLocation().getBlockZ();
    }

}
