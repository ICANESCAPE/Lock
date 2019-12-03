package org.sct.lock;

import org.bukkit.plugin.java.JavaPlugin;

public final class Lock extends JavaPlugin {

    private static Lock lock;

    @Override
    public void onEnable() {
        lock = this;

    }

    @Override
    public void onDisable() {

    }

    public static Lock getLock() {
        return lock;
    }
}
