package zx.soft.api.adduser.post;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zx.soft.utils.log.LogbackUtil;

import java.io.IOException;

/**
 * Created by jimbo on 15-4-2.
 * 将数据Post到指定接口
 */
public class RestletPost {

    private static Logger logger = LoggerFactory.getLogger(RestletPost.class);

    /**
     * 将data Post到指定的Url
     */
    public static String postData(String url,String data){

        String result = "";
        ClientResource requsetResource = new ClientResource(url);
        Representation entity = new StringRepresentation(data);
        entity.setMediaType(MediaType.APPLICATION_JSON);
        Representation response = requsetResource.post(entity);
        try{
            result = response.getText();
            logger.info("post inner result :: " + result);
            if(result.charAt(13) != 0){
                return "-1";
            }
        } catch (IOException e) {
            logger.error("Exception:{}", LogbackUtil.expection2Str(e));
            result = "-1";
        }finally {
            response.release();
        }
        return result;
    }
}
