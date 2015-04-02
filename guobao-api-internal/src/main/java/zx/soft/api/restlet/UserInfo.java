package zx.soft.api.restlet;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.api.dao.UserMapper;
import zx.soft.model.user.GooglePlusUser;
import zx.soft.model.user.TwitterUser;
import zx.soft.utils.log.LogbackUtil;

/**
 * OA首页查询信息
 *
 * @author wanggang
 *
 */
public class UserInfo {

	private static Logger logger = LoggerFactory.getLogger(UserInfo.class);

	private final SqlSessionFactory sqlSessionFactory;

	public UserInfo(MybatisConfig.ServerEnum server) {
		try {
			sqlSessionFactory = MybatisConfig.getSqlSessionFactory(server);
		} catch (RuntimeException e) {
			logger.error("Exception:{}", LogbackUtil.expection2Str(e));
			throw new RuntimeException(e);
		}
	}

	public TwitterUser selectTWUser(String uid) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			return mapper.selectUserInfoTW(uid);
		}
	}

	public GooglePlusUser selectGPUser(String uid) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			return mapper.selectUserInfoGP(uid);
		}
	}

}
