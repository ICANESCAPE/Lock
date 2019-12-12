package org.sct.lock.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class BasicUtil {

    public static String convert(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> convert(List<String> message) {
        List<String> msgList = new ArrayList<>();
        for (String key : message) {
            msgList.add(convert(key));
        }
        return msgList;
    }

    public static List<String> convertMaterial(List<String> name) {
        List<String> materialList = new ArrayList<>();
        for (String materialName : name) {
            materialList.add(materialName.replace("_SIGN","_WALL_SIGN"));
        }
        return materialList;
    }

    public static int ExtraceInt(String string) {
        String s = string.trim();
        String collect = "";
        if (s != null && !"".equals(s)) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) >= 48 && s.charAt(i) <= 57) {
                    if (s.charAt(i-1) == 38) {
                        continue;
                    }
                    collect += s.charAt(i);
                }
            }
        }
        return Integer.parseInt(collect);
    }

}
