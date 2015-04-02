package zx.soft.api.adduser.demo;


import zx.soft.api.adduser.common.MybatisConfig;
import zx.soft.api.adduser.dao.server.MonitorUserDaoServer;
import zx.soft.api.domain.GplusApp;
import zx.soft.utils.json.JsonUtils;

/**
 * Created by jimbo on 15-3-31.
 */
public class MonitorGplusAppDemo {
    public static void main(String[] args) {
        MonitorUserDaoServer server = new MonitorUserDaoServer(MybatisConfig.Servers.GBXM);

        for(GplusApp app: server.getGplusApps()){
            System.out.println(JsonUtils.toJson(app));
        }
    }
}
