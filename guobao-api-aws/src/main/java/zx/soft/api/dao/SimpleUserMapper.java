package zx.soft.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import zx.soft.model.aws.SimpleUser;

/**
 * 监控用户信息Mapper
 *
 * @author wanggang
 *
 */
public interface SimpleUserMapper {

	/**
	 * 插入单个用户
	 */
	@Insert("INSERT INTO `monitor_user_add` (`uid`,`name`,`sns`,`lasttime`) VALUES (#{uid},#{name},#{sns},NOW())")
	public void insertSimpleUser(SimpleUser user);

	/**
	 * 根据uid删除用户
	 */
	@Delete("DELETE FROM `monitor_user_add` WHERE `uid` = #{0}")
	public void deleteSimpleUser(String uid);

	/**
	 * 查询所有用户
	 */
	@Select("SELECT `uid`,`name`,`sns` FROM `monitor_user_add`")
	public List<SimpleUser> selectSimpleUsers();

}
