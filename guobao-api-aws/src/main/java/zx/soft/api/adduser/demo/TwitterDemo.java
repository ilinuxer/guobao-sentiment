package zx.soft.api.adduser.demo;

import twitter4j.User;
import zx.soft.api.adduser.common.MybatisConfig;
import zx.soft.api.adduser.dao.server.MonitorUserDaoServer;
import zx.soft.api.adduser.twitter.MonitorUserTwitter;
import zx.soft.api.domain.TwitterUserInfos;
import zx.soft.utils.json.JsonUtils;
import zx.soft.utils.time.TimeUtils;

/**
 * Created by jimbo on 15-4-2.
 */
public class TwitterDemo {

    private MonitorUserDaoServer daoServer = new MonitorUserDaoServer(MybatisConfig.Servers.GBXM);

    public static void main(String[] args) {
        TwitterDemo demo = new TwitterDemo();
        MonitorUserTwitter monitor = new MonitorUserTwitter();
        User user = monitor.createFriendship("jimbo2527");
        TwitterUserInfos userInfos = demo.getTwitterUserInfos(user);
        System.out.println(JsonUtils.toJson(demo.getTwitterUserInfos(user)));
        demo.insertIntoTwitterUserInfos(user);

    }

    public TwitterUserInfos getTwitterUserInfos(User person){
        System.out.println(JsonUtils.toJson(person));
        return new TwitterUserInfos(person.getId(),person.getName(),
                person.getScreenName(),person.getProfileImageURL(), TimeUtils.transStrToCommonDateStr(person.getCreatedAt().toString()),
                person.getLocation(),person.getURL(),person.getFavouritesCount(),person.getUtcOffset(),
                person.getListedCount(),person.getFollowersCount(),person.getLang(),person.getDescription(),
                person.isVerified(),person.getTimeZone(),person.getStatusesCount(),person.getFriendsCount());
    }

    public void insertIntoTwitterUserInfos(User person){
        TwitterUserInfos personInfo = getTwitterUserInfos(person);
        daoServer.addTwitterUserInfo(personInfo);

    }


}
