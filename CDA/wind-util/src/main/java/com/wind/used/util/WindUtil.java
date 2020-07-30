package com.wind.used.util;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public interface WindUtil {
    public static void pushProgressBar(int add, String message)  {

//
//        try {
//            message = URLEncoder.encode(message, "GBK");
//        } catch (UnsupportedEncodingException e) {
//            System.out.println("进度条请求失败");
//            e.printStackTrace();
//            return;
//        }

//        String pathStr = "http://localhost:8080/progress/mr/add?add="
//                +String.valueOf(add)
//                +"&message="
//                +message;
        String pathStr = "http://localhost:8080/progress/mr/add";

        try {
            URL url = new URL(pathStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");//GET和POST必须全大写
            conn.setRequestProperty("add",String.valueOf(add));
            conn.setRequestProperty("message",message);
            conn.connect();
            conn.getResponseCode();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
