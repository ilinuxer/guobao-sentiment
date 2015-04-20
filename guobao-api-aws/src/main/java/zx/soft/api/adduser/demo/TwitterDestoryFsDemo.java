package zx.soft.api.adduser.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.TwitterException;
import twitter4j.User;
import zx.soft.api.adduser.twitter.MonitorUserTwitter;

import java.util.List;

/**
 * Created by jimbo on 4/17/15.
 */
public class TwitterDestoryFsDemo {
    private static Logger logger = LoggerFactory.getLogger(TwitterDestoryFsDemo.class);

    public static void main(String[] args) {
        MonitorUserTwitter monitor = new MonitorUserTwitter();
        try {
            List<User> users = monitor.destoryFriendship("2581266745");
            System.out.println("test");
        } catch (TwitterException e) {
            logger.info("hehhe");
            e.printStackTrace();
        }
    }
}
