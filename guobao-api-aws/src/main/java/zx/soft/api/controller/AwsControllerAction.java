package zx.soft.api.controller;

import com.google.api.services.plus.model.Person;
import com.jimbo.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.User;
import zx.soft.api.adduser.common.MybatisConfig;
import zx.soft.api.adduser.common.PostUrlConfig;
import zx.soft.api.adduser.dao.server.MonitorUserDaoServer;
import zx.soft.api.adduser.post.HttpClientPost;
import zx.soft.api.domain.GplusUserInfos;
import zx.soft.api.domain.SimpleStatus;
import zx.soft.api.domain.TwitterUserInfos;
import zx.soft.model.user.CurrentUserInfo;
import zx.soft.utils.time.TimeUtils;

import java.util.Properties;

/**
 * Created by jimbo on 5/3/15.
 */
public class AwsControllerAction {
    private static Logger logger = LoggerFactory.getLogger(AwsControllerAction.class);

    private MonitorUserDaoServer daoServer = new MonitorUserDaoServer(MybatisConfig.Servers.GBXM);

    protected GplusUserInfos getGplusUserInfos(Person person) {
        return new GplusUserInfos(person.getId(), person.getDisplayName(), person.getDisplayName(), person.getUrl(), person.getImage().getUrl());
    }

    protected TwitterUserInfos getTwitterUserInfos(User person) {
        return new TwitterUserInfos(person.getId(), person.getName(),
                person.getScreenName(), person.getProfileImageURL(), TimeUtils.transStrToCommonDateStr(person.getCreatedAt().toString()),
                person.getLocation(), person.getURL(), person.getFavouritesCount(), person.getUtcOffset(),
                person.getListedCount(), person.getFollowersCount(), person.getLang(), person.getDescription(),
                person.isVerified(), person.getTimeZone(), person.getStatusesCount(), person.getFriendsCount());
    }

    protected String insertIntoGplusUserDB(Person person) {

        String outterResult = "0";

        GplusUserInfos personInfo = getGplusUserInfos(person);
        logger.info("POST google+ 用户 {} 详细信息到solr接口并插入用户信息详情表",person.getDisplayName());
        String url = getPostUrl("guobao.gplus.url");

        String result = HttpClientPost.postData(url, JsonUtils.toJson(personInfo));
        logger.info("post gplus user {} result is :" + result ,person.getDisplayName());

        if (result == "-1") {
            outterResult = "-1";
        }
        try {
            //插入监控用户列表      "googleUserInfos"
            daoServer.addGplusListern(person.getId(), person.getDisplayName());
            //插入用户详细信息列表    "user_info_googleplus"
            daoServer.addGplusUserInfo(personInfo);
            //插入新增用户信息列表    "current_user_info"
            daoServer.insertCurrentUser(new CurrentUserInfo(person.getId(), person.getDisplayName(), "gp"));
        }catch (Exception e ){
            logger.error("Exception {}",e);
            outterResult = "-1";

        }
        return outterResult;
    }

    /**
     * POST用户信息数据到指定接口，并将这些信息存库
     */
    protected String insertIntoTwitterUserInfos(User person) {

        String outterResult = "0";

        TwitterUserInfos personInfo = getTwitterUserInfos(person);
        logger.info("POST Twitter 用户 {} 详细信息到solr接口并插入用户信息详情表",person.getScreenName());
        String url = getPostUrl("guobao.twitter.url");
        String result = HttpClientPost.postData(url, JsonUtils.toJson(personInfo));
        logger.info("post result :" + result);
        if (result == "-1") {
            outterResult = "-1";
        }
        try{
            logger.info("开始插入数据库");
            //插入Twitter监控用户列表
            daoServer.addTwitterListern(String.valueOf(person.getId()), person.getScreenName(), 1L);
            logger.info("listern success");
            //插入用户详细信息列表
            daoServer.addTwitterUserInfo(personInfo);
            //插入新增用户信息列表
            daoServer.insertCurrentUser(new CurrentUserInfo(Long.toString(person.getId()), person.getScreenName(), "tw"));
        } catch (Exception e){
            outterResult = "-1";
            logger.error("error");
        }

        return outterResult;
    }

    /**
     * 数据库添加状态监控信息
     */
    protected void insertStatus(SimpleStatus status){
        try{
            daoServer.insertStatus(status);
        }catch (Exception e){
            logger.error("insert into status error : {}" ,e);
        }
    }


    protected void delStatus(String statusId){
        try{
            daoServer.delStatus(statusId);
        }catch (Exception e){
            logger.error("delete status error : {}",e);
        }
    }


    /**
     * 数据库中删除gplus监控用户与用户详细信息
     */
    protected void deleteGplusUser(String id) {
        if (daoServer.isExits(id)){
            daoServer.deleteCurrentUser(id);
        }
        daoServer.delGplusMonitorInfo(id);
        daoServer.delGplusUserInfo(id);
    }

    /**
     * 解除Twitter好友关系，并从数据库删除该用户的详细信息
     */
    protected void deleteTwUser(String id){
        if (daoServer.isExits(id)){
            daoServer.deleteCurrentUser(id);
        }
        //从数据库中删除用户详细信息
        daoServer.delTwUserInfo(id);
        daoServer.delTwitterListern(id);
    }

    protected String getPostUrl(String urlName) {
        Properties properties = PostUrlConfig.getProp("posturls.properties");
        return properties.getProperty(urlName);
    }
}
