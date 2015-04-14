package zx.soft.api.controller;

import com.google.api.services.plus.model.Person;
import com.jimbo.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import twitter4j.User;
import zx.soft.api.adduser.common.MybatisConfig;
import zx.soft.api.adduser.common.PostUrlConfig;
import zx.soft.api.adduser.dao.server.MonitorUserDaoServer;
import zx.soft.api.adduser.gplus.MonitorUserGplus;
import zx.soft.api.adduser.post.HttpClientPost;
import zx.soft.api.adduser.post.RestletPost;
import zx.soft.api.adduser.twitter.MonitorUserTwitter;
import zx.soft.api.domain.ErrorResponse;
import zx.soft.api.domain.GplusUserInfos;
import zx.soft.api.domain.TwitterUserInfos;
import zx.soft.api.service.AwsService;
import zx.soft.api.url.UrlResources;
import zx.soft.model.aws.SimpleUser;
import zx.soft.model.aws.SnapShot;
import zx.soft.utils.checksum.CheckSumUtils;
import zx.soft.utils.log.LogbackUtil;
import zx.soft.utils.time.TimeUtils;

import javax.inject.Inject;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/aws")
public class AwsController {

    private static final Logger logger = LoggerFactory.getLogger(AwsController.class);

    @Inject
    private AwsService awsService;

    private MonitorUserGplus monitorGplus = new MonitorUserGplus();
    private MonitorUserTwitter monitorTwitter = new MonitorUserTwitter();

    private MonitorUserDaoServer daoServer = new MonitorUserDaoServer(MybatisConfig.Servers.GBXM);

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    String createUsers(@RequestBody SimpleUser user) {
        if (user == null) {
            return "-1";
        }
        //"-1"--查找错误或者用户信息插入数据库失败;"0"--未查到用户
        String result = "0";

        String sns = user.getSns();
        String userName = user.getName();
        String url = user.getUid();
        logger.info(sns+"::"+userName+"::"+url);
        try {
            if ("tw".equals(sns)) {
                //添加twitter用户
                try{
                    User person = monitorTwitter.createFriendship(userName);
                    logger.info(JsonUtils.toJson(person));
                    if(person != null ){
                        insertIntoTwitterUserInfos(person);
                        result = person.getId() + "";
                    }else {
                        result = "0";
                    }
                }catch (Exception e){
                    result = "0";
                }


            } else if ("gp".equals(sns)) {
                //添加gplus用户
                List<Person> people = monitorGplus.createFriendship(userName);
                if (people == null | people.size() == 0) {
                    result = "0";
                } else if (people.size() == 1) {
                    Person person = people.get(0);
                    //插入数据库
                    String insertResult = insertIntoGplusUserDB(person);
                    if(insertResult == "0"){
                        result = person.getId();
                    }else {
                        result = "-1";
                    }

                } else {
                    String lowUserName = userName.replaceAll("\\s", "").replaceAll("\\(", "").replaceAll("\\)","").toLowerCase();
                    for (Person person : people) {
                        String id = person.getId();
                        if (person.getUrl().contains(lowUserName)) {
                            String insertResult = insertIntoGplusUserDB(person);
                            if(insertResult == "0"){
                                result = person.getId();
                            }else {
                                result = "-1";
                            }
                            break;
                        }
                        if (url.contains(id)) {
                            String insertResult = insertIntoGplusUserDB(person);
                            if(insertResult == "0"){
                                result = person.getId();
                            }else {
                                result = "-1";
                            }
                            break;
                        }else{
                            result="0";
                        }
                    }

                }
            } else if ("fb".equals(sns)) {
                //添加facebook用户
                return "undo";
            }
        } catch (Exception e) {
            logger.error("Exception:{}", LogbackUtil.expection2Str(e));
            return "-1";
        }
        return result;
    }

    @RequestMapping(value = "/snapshot", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    Object createUrls(@RequestBody List<String> urls) {
        if (urls == null) {
            return new ErrorResponse.Builder(-1, "has no urls");
        }
        String url = urls.get(0);
        String html = "";
        try {
            html = awsService.selectSnapShotHtml(CheckSumUtils.getMD5(url));

            if (html == null || html == "") {
                html = UrlResources.getResource(url);
                awsService.insertSnapShot(new SnapShot(CheckSumUtils.getMD5(url), url, "0"));
                awsService.updateSnapShot(new SnapShot(CheckSumUtils.getMD5(url), url, html));
            }

        } catch (Exception e) {
            logger.error("Exception:{}", LogbackUtil.expection2Str(e));
        }

        return (html == "" || html == null) ? "0" : html;

    }

    private GplusUserInfos getGplusUserInfos(Person person){
        return new GplusUserInfos(person.getDisplayName(),person.getId(),person.getImage().getUrl(),person.getUrl());
    }

    private TwitterUserInfos getTwitterUserInfos(User person){
        return new TwitterUserInfos(person.getId(),person.getName(),
                person.getScreenName(),person.getProfileImageURL(), TimeUtils.transStrToCommonDateStr(person.getCreatedAt().toString()),
                person.getLocation(),person.getURL(),person.getFavouritesCount(),person.getUtcOffset(),
                person.getListedCount(),person.getFollowersCount(),person.getLang(),person.getDescription(),
                person.isVerified(),person.getTimeZone(),person.getStatusesCount(),person.getFriendsCount());
    }

    private String insertIntoGplusUserDB(Person person){
        GplusUserInfos personInfo = getGplusUserInfos(person);
        logger.info("POST用户详细信息到solr接口并插入用户信息详情表！！");
        String url = getPostUrl("guobao.gplus.url");
        String result = HttpClientPost.postData(url, JsonUtils.toJson(personInfo));
//        String result = RestletPost.postData(url,JsonUtils.toJson(personInfo));
        System.out.println("post result :" + result);
        if(result == "-1"){
            return "-1";
        }
        daoServer.addGplusListern(person.getId(), person.getDisplayName());
        daoServer.addGplusUserInfo(personInfo);
        return "0";
    }

    private void insertIntoTwitterUserInfos(User person){

        TwitterUserInfos personInfo = getTwitterUserInfos(person);
        logger.info("POST用户详细信息到solr接口并插入用户信息详情表！！");
        String url = getPostUrl("guobao.twitter.url");
//        HttpClientPost.postData(url, JsonUtils.toJson(personInfo));
        String result = RestletPost.postData(url,JsonUtils.toJson(personInfo));
        System.out.println("post result :" + result);
        daoServer.addTwitterUserInfo(personInfo);
    }

    private String getPostUrl(String urlName){
        Properties properties = PostUrlConfig.getProp("posturls.properties");
        return properties.getProperty(urlName);
    }

}
