package org.sct.lock.util;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.sct.lock.Lock;
import org.sct.lock.listener.PlayerInteract;
import org.sct.lock.listener.SignChange;

public class ListenerManager {

    private static void register(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, Lock.getInstance());
    }

    /**
     * 注册监听器
     */
    public static void register() {
        Listener[] listeners = {new PlayerInteract(),new SignChange()};
        for (Listener listener : listeners) {
            register(listener);
        }
    }

}
