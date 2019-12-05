package org.sct.lock;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.sct.lock.cache.Cache;
import org.sct.lock.command.CommandHandler;
import org.sct.lock.file.Lang;
import org.sct.lock.util.ListenerManager;


/**
 * @author alchemy
 * @since 2019/12/2 17:15:17
 */
public final class Lock extends JavaPlugin {
    public static Cache cache = new Cache();
    private static Lock instance;

    @Override
    public void onEnable() {
        instance = this;
        ListenerManager.register();
        Lang.loadLang();
        saveDefaultConfig();
        Bukkit.getPluginCommand("lock").setExecutor(new CommandHandler());
        getServer().getConsoleSender().sendMessage("§7[§eLock§7]§2插件已被加载");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§7[§eLock§7]§c插件已被卸载");
    }

    public static Lock getInstance() {
        return instance;
    }
}
