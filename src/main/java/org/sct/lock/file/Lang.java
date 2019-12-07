package org.sct.lock.file;

import org.bukkit.configuration.file.YamlConfiguration;
import org.sct.lock.Lock;
import org.sct.lock.enumeration.LangType;
import org.sct.lock.util.BasicUtil;
import java.io.File;
import java.util.List;

public class Lang {

    private static File file;
    private static YamlConfiguration config;

    public static void loadLang() {
        file = new File(Lock.getInstance().getDataFolder() + "\\lang.yml");
        if (!file.exists()) { Lock.getInstance().saveResource("lang.yml",false); }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static String getString(LangType langType) {
        return BasicUtil.convert(config.getString(langType.getPath()));
    }

    public static List<String> getStringList(LangType langType) {
        return BasicUtil.convert(config.getStringList(langType.getPath()));
    }

}
