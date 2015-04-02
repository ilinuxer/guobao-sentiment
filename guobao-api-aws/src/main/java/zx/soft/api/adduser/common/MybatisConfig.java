package zx.soft.api.adduser.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jimbo on 15-3-31.
 */
public class MybatisConfig {
    private static Logger logger = LoggerFactory.getLogger(MybatisConfig.class);

    public enum Servers{
        GBXM
    }

    private static Map<Servers,SqlSessionFactory> factories = new HashMap<>();

    static {
        for(Servers server: Servers.values()){
            try(InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml")){
                factories.put(server,new SqlSessionFactoryBuilder().build(inputStream,server.name()));
            } catch (IOException e) {
                logger.error("error during reading mybaits-config.xml");
                throw new RuntimeException(e);
            }
        }
    }
    public static SqlSessionFactory getSqlSessionFactory(Servers server){
        return factories.get(server);
    }
}
