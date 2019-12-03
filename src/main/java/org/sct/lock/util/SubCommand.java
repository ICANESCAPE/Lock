package org.sct.lock.util;

import org.bukkit.command.CommandSender;

public interface SubCommand {
    /**
     * 指令调用接口
     *
     * @param sender
     * @param args
     * @return
     */
    boolean execute(CommandSender sender, String[] args);

}
