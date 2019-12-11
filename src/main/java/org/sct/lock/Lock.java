package org.sct.lock;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.sct.lock.data.LockData;
import org.sct.lock.command.CommandHandler;
import org.sct.lock.file.Lang;
import org.sct.lock.util.EcoUtil;
import org.sct.lock.util.ListenerManager;
import org.sct.lock.util.Update;


/**
 * @author alchemy
 * @since 2019/12/2 17:15:17
 */
public final class Lock extends JavaPlugin {

    @Getter private static LockData lockData;
    private static Lock instance;

    @Override
    public void onEnable() {
        instance = this;
        lockData = new LockData();
        ListenerManager.register();
        Lang.loadLang();
        EcoUtil.loadVault();
        LockData.getPool().submit(Update::checkupdate);
        saveDefaultConfig();
        Bukkit.getPluginCommand("lock").setExecutor(new CommandHandler());
        getServer().getConsoleSender().sendMessage("      ___       ___           ___           ___     ");
        getServer().getConsoleSender().sendMessage("     /\\__\\     /\\  \\         /\\  \\         /\\__\\");
        getServer().getConsoleSender().sendMessage("    /:/  /    /::\\  \\       /::\\  \\       /:/  /");
        getServer().getConsoleSender().sendMessage("   /:/  /    /:/\\:\\  \\     /:/\\:\\  \\     /:/__/");
        getServer().getConsoleSender().sendMessage("  /:/  /    /:/  \\:\\  \\   /:/  \\:\\  \\   /::\\__\\____");
        getServer().getConsoleSender().sendMessage(" /:/__/    /:/__/ \\:\\__\\ /:/__/ \\:\\__\\ /:/\\:::::\\__\\");
        getServer().getConsoleSender().sendMessage(" \\:\\  \\    \\:\\  \\ /:/  / \\:\\  \\  \\/__/ \\/_|:|~~|~");
        getServer().getConsoleSender().sendMessage("  \\:\\  \\    \\:\\  /:/  /   \\:\\  \\          |:|  |");
        getServer().getConsoleSender().sendMessage("    \\:\\__\\    \\::/  /       \\:\\__\\        |:|  |");
        getServer().getConsoleSender().sendMessage("     \\/__/     \\/__/         \\/__/         \\|__|");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§7[§eLock§7]§c插件已被卸载");
    }

    public static Lock getInstance() {
        return instance;
    }
}
