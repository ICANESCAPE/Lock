package org.sct.lock.command.sub;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.sct.lock.Lock;
import org.sct.lock.util.DownloadUtil;
import org.sct.lock.util.SubCommand;

import java.io.IOException;

public class Update implements SubCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Runnable runnable = () ->{
            try {
                DownloadUtil.Download("https://github.com/ICANESCAPE/Lock/releases/download/" + Lock.getInstance().getDescription().getVersion() + "/Lock.jar","Lock.jar",Lock.getInstance().getDataFolder().getPath() + "\\update");
            } catch (IOException e) {
                e.printStackTrace();
                sender.sendMessage("§7[§eLock§7]§c下载更新时出错");
            }
        };
        new Thread(runnable).run();
        sender.sendMessage("§7[§eLock§7]§2下载成功");

        return true;
    }
}
