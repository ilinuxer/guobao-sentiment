package zx.soft.api.adduser.dao.server;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zx.soft.api.adduser.common.MybatisConfig;
import zx.soft.api.adduser.mapper.MonitorUserMapper;
import zx.soft.api.domain.GplusApp;
import zx.soft.api.domain.GplusUserInfos;
import zx.soft.api.domain.TwitterToken;
import zx.soft.api.domain.TwitterUserInfos;
import zx.soft.model.aws.SimpleUser;

import java.util.List;


/**
 * Created by jimbo on 15-3-31.
 */
public class MonitorUserDaoServer {
    private Logger logger = LoggerFactory.getLogger(MonitorUserDaoServer.class);
    private final SqlSessionFactory sqlSessionFactory;

    public MonitorUserDaoServer(MybatisConfig.Servers server) {
        try{
            sqlSessionFactory = MybatisConfig.getSqlSessionFactory(server);
        }catch (RuntimeException e){
            logger.error("SpecoalQuery RuntimeException:" + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 数据库查询未添加用户
     * 根据所属社交网络类别
     * tw–twitter,fb–facebook,gp–google+
     */
    public List<SimpleUser> selectUser(String type){
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            return monitor.selectUser(type);
        }
    }

    /**
     * 数据库删除已添加用户
     * 根据用户名和用户所属的社交网络类别
     */
    public void deleteUser(String name,String type){
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            monitor.deleteUser(name,type);
        }
    }

    /**
     * 数据库获取Twitter Token列表
     */
    public List<TwitterToken> getTwitterTokens(){
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            return monitor.getTwitterTokens();
        }
    }

    /**
     * 数据库获取Gplus应用列表
     */
    public List<GplusApp> getGplusApps(){
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            return monitor.getGplusApps();
        }
    }

    /**
     * 添加Gplus用户的ID与name到数据库
     * 作用是对用户进行监控
     */
    public void addGplusListern(String uid,String name){
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            monitor.addGplusListern(uid,name);
        }
    }

    /**
     * 添加Gplus用户基本信息到数据库
     */
    public void addGplusUserInfo(GplusUserInfos gplusUserInfos){
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            logger.info("开始插入用户信息详情");
            monitor.addGplusUserInfo(gplusUserInfos);
        }
    }
    /**
     * 添加Twitter用户基本信息到数据库
     */
    public void addTwitterUserInfo(TwitterUserInfos twitterUserInfos){
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            monitor.addTwitterUserInfo(twitterUserInfos);
        }
    }
}
