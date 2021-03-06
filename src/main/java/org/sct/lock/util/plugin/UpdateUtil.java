package org.sct.lock.util.plugin;

import org.sct.lock.Lock;
import org.sct.lock.util.plugin.GetVersionlMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
/**
 * @author icestar
 */
public class UpdateUtil {

    public static String newestversion = null;

    public static void checkupdate() {
        try {
            URL url = new URL("https://api.github.com/repos/ICANESCAPE/Lock/releases");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            newestversion = reader.readLine().split(",") [7];
            String currentVersion = Lock.getInstance().getDescription().getVersion();
            String[] replace = {"tag_name","\"",":"};
            for (String cr :replace) {
                newestversion = newestversion.replace(cr,"");
            }
            reader.close();
            if (currentVersion.equalsIgnoreCase(newestversion)) {
                Lock.getInstance().getServer().getConsoleSender().sendMessage("§7[§eLock§7]§2你正在使用最新的" + currentVersion + "版本");
            } else {
                Lock.getInstance().getServer().getConsoleSender().sendMessage("§7[§eLock§7]§c最新版本为" + newestversion);
                GetVersionlMessage.get(Lock.getInstance().getServer().getConsoleSender());
                Lock.getInstance().getServer().getConsoleSender().sendMessage("§7[§eLock§7]§c请前往https://www.mcbbs.net/thread-932739-1-1.html下载");
            }
        } catch (IOException e) {
            Lock.getInstance().getLogger().severe("插件在检测版本时发生错误!");
        }
    }
}
