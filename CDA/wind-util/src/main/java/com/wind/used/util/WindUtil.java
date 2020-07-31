package com.wind.used.util;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public interface WindUtil {
    static void pushProgressBar(int add, String message)  {

        String codeMessage = "";
        try {
            codeMessage = URLEncoder.encode(URLEncoder.encode(message,"UTF-8"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String pathStr = "http://localhost:8080/progress/mr/add?add="
                +add
                +"&message="
                +codeMessage;

        try {
            URL url = new URL(pathStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");//GET和POST必须全大写
            conn.connect();
            conn.getResponseCode();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
