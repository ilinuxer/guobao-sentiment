package zx.soft.api.controller;

import com.google.api.services.plus.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import twitter4j.User;
import zx.soft.api.adduser.gplus.MonitorUserGplus;
import zx.soft.api.adduser.twitter.MonitorUserTwitter;
import zx.soft.api.domain.*;
import zx.soft.api.service.AwsService;
import zx.soft.model.aws.SimpleUser;
import zx.soft.utils.log.LogbackUtil;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/aws")
public class AwsController {

    private static final Logger logger = LoggerFactory.getLogger(AwsController.class);

    @Inject
    private AwsService awsService;

    private AwsControllerAction action = new AwsControllerAction();

    private MonitorUserGplus monitorGplus = new MonitorUserGplus();
    private MonitorUserTwitter monitorTwitter = new MonitorUserTwitter();


    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    Object createUsers(@RequestBody SimpleUser user) {
        if (user == null) {
            return "-1";
        }
        //"-1"--查找错误或者用户信息插入数据库失败;"0"--未查到用户;id 值表示插入成功
        String result = "0";
        String sns = user.getSns();
        String userName = user.getName();
        String url = user.getUid().toLowerCase();
        List<String> success = new ArrayList<>();
        try {
            if ("tw".equals(sns)) {

                //添加twitter用户
                try {
                    User person = monitorTwitter.showPerson(userName);
                    logger.info("add twitter id:" + person.getId() + "  name:" + userName);
                    if (person != null) {
                        String result1 = action.insertIntoTwitterUserInfos(person);
                        if (result1 == "0") {
                            result = person.getId() +","+person.getName();
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
//                    success.add("-1");
//                    return new PostResponse(-1,success);
                }


            } else if ("gp".equals(sns)) {
                //添加gplus用户
                List<Person> people = monitorGplus.createFriendship(userName);
                logger.info("people size is :" + people.size());
                if (people == null | people.size() == 0) {
                    success.add("0");
                    return new PostResponse(0,success);
                } else if (people.size() == 1) {
                    Person person = people.get(0);
                    //插入数据库
                    String insertResult = action.insertIntoGplusUserDB(person);
                    if (insertResult == "0") {
                        result = person.getId()+","+person.getDisplayName();
                    } else {
                        result = "-1";
                    }
                } else {
                    for (Person person : people) {
                        String id = person.getId();
                        String name = person.getDisplayName();
                        String sourceUrl = person.getUrl();

                        logger.info("userName : {} inputName : {} ",name,userName);
                        if (userName.trim().equals(name) && EncodeUrl.isPerson(url,sourceUrl)){
                            logger.info("search user {} success",name);
                            String insertResult = action.insertIntoGplusUserDB(person);
                            if (insertResult == "0") {
                                result = person.getId()+","+person.getDisplayName();
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
                result = "-1";
            }
        } catch (Exception e) {
            result = "-1";
            logger.error("Exception:{}", LogbackUtil.expection2Str(e));
        }

        //根据result结果设置返回值
        if (result.equals("0")){
            success.add("0");
            return new PostResponse(-1,success);
        }else if (result.equals("-1")){
            success.add("-1");
            return new PostResponse(-1,success);
        }else {
            String[] results = result.split(",");
            success.add(results[0]);
            success.add(results[1]);
            return new PostResponse(0,success);
        }
    }

    /**
     * status 跟踪
     */
    @RequestMapping(value = "/status",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    Object focusGp(@RequestBody SimpleStatus status){
        List<String> unSuccess = new ArrayList<>();
        if (status == null){
            return new ErrorResponse.Builder(-1,"has no id");
        }

        String statusId = status.getStatusId();
        String statusSns = status.getSns();
        try {
            logger.info("Focus "+ statusSns +" status : {}", statusId);
            //添加到关注列表（以定时获取评论信息）
            action.insertStatus(new SimpleStatus(statusId,statusSns));
        } catch (Exception e) {
            logger.error("Exception:{}", LogbackUtil.expection2Str(e));
            unSuccess.add(status.getStatusId());
        }
        if (unSuccess.size() > 0) {
            return new PostResponse(-1, unSuccess);
        } else {
            return new PostResponse(0, null);
        }
    }

    /**
     * 删除status 跟踪
     */
    @RequestMapping(value = "/delstatus",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    Object delFocusGp(@RequestBody List<SimpleStatus> statuses){
        List<String> unSuccess = new ArrayList<>();
        if (statuses == null){
            return new ErrorResponse.Builder(-1,"has no status");
        }
        for (SimpleStatus status:statuses){
            try {
                logger.info("delete Focus on status : {}", status.getStatusId());
                //添加到关注列表（以定时获取评论信息）
                action.delStatus(status.getStatusId(),status.getSns());
            } catch (Exception e) {
                logger.error("Exception:{}", LogbackUtil.expection2Str(e));
                unSuccess.add(status.getStatusId());
            }

        }
        if (unSuccess.size() > 0) {
            return new PostResponse(-1, unSuccess);
        } else {
            return new PostResponse(0, null);
        }
    }

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
                action.deleteGplusUser(id);
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
                action.deleteTwUser(id);
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
}
