package org.sct.lock.command.sub;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.sct.lock.Lock;
import org.sct.lock.util.DownloadUtil;
import org.sct.lock.util.GetUrlMessage;
import org.sct.lock.util.SubCommand;

import java.io.IOException;

public class Update implements SubCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length == 2) {
            if (args.length == 2 && args[1].equalsIgnoreCase("download")) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            DownloadUtil.Download("https://github.com/ICANESCAPE/Lock/releases/download/" + Lock.getInstance().getDescription().getVersion() + "/Lock.jar","Lock.jar",Lock.getInstance().getDataFolder().getPath() + "\\update");
                            sender.sendMessage("§7[§eLock§7]§2下载成功");
                        } catch (IOException e) {
                            sender.sendMessage("§7[§eLock§7]§c下载更新时出错");
                        }
                    }
                }).start();
            } else if (args.length == 2 && args[1].equalsIgnoreCase("version")) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            GetUrlMessage.get(sender);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
        return true;
    }
}
