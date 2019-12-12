package org.sct.lock.util;

import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GetVersionlMessage {
    public static void get(CommandSender sender) throws IOException {
        URL url = new URL("https://lovesasuna.github.io/update/Lock");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(3 * 1000);
        conn.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String version = reader.lines().parallel().skip(8).limit(1).collect(Collectors.toList()).get(0);
        List<String> versionList = getMsg(version);
        sender.sendMessage("§e===============================================");
        for (String versionMsg : versionList) {
            sender.sendMessage(versionMsg);
        }
        sender.sendMessage("§e===============================================");
    }

    private static List<String> getMsg (String urlString) throws UnsupportedEncodingException {
        byte[] bytes = urlString.getBytes("GBK");
        List<String> msgList = new ArrayList<>();
        String message = new String(bytes, StandardCharsets.UTF_8);
        for (String string : message.split(";")) {
            msgList.add(string);
        }
        return BasicUtil.convert(msgList);
    }
}
