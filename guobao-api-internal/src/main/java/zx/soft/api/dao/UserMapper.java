package zx.soft.api.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import zx.soft.model.user.GooglePlusUser;
import zx.soft.model.user.TwitterUser;

/**
 * 用户信息Mapper
 *
 * @author wanggang
 *
 */
public interface UserMapper {

	@Insert("INSERT INTO `user_info_twitter` (`id`,`name`,`screen_name`,`profile_image_url`,`created_at`,`location`,"
			+ "`url`,`favourites_count`,`utc_offset`,`listed_count`,`followers_count`,`lang`,`description`,`verified`,"
			+ "`time_zone`,`statuses_count`,`friends_count`,`lasttime`) VALUES (#{id},#{name},#{screen_name},#{profile_image_url},"
			+ "#{created_at},#{location},#{url},#{favourites_count},#{utc_offset},#{listed_count},#{followers_count},"
			+ "#{lang},#{description},#{verified},#{time_zone},#{statuses_count},#{friends_count},NOW())")
	public void insertUserInfoTW(TwitterUser user);

	@Delete("DELETE FROM `user_info_twitter` WHERE `id` = #{0}")
	public void deleteUserInfoTW(String uid);

	@Select("SELECT `id`,`name`,`screen_name`,`profile_image_url`,`created_at`,`location`,`url`,`favourites_count`,"
			+ "`utc_offset`,`listed_count`,`followers_count`,`lang`,`description`,`verified`,`time_zone`,`statuses_count`,"
			+ "`friends_count` FROM `user_info_twitter` WHERE `id` = #{0}")
	public TwitterUser selectUserInfoTW(String uid);

	@Insert("INSERT INTO `user_info_googleplus` (`id`,`display_name`,`name`,`url`,`image_url`,`lasttime`) "
			+ "VALUES (#{id},#{display_name},#{name},#{url},#{image_url},NOW())")
	public void insertUserInfoGP(GooglePlusUser user);

	@Delete("DELETE FROM `user_info_googleplus` WHERE `id` = #{0}")
	public void deleteUserInfoGP(String uid);

	@Select("SELECT `id`,`display_name`,`name`,`url`,`image_url` FROM `user_info_googleplus` WHERE `id` = #{0}")
	public GooglePlusUser selectUserInfoGP(String uid);

}
