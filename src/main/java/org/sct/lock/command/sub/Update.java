package org.sct.lock.command.sub;

import org.bukkit.command.CommandSender;
import org.sct.lock.Lock;
import org.sct.lock.data.LockData;
import org.sct.lock.util.DownloadUtil;
import org.sct.lock.util.GetVersionlMessage;
import org.sct.lock.util.SubCommand;

import java.io.IOException;

public class Update implements SubCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length == 2) {
            if (args.length == 2 && args[1].equalsIgnoreCase("download")) {
                LockData.getPool().submit(() -> {
                    try {
                        DownloadUtil.download("https://github.com/ICANESCAPE/Lock/releases/download/" + Lock.getInstance().getDescription().getVersion() + "/Lock.jar","Lock.jar",Lock.getInstance().getDataFolder().getPath() + "\\update");
                        sender.sendMessage("§7[§eLock§7]§2下载成功");
                    } catch (IOException e) {
                        sender.sendMessage("§7[§eLock§7]§c下载更新时出错");
                    }
                });

            } else if (args.length == 2 && args[1].equalsIgnoreCase("version")) {
                LockData.getPool().submit(() -> {
                    try {
                        GetVersionlMessage.get(sender);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }

        }
        return true;
    }
}
