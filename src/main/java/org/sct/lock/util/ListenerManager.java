package org.sct.lock.util;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import org.sct.lock.Lock;
import org.sct.lock.listener.PlayerInteractListener;
import org.sct.lock.listener.PlayerToggleSneakListener;
import org.sct.lock.listener.SignChangeListener;

public class ListenerManager {

    private static void register(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, Lock.getInstance());
    }

    /**
     * 注册监听器
     */
    public static void register() {
        Listener[] listeners = {new PlayerInteractListener(), new SignChangeListener(), new PlayerToggleSneakListener()};
        for (Listener listener : listeners) {
            register(listener);
        }
    }

}
