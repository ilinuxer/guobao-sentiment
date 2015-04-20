package zx.soft.api.adduser.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import zx.soft.api.domain.GplusApp;
import zx.soft.api.domain.GplusUserInfos;
import zx.soft.api.domain.TwitterToken;
import zx.soft.api.domain.TwitterUserInfos;
import zx.soft.model.aws.SimpleUser;

import java.util.List;

/**
 * Created by jimbo on 15-3-31.
 */
public interface MonitorUserMapper {

    /**
     * 数据库查询未添加用户
     * 根据所属社交网络类别
     */
    @Select("SELECT `uid`,`name`,`sns` FROM `monitor_user_add` WHERE `sns` = #{0}")
    public List<SimpleUser> selectUser(String type);

    /**
     * 数据库删除已添加用户
     * 根据用户名，用户所属社交网络类别
     */
    @Delete("DELETE FROM `monitor_user_add` WHERE `name` = #{0} AND `sns` = #{1}")
    public void deleteUser(String name, String type);

    /**
     * 数据库获取Twitter Token列表
     */
    @Select("SELECT `tokenkey`,`tokensecret` FROM `twitterTokens`")
    public List<TwitterToken> getTwitterTokens();

    /**
     * 数据库获取Gplus应用列表
     */
    @Select("SELECT `app_name` AS appName,`client_id` AS clientId,`client_secret` AS clientSecret FROM `gplusApps`")
    public List<GplusApp> getGplusApps();

    /**
     * 添加Gplus用户的ID与name到数据库
     * 作用是对用户进行监控
     */
    @Insert("INSERT INTO `googleUserInfos` (`userId`,`userName`,`lastUpdateTime`) VALUES (#{0},#{1},NOW())")
    public void addGplusListern(String uid, String name);

    /**
     * 添加Gplus用户基本信息到数据库
     */
    @Insert("INSERT INTO `user_info_googleplus` (`id`,`display_name`,`name`,`url`,`image_url`,`lastTime`) VALUES (#{id},#{display_name},#{display_name},#{url},#{image_url},NOW())")
    public void addGplusUserInfo(GplusUserInfos gplusUserInfos);

    /**
     * 添加Twitter用户基本信息到数据库
     */
    @Insert("INSERT INTO `user_info_twitter` (`id`,`name`,`screen_name`,`profile_image_url`," +
            "`created_at`,`location`,`url`,`favourites_count`,`utc_offset`,`listed_count`,`followers_count`," +
            "`lang`,`description`,`verified`,`time_zone`,`statuses_count`, `friends_count`,`lasttime`) "+
            "VALUES (#{id},#{name},#{screen_name},#{profile_image_url},#{created_at},#{location},#{url}," +
            "#{favourites_count},#{utc_offset},#{listed_count},#{followers_count},#{lang},#{description}," +
            "#{verified},#{time_zone},#{statuses_count},#{friends_count},NOW())")
    public void addTwitterUserInfo(TwitterUserInfos twitterUserInfos);

    /**
     * 删除google＋指定关注用户
     */
    @Delete("DELETE FROM `googleUserInfos` WHERE `userId` = #{0}")
    public void delGplusMonitorInfo(String id);


    /**
     * 删除gplus用户相信信息列表中的用户信息
     */
    @Delete("DELETE FROM `user_info_googleplus` WHERE `id` = #{0}")
    public void delGplusUserInfo(String id);

    /**
     * 删除Twitter用户详细信息列表中指定的用户
     */
    @Delete("DELETE FROM `user_info_twitter` WHERE `id` = #{0}")
    public void delTwUserInfo(String id);

}
