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
import zx.soft.api.adduser.twitter.MonitorUserTwitter;
import zx.soft.api.domain.*;
import zx.soft.api.service.AwsService;
import zx.soft.api.url.UrlResources;
import zx.soft.model.aws.SimpleUser;
import zx.soft.model.aws.SnapShot;
import zx.soft.model.user.CurrentUserInfo;
import zx.soft.utils.checksum.CheckSumUtils;
import zx.soft.utils.log.LogbackUtil;
import zx.soft.utils.time.TimeUtils;

import javax.inject.Inject;
import java.util.ArrayList;
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
        //"-1"--查找错误或者用户信息插入数据库失败;"0"--未查到用户;id 值表示插入成功
        String result = "0";
        String sns = user.getSns();
        String userName = user.getName();
        String url = user.getUid();
        try {
            if ("tw".equals(sns)) {
                //添加twitter用户
                try {
//                    User person = monitorTwitter.createFriendship(userName);
                    User person = monitorTwitter.showPerson(userName);
                    logger.info("添加关注 ： id:" + person.getId() + "  name:" + userName);
                    if (person != null) {
                        String result1 = insertIntoTwitterUserInfos(person);
                        System.out.println(result1);
                        if (result1 == "0") {
                            result = person.getId() + "";
                        } else if (result1 == "-1") {
                            result = "-1";
                        } else {
                            result = "0";
                        }

                    } else if (person == null) {
                        result = "0";
                    }
                } catch (Exception e) {
                    logger.error("Exception{}:" + LogbackUtil.expection2Str(e));
                    result = "-1";
                }


            } else if ("gp".equals(sns)) {
                //添加gplus用户
                List<Person> people = monitorGplus.createFriendship(userName);
                logger.info("people size is :" + people.size());
                if (people == null | people.size() == 0) {
                    result = "0";
                } else if (people.size() == 1) {
                    Person person = people.get(0);
                    //插入数据库
                    String insertResult = insertIntoGplusUserDB(person);
                    if (insertResult == "0") {
                        result = person.getId();
                    } else {
                        result = "-1";
                    }
                } else {
                    String lowUserName = userName.replaceAll("\\s", "").replaceAll("\\(", "").replaceAll("\\)", "").toLowerCase();
                    for (Person person : people) {
                        String id = person.getId();
                        if (person.getUrl().contains(lowUserName)) {
                            String insertResult = insertIntoGplusUserDB(person);
                            if (insertResult == "0") {
                                result = person.getId();
                            } else {
                                result = "-1";
                            }
                            break;
                        }
                        if (url.contains(id)) {
                            String insertResult = insertIntoGplusUserDB(person);
                            if (insertResult == "0") {
                                result = person.getId();
                            } else {
                                result = "-1";
                            }
                            break;
                        } else {
                            result = "0";
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

    //网页快照部分
//    @RequestMapping(value = "/snapshot", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    public
//    @ResponseBody
//    Object createUrls(@RequestBody List<String> urls) {
//        if (urls == null) {
//            return new ErrorResponse.Builder(-1, "has no urls");
//        }
//        String url = urls.get(0);
//        String html = "";
//        try {
//            html = awsService.selectSnapShotHtml(CheckSumUtils.getMD5(url));
//
//            if (html == null || html == "") {
//                html = UrlResources.getResource(url);
//                awsService.insertSnapShot(new SnapShot(CheckSumUtils.getMD5(url), url, "0"));
//                awsService.updateSnapShot(new SnapShot(CheckSumUtils.getMD5(url), url, html));
//            }
//
//        } catch (Exception e) {
//            logger.error("Exception:{}", LogbackUtil.expection2Str(e));
//        }
//
//        return (html == "" || html == null) ? "0" : html;
//
//    }


    /**
     * 删除gplus重点关注人员
     *
     * @param ids 用户id列表
     * @return
     */
    @RequestMapping(value = "/gpdel", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    Object deleteGpUsers(@RequestBody List<String> ids) {
        List<String> unSuccess = new ArrayList<>();

        if (ids == null) {
            return new ErrorResponse.Builder(-1, "has no ids");
        }
        for (String id : ids) {
            try {
                logger.info("Delete Gp Monitor user : {}", id);
                deleteGplusUser(id);
            } catch (Exception e) {
                logger.error("Exception:{}", LogbackUtil.expection2Str(e));
                unSuccess.add(id);
            }
        }
        if (unSuccess.size() > 0) {
            return new PostResponse(-1, unSuccess);
        } else {
            return new PostResponse(0, null);
        }
    }

    @RequestMapping(value = "/twdel", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    Object deleteTwUsers(@RequestBody List<String> ids) {
        List<String> unSuccess = new ArrayList<>();
        if (ids == null) {
            return new ErrorResponse.Builder(-1, "has no ids");
        }
        for (String id : ids) {
            try {
                deleteTwUser(id);
            } catch (Exception e) {
                logger.error("Exception:{}", LogbackUtil.expection2Str(e));
                unSuccess.add(id);
            }
        }
        if (unSuccess.size() > 0) {
            return new PostResponse(-1, unSuccess);
        } else {
            return new PostResponse(0, null);
        }
    }

    private GplusUserInfos getGplusUserInfos(Person person) {
        return new GplusUserInfos(person.getId(), person.getDisplayName(), person.getDisplayName(), person.getUrl(), person.getImage().getUrl());
    }

    private TwitterUserInfos getTwitterUserInfos(User person) {
        return new TwitterUserInfos(person.getId(), person.getName(),
                person.getScreenName(), person.getProfileImageURL(), TimeUtils.transStrToCommonDateStr(person.getCreatedAt().toString()),
                person.getLocation(), person.getURL(), person.getFavouritesCount(), person.getUtcOffset(),
                person.getListedCount(), person.getFollowersCount(), person.getLang(), person.getDescription(),
                person.isVerified(), person.getTimeZone(), person.getStatusesCount(), person.getFriendsCount());
    }

    private String insertIntoGplusUserDB(Person person) {

        GplusUserInfos personInfo = getGplusUserInfos(person);
        logger.info("POST google+ 用户 {} 详细信息到solr接口并插入用户信息详情表",person.getDisplayName());
        String url = getPostUrl("guobao.gplus.url");

        String result = HttpClientPost.postData(url, JsonUtils.toJson(personInfo));
        logger.info("post gplus user {} result is :" + result ,person.getDisplayName());

        if (result == "-1") {
            return "-1";
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
        }
        return "0";
    }

    /**
     * POST用户信息数据到指定接口，并将这些信息存库
     */
    private String insertIntoTwitterUserInfos(User person) {

        TwitterUserInfos personInfo = getTwitterUserInfos(person);
        logger.info("POST Twitter 用户 {} 详细信息到solr接口并插入用户信息详情表",person.getScreenName());
        String url = getPostUrl("guobao.twitter.url");
        String result = HttpClientPost.postData(url, JsonUtils.toJson(personInfo));
        logger.info("post result :" + result);
        if (result == "-1") {
            return "-1";
        }
        try{
            //插入Twitter监控用户列表
            daoServer.addTwitterListern(String.valueOf(person.getId()),person.getScreenName(),1L);
            //插入用户详细信息列表
            daoServer.addTwitterUserInfo(personInfo);
        } catch (Exception e){
            logger.error("error");
        }
        //插入新增用户信息列表
        daoServer.insertCurrentUser(new CurrentUserInfo(Long.toString(person.getId()), person.getScreenName(), "tw"));
        return "0";
    }

    /**
     * 数据库中删除gplus监控用户与用户详细信息
     */
    private void deleteGplusUser(String id) {
        daoServer.delGplusMonitorInfo(id);
        daoServer.delGplusUserInfo(id);
    }

    /**
     * 解除Twitter好友关系，并从数据库删除该用户的详细信息
     */
    private void deleteTwUser(String id){
        //从数据库中删除用户详细信息
        daoServer.delTwUserInfo(id);
        daoServer.delTwitterListern(id);
    }

    private String getPostUrl(String urlName) {
        Properties properties = PostUrlConfig.getProp("posturls.properties");
        return properties.getProperty(urlName);
    }

}
