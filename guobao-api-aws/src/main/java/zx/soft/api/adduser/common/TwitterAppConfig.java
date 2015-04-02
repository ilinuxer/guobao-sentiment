package zx.soft.api.adduser.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jimbo on 15-3-31.
 */
public class TwitterAppConfig {
    private static Logger logger = LoggerFactory.getLogger(TwitterAppConfig.class);

    public static Properties getProp(String fileName){
        Properties result = new Properties();
        try(InputStream inputStream = TwitterAppConfig.class.getClassLoader().getResourceAsStream(fileName)){
            result.load(inputStream);
            return result;
        } catch (IOException e) {
            logger.error("wrong during reading TwitterAppConfig");
            throw new RuntimeException(e);
        }
    }
}
