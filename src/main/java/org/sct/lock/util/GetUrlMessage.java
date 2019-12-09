package org.sct.lock.util;

import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetUrlMessage {
    public static void get(CommandSender sender) throws IOException {
        URL url = new URL("https://lovesasuna.github.io/update/Lock");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(3 * 1000);
        conn.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String read;
        int line = 0;
        while ((read = reader.readLine()) != null) {
            line++;
            if (line == 9) {
                sender.sendMessage(unicodeToUtf8(read));
            }
        }

    }

    private static String unicodeToUtf8 (String string) throws UnsupportedEncodingException {
        byte[] bytes = string.getBytes("GBK");
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
