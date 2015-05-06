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
                    logger.info("add twitter id:" + person.getId() + "  name:" + userName);
                    if (person != null) {
                        String result1 = action.insertIntoTwitterUserInfos(person);
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
                    String insertResult = action.insertIntoGplusUserDB(person);
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
                            String insertResult = action.insertIntoGplusUserDB(person);
                            if (insertResult == "0") {
                                result = person.getId();
                            } else {
                                result = "-1";
                            }
                            break;
                        }
                        if (url.contains(id)) {
                            String insertResult = action.insertIntoGplusUserDB(person);
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
    Object delFocusGp(@RequestBody List<String> statusIds){
        List<String> unSuccess = new ArrayList<>();
        if (statusIds == null){
            return new ErrorResponse.Builder(-1,"has no id");
        }
        for (String statusId:statusIds){
            try {
                logger.info("delete Focus on status : {}", statusId);
                //添加到关注列表（以定时获取评论信息）
                action.delStatus(statusId);
            } catch (Exception e) {
                logger.error("Exception:{}", LogbackUtil.expection2Str(e));
                unSuccess.add(statusId);
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
