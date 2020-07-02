package cn.tedu.musicplayer.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils {
    public static String getStringForOkHttp(String path) throws IOException {
        OkHttpClient client=new OkHttpClient();
        //模拟浏览器请求，否则403，没有请求权限
        Request request =
                new Request.Builder().url(path).removeHeader("User-Agent").addHeader("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)").build();
        Response response=client.newCall(request).execute();
        String responseData=response.body().string();
        return responseData;
    }
    /**
     * 发送http get请求 返回接收到的响应输入流
     * @param path  请求资源路径
     * @return
     */
    public static InputStream getInputStream(String path)throws Exception{
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        InputStream is = conn.getInputStream();
        return is;
    }
}
