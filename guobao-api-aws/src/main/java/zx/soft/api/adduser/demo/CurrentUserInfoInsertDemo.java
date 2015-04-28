package zx.soft.api.adduser.demo;

import com.jimbo.json.JsonUtils;
import zx.soft.api.adduser.common.MybatisConfig;
import zx.soft.api.adduser.dao.server.MonitorUserDaoServer;
import zx.soft.model.user.CurrentUserInfo;

/**
 * Created by jimbo on 4/21/15.
 */
public class CurrentUserInfoInsertDemo {

    public static void main(String[] args) {
        MonitorUserDaoServer daoServer = new MonitorUserDaoServer(MybatisConfig.Servers.GBXM);

        CurrentUserInfo user = new CurrentUserInfo("123","test_name","gp");
        CurrentUserInfo user1 = new CurrentUserInfo("124","test_name","tw");
        CurrentUserInfo user2 = new CurrentUserInfo("125","test_name","fb");
        CurrentUserInfo user3 = new CurrentUserInfo("126","test_name","fb");
//        daoServer.insertCurrentUser(user);
//        daoServer.insertCurrentUser(user1);
//        daoServer.insertCurrentUser(user2);
//        daoServer.insertCurrentUser(user3);

        System.out.println(JsonUtils.toJson(daoServer.getCurrentUsers()));


//        daoServer.deleteCurrentUser("test_id");
    }
}
