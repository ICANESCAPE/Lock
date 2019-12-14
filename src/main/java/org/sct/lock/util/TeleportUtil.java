package org.sct.lock.util;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.sct.lock.data.LockData;
import org.sct.lock.enumeration.LangType;
import org.sct.lock.file.Lang;

/**
 * @author icestar
 */
public class TeleportUtil {
    public static void EnterTp(Player player) {
        Block sign = LockData.getPlayerSign().get(player);
        Sign Sign = (Sign) sign.getState();
        int charge = BasicUtil.ExtraceInt(Sign.getLine(1).trim());
        Block block = LockData.getPlayerBlock().get(player);
        String blockFace = sign.getBlockData().getAsString().split(",")[0].split("=")[1];
        //判断是出门还是进门
        double PlayerX = player.getLocation().getBlockX();
        double PlayerY = player.getLocation().getBlockY();
        double PlayerZ = player.getLocation().getBlockZ();
        double BlockX = block.getLocation().getBlockX();
        double BlockZ = block.getLocation().getBlockZ();
        //储存进出的状态
        String status = judgeStatus(blockFace,PlayerX,PlayerZ,BlockX,BlockZ);

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
            if (EcoUtil.has(player, charge)) {
                player.teleport(new Location(player.getWorld(),BlockX,PlayerY,BlockZ,player.getLocation().getYaw(),player.getLocation().getPitch()));
                player.sendMessage(BasicUtil.replace(Lang.getString(LangType.LANG_ENTER),"%charge", charge));
                EcoUtil.take(player,charge);
            } else {
                player.sendMessage(BasicUtil.convert(Lang.getString(LangType.LANG_NOTENOUGHMONEY)));
            }

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
            player.teleport(new Location(player.getWorld(),BlockX,PlayerY,BlockZ,player.getLocation().getYaw(),player.getLocation().getPitch()));
            player.sendMessage(Lang.getString(LangType.LANG_LEAVE));
        }

    }

    private static String judgeStatus(String blockFace,double PlayerX,double PlayerZ,double BlockX,double BlockZ) {
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
}
