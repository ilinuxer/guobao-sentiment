package zx.soft.api.adduser.demo;

import zx.soft.api.adduser.common.MybatisConfig;
import zx.soft.api.adduser.dao.server.MonitorUserDaoServer;

/**
 * Created by jimbo on 4/17/15.
 */
public class TwitterUserDemo {
    public static void main(String[] args) {
        MonitorUserDaoServer daoServer = new MonitorUserDaoServer(MybatisConfig.Servers.GBXM);

        System.out.println(daoServer.isExits("23213213"));
        System.out.println("插入结束，请检验是否插入成功！！");
    }
}
