package zx.soft.api.adduser.twitter;

import com.jimbo.json.JsonUtils;
import com.jimbo.log.LogbackUtil;
import com.jimbo.math.RandomNum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import zx.soft.api.adduser.common.MybatisConfig;
import zx.soft.api.adduser.common.TwitterAppConfig;
import zx.soft.api.adduser.dao.server.MonitorUserDaoServer;
import zx.soft.api.domain.TwitterToken;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Created by jimbo on 15-3-31.
 */
public class MonitorUserTwitter {
    private static Logger logger = LoggerFactory.getLogger(MonitorUserTwitter.class);
    private static MonitorUserDaoServer server;

    public MonitorUserTwitter() {
        this.server = new MonitorUserDaoServer(MybatisConfig.Servers.GBXM);
    }

    /**
     * 添加关注用户
     * @param name
     * @return
     */
    public User createFriendship(String name){

        List<TwitterToken> tokens = getTokens();
        TwitterToken token = tokens.get(RandomNum.integerRandom(-1, tokens.size()));
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
            logger.error("Exception : {}" , LogbackUtil.exception2Str(e));
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<User> destoryFriendship(String id) throws TwitterException {
        List<User> result = new LinkedList<>();
        User user = null;
        List<TwitterToken> tokens = getTokens();
        Properties properties = TwitterAppConfig.getProp("twitter-apps.properties");
        String consumerKey = properties.getProperty("consumer.key");
        String consumerSecret = properties.getProperty("consumer.secret");
        for (TwitterToken token : tokens){
            Twitter twitter = new TwitterFactory().getInstance();
            twitter.setOAuthConsumer(consumerKey, consumerSecret);
            twitter.setOAuthAccessToken(new AccessToken(token.getTokenkey(), token.getTokenSecret()));

            try{
                user = twitter.destroyFriendship(Long.getLong(id));
                logger.info("删除用户");
            }catch (Exception e){
                logger.error("下一用户");
                continue;
            }
            result.add(user);
        }
        return result;
    }



    public static List<TwitterToken> getTokens(){
        List<TwitterToken> tokens = server.getTwitterTokens();
        return tokens;
    }

}
