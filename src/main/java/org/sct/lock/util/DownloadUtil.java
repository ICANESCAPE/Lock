package org.sct.lock.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadUtil {
    public static void Download(String urlString, String fileName, String savePath) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        /*设置超时时间为3秒*/
        conn.setConnectTimeout(3 * 1000);
        /*得到输入流*/
        InputStream inputStream = conn.getInputStream();
        /*获取字节数组*/
        byte[] getData = readInputStream(inputStream);

        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
    }

    private static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer,0,len);
        }
        bos.close();
        return bos.toByteArray();
    }
}
