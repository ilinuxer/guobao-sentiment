package zx.soft.api.adduser.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import zx.soft.api.adduser.common.MybatisConfig;
import zx.soft.api.adduser.common.TwitterAppConfig;
import zx.soft.api.adduser.dao.server.MonitorUserDaoServer;
import zx.soft.api.domain.TwitterToken;

import java.util.List;
import java.util.Properties;

/**
 * Created by jimbo on 15-3-31.
 */
public class MonitorUserTwitter {
    private static MonitorUserDaoServer server;

    public MonitorUserTwitter() {
        this.server = new MonitorUserDaoServer(MybatisConfig.Servers.GBXM);
    }

    public User createFriendship(String name){

        List<TwitterToken> tokens = getTokens();
        TwitterToken token = tokens.get(tokens.size()-1);
        Properties properties = TwitterAppConfig.getProp("twitter-apps.properties");
        String consumerKey = properties.getProperty("consumer.key");
        String consumerSecret = properties.getProperty("consumer.secret");
        User result = null;
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(consumerKey,consumerSecret);
        twitter.setOAuthAccessToken(new AccessToken(token.getTokenkey(),token.getTokenSecret()));
        try {
            result = twitter.createFriendship(name);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static List<TwitterToken> getTokens(){
        List<TwitterToken> tokens = server.getTwitterTokens();
        return tokens;
    }

}
