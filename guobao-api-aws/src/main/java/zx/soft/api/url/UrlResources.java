package zx.soft.api.url;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jimbo Han on 2015/3/25.
 */
public class UrlResources {

    public static String getResource (String strUrl) throws Exception{
        URL url = new URL(strUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");

        conn.setConnectTimeout(5 * 1000);
        InputStream inStream =  conn.getInputStream();  //通过输入流获取html二进制数据
        byte[] data = readInputStream(inStream);        //把二进制数据转化为byte字节数据
        String htmlSource = new String(data);

        return htmlSource;
    }
    public static byte[] readInputStream(InputStream instream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[]  buffer = new byte[1204];
        int len = 0;
        while ((len = instream.read(buffer)) != -1){
            outStream.write(buffer,0,len);
        }
        instream.close();
        return outStream.toByteArray();
    }

}
