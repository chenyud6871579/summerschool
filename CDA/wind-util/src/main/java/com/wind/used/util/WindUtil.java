package com.wind.used.util;

import java.net.HttpURLConnection;
import java.net.URL;

public interface WindUtil {
    public static void pushProgressBar(int add, String message){

        String pathStr = "http://localhost:8080/progress/mr/add?add="
                +String.valueOf(add)
                +"&message="
                +message;

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
