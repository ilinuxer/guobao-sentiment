package zx.soft.api.util;

/**
 * 数据持久化常量
 *
 * @author wanggang
 *
 */
public class PersistConstant {

	/**
	 * 授权数据表
	 */
	public static final String OAUTH_USER_TWITTER = "oauth_user_twitter";
	public static final String OAUTH_USER_FACEBOOK = "oauth_user_facebook";
	public static final String OAUTH_USER_GOOGLEPLUS = "oauth_user_googleplus";

	/**
	 * 用户基本信息表
	 */
	public static final String USER_INFO_TWITTER = "user_info_twitter";
	public static final String USER_INFO_FACEBOOK = "user_info_facebook";
	public static final String USER_INFO_GOOGLEPLUS = "user_info_googleplus";

	/**
	 * 用户状态信息相关
	 */
	public static final String STATUS_INFO_TWITTER_PREFIX = "status_info_twitter_";
	public static final String STATUS_INFO_FACEBOOK_PREFIX = "status_info_facebook_";
	public static final String STATUS_INFO_GOOGLEPLUS_PREFIX = "status_info_googleplus_";
	// 数据表个数
	public static final int STATUS_TABLE_COUNT = 32;

}
