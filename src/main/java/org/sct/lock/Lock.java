package org.sct.lock;

import org.bukkit.plugin.java.JavaPlugin;
import org.sct.lock.util.ListenerManager;


/**
 * @author alchemy
 * @since 2019/12/2 17:15:17
 */
public final class Lock extends JavaPlugin {

    private static Lock instance;

    @Override
    public void onEnable() {
        instance = this;
        ListenerManager.register();

    }

    @Override
    public void onDisable() {

    }

    public static Lock getInstance() {
        return instance;
    }
}
