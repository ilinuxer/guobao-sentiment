package zx.soft.api.adduser.dao.server;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zx.soft.api.adduser.common.MybatisConfig;
import zx.soft.api.adduser.mapper.MonitorUserMapper;
import zx.soft.api.domain.*;
import zx.soft.model.aws.SimpleUser;
import zx.soft.model.user.CurrentUserInfo;

import java.util.LinkedList;
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
            monitor.addGplusListern(uid, name);
        }
    }

    /**
     * 添加Gplus用户基本信息到数据库
     */
    public void addGplusUserInfo(GplusUserInfos gplusUserInfos){
        logger.info("google+ user insert user information table");
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            monitor.addGplusUserInfo(gplusUserInfos);
        }
    }
    /**
     * 添加Twitter用户基本信息到数据库
     */
    public void addTwitterUserInfo(TwitterUserInfos twitterUserInfos){
        logger.info("添加Twitter用户到基本信息列表" );
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            monitor.addTwitterUserInfo(twitterUserInfos);
            logger.info("insert into user_twitter_info success ");
        }
    }

    /**
     * 删除监控用户列表中gplus用户信息
     */
    public void delGplusMonitorInfo(String id){
        logger.info("delete google+ monitor user " + id );
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            monitor.delGplusMonitorInfo(id);
        }
    }

    /**
     * 删除gplus用户相信信息列表中的用户信息
     */
    public void delGplusUserInfo(String id){
        logger.info("delete google+ user information "+ id);
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            monitor.delGplusUserInfo(id);
        }
    }

    /**
     * 删除Twitter 用户详细信息列表中的指定用户
     */
    public void delTwUserInfo(String id){
        logger.info("delete Twitter user information " + id);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            monitor.delTwUserInfo(id);
        }
    }
    /**
     * 添加Twitte监控用户信息到数据库
     */
    public void addTwitterListern(String userId,String userName,long sinceId){
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            monitor.addTwitterListern(userId, userName, sinceId);
        }
        logger.info("add monitor listern to " + userName + "success!");
    }

    /**
     * 删除Twitter 监控用户
     */
    public void delTwitterListern(String userId){
        logger.info("delete monitor to "+userId);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            monitor.delTwitterListern(userId);
        }
    }

    /**
     * 将新增用户插入新增用户信息列表
     */
    public void insertCurrentUser(CurrentUserInfo userInfo){
        logger.info("add "+userInfo.getUserId()+" into current_user ");
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            monitor.insertCurrentUser(userInfo);
        }
    }

    /**
     * 删除新增用户
     */
    public void deleteCurrentUser(String userId){
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            monitor.deleteCurrentUser(userId);
        }
    }

    /**
     * 获取当前新增用户列表
     */
    public List<CurrentUserInfo> getCurrentUsers(){
        List<CurrentUserInfo> result = new LinkedList<>();
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            result = monitor.getCurrentUsers();
        }
        return result;
    }

    /**
     * 判断current_user_info表中是否存在某用户
     */
    public boolean isExits(String userId){
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            Object object = monitor.isExits(userId);
            if (object == null){
                return false;
            }
            return true;
        }
    }

    /**
     * 新增tweet跟踪信息
     */
    public void insertStatus(SimpleStatus status){
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            monitor.insertStatus(status);
        }
        logger.info("add {} status focus success",status.getStatusId());
    }

    /**
     * 删除跟踪推文信息
     */
    public void delStatus(String statusId){
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            MonitorUserMapper monitor = sqlSession.getMapper(MonitorUserMapper.class);
            monitor.delStatus(statusId);
        }
        logger.info("delete {} status focused success",statusId);
    }
}
