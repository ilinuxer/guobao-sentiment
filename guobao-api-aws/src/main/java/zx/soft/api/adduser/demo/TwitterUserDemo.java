package zx.soft.api.adduser.demo;

import zx.soft.api.adduser.common.MybatisConfig;
import zx.soft.api.adduser.dao.server.MonitorUserDaoServer;

/**
 * Created by jimbo on 4/17/15.
 */
public class TwitterUserDemo {
    public static void main(String[] args) {
        MonitorUserDaoServer daoServer = new MonitorUserDaoServer(MybatisConfig.Servers.GBXM);

//        daoServer.delTwUserInfo("123");
//        System.out.println("delete success");


//        daoServer.addTwitterListern("123455567","测试name23");
//        daoServer.delTwitterListern("12345555");
        System.out.println(daoServer.isExits("23213213"));
        System.out.println("插入结束，请检验是否插入成功！！");
    }
}
