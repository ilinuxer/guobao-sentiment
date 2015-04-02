package zx.soft.api.adduser.demo;

import zx.soft.api.adduser.common.PostUrlConfig;

import java.util.Properties;

/**
 * Created by jimbo on 15-4-2.
 */
public class PostUrlDemo {
    public static void main(String[] args) {
        Properties properties =
        PostUrlConfig.getProp("posturls.properties");

        System.out.println(properties.getProperty("guobao.twitter.url"));
    }
}
