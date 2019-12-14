package org.sct.lock.file;

import org.bukkit.configuration.file.FileConfiguration;
import org.sct.lock.Lock;
import org.sct.lock.enumeration.ConfigType;

import java.util.List;

public class Config {

    private static FileConfiguration config = Lock.getInstance().getConfig();

    public static String getString(ConfigType configType) {
        return config.getString(configType.getPath());
    }

    public static int getInteger(ConfigType configType) {
        return config.getInt(configType.getPath());
    }

    public static boolean getBoolean(ConfigType configType) {
        return config.getBoolean(configType.getPath());
    }

    public static List<String> getStringList(ConfigType configType) {
        return config.getStringList(configType.getPath());
    }

}
