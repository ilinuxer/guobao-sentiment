package zx.soft.api.adduser.post;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zx.soft.utils.json.JsonUtils;
import zx.soft.utils.log.LogbackUtil;

/**
 * Created by jimbo on 15-4-10.
 * post数据到指定接口
 */
public class HttpClientPost {
    private static Logger logger = LoggerFactory.getLogger(HttpClientPost.class);

    public static String postData(String url,String data){
        String result = "";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        try {
            StringEntity params = new StringEntity(data);
            request.setHeader("content-type", "application/json");
            request.setEntity(params);
            logger.info("post 用户详细信息");
            HttpResponse response = httpClient.execute(request);
            logger.info("Response Code : "
                    + response.getStatusLine().getStatusCode());

        } catch (Exception e) {
            logger.info("post inner inner exception ");
            logger.error("Exception:{}", LogbackUtil.expection2Str(e));
            result = "-1";
        }
        return result;
    }
}
