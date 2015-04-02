package zx.soft.api.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import zx.soft.model.oauth.OauthUserFacebook;
import zx.soft.model.oauth.OauthUserGooglePlus;
import zx.soft.model.oauth.OauthUserTwitter;

/**
 * 授权Mapper接口
 *
 * @author wanggang
 *
 */
public interface OauthMapper {

	/**
	 * Twitter授权CURD
	 */
	@Insert("INSERT INTO `oauth_user_twitter` (`username`,`password`,`consumer_key`,`consumer_secret`,"
			+ "`access_token`,`token_secret`,`since_id`,`lasttime`) VALUES (#{username},#{password},"
			+ "#{consumer_key},#{consumer_secret},#{access_token},#{token_secret},#{since_id},NOW())")
	public void insertOauthUserTW(OauthUserTwitter user);

	@Delete("DELETE FROM `oauth_user_twitter` WHERE `username` = #{0}")
	public void deleteOauthUserTW(String username);

	@Select("SELECT `username`,`password`,`consumer_key`,`consumer_secret`,`access_token`,`token_secret`,`since_id`"
			+ " FROM `oauth_user_twitter` WHERE `username` = #{0}")
	public OauthUserTwitter selectOauthUserTW(String username);

	public long selectOauthUserTWSinceId(String username);

	@Update("UPDATE `oauth_user_twitter` SET `since_id` = #{1} WHERE `username` = #{0}")
	public void updateOauthUserTWSinceId(String username, long sinceId);

	/**
	 * Facebook授权
	 */
	@Insert("INSERT INTO `oauth_user_facebook` (`username`,`password`,`consumer_key`,`consumer_secret`,"
			+ "`access_token`,`expires_at`,`lasttime`) VALUES (#{username},#{password},#{consumer_key},"
			+ "#{consumer_secret},#{access_token},#{expires_at},NOW())")
	public void insertOauthUserFB(OauthUserFacebook user);

	@Delete("DELETE FROM `oauth_user_facebook` WHERE `username` = #{0}")
	public void deleteOauthUserFB(String username);

	@Select("SELECT `username`,`password`,`consumer_key`,`consumer_secret`,`access_token`,`expires_at`"
			+ " FROM `oauth_user_facebook` WHERE `username` = #{0}")
	public OauthUserFacebook selectOauthUserFB(String username);

	/**
	 * Google+授权
	 */
	@Insert("INSERT INTO `oauth_user_googleplus` (`username`,`password`,`app_name`,`client_id`,`client_secret`,"
			+ "`lasttime`) VALUES (#{username},#{password},#{app_name},#{client_id},#{client_secret},NOW())")
	public void insertOauthUserGP(OauthUserGooglePlus user);

	@Delete("DELETE FROM `oauth_user_googleplus` WHERE `username` = #{0}")
	public void deleteOauthUserGP(String username);

	@Select("SELECT `username`,`password`,`app_name`,`client_id`,`client_secret` FROM `oauth_user_googleplus`"
			+ " WHERE `username` = #{0}")
	public OauthUserGooglePlus selectOauthUserGP(String username);

}
