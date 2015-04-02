package zx.soft.api.persist;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.utils.config.ConfigUtil;
import zx.soft.utils.log.LogbackUtil;

/**
 * 舆情数据JDBC
 *
 * @author wanggang
 *
 */
public class SentDbcp {

	private static Logger logger = LoggerFactory.getLogger(SentDbcp.class);

	private BasicDataSource dataSource;

	private String db_url;
	private String db_username;
	private String db_password;

	public SentDbcp() {
		dbInit();
		dbConnection();
	}

	/**
	 * 初始化数据库相关参数
	 */
	private void dbInit() {
		Properties props = ConfigUtil.getProps("db.properties");
		db_url = props.getProperty("guobao.jdbc.url");
		db_username = props.getProperty("guobao.jdbc.username");
		db_password = props.getProperty("guobao.jdbc.password");
	}

	/**
	 * 链接数据库
	 */
	private void dbConnection() {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(db_url);
		dataSource.setUsername(db_username);
		dataSource.setPassword(db_password);
		dataSource.setTestOnBorrow(true);
		dataSource.setValidationQuery("select 1");
	}

	/**
	 * 获取链接
	 */
	private Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			logger.error("Exception:{}", LogbackUtil.expection2Str(e));
			throw new RuntimeException(e);
		}
	}

	/**
	 * 关闭数据库
	 */
	public void close() {
		try {
			dataSource.close();
		} catch (SQLException e) {
			logger.error("Exception:{}", LogbackUtil.expection2Str(e));
			logger.info("Db close error.");
		}
	}

	@Override
	public String toString() {
		return "SentDbcp:[db_url=" + db_url + ",db_username=" + db_username + ",db_password=" + db_password + "]";
	}

	/**
	 * 创建舆情数据表
	 */
	public void createSentimentTable(String tablename) {
		String sql = "CREATE TABLE IF NOT EXISTS " + tablename + " ("
				+ "`rid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',"
				+ "`id` char(40) NOT NULL COMMENT '记录id，一般通过记录的url进行md5加密得到',"
				+ "`platform` smallint(5) unsigned NOT NULL COMMENT '平台类型，如：博客、微博、论坛等，用数字代替',"
				+ "`mid` varchar(50) NOT NULL COMMENT '主id'," + "`username` varchar(50) NOT NULL COMMENT '用户id',"
				+ "`nickname` varchar(50) NOT NULL COMMENT '用户昵称',"
				+ "`original_id` varchar(50) NOT NULL COMMENT '原创记录id',"
				+ "`original_uid` varchar(50) NOT NULL COMMENT '原创用户或者父用户id',"
				+ "`original_name` varchar(50) NOT NULL COMMENT '原创用户或者父用户昵称',"
				+ "`original_title` varchar(500) NOT NULL COMMENT '原创记录或者记录户标题',"
				+ "`original_url` varchar(500) NOT NULL COMMENT '原创记录或者父记录url',"
				+ "`url` varchar(500) NOT NULL COMMENT '该记录url',"
				+ "`home_url` varchar(500) NOT NULL COMMENT '用户首页url',"
				+ "`title` varchar(500) NOT NULL COMMENT '该记录标题'," + "`type` varchar(50) NOT NULL COMMENT '该记录所属类别',"
				+ "`isharmful` tinyint(4) NOT NULL COMMENT '该记录是否有害，0--无害，1--有害',"
				+ "`content` mediumtext NOT NULL COMMENT '该记录内容',"
				+ "`comment_count` mediumint(8) unsigned NOT NULL COMMENT '评论数',"
				+ "`read_count` mediumint(8) unsigned NOT NULL COMMENT '阅读数',"
				+ "`favorite_count` mediumint(8) unsigned NOT NULL COMMENT '收藏数',"
				+ "`attitude_count` mediumint(8) unsigned NOT NULL COMMENT '赞数',"
				+ "`repost_count` mediumint(8) unsigned NOT NULL COMMENT '转发数',"
				+ "`video_url` varchar(500) NOT NULL COMMENT '视频url',"
				+ "`pic_url` varchar(500) NOT NULL COMMENT '图片url',"
				+ "`voice_url` varchar(500) NOT NULL COMMENT '音频url',"
				+ "`timestamp` datetime NOT NULL COMMENT '该记录发布时间',"
				+ "`source_id` mediumint(9) unsigned NOT NULL COMMENT '来源网站名',"
				+ "`lasttime` datetime NOT NULL COMMENT '最新监测时间',"
				+ "`server_id` mediumint(9) unsigned NOT NULL COMMENT '来自前置机编号',"
				+ "`identify_id` int(11) unsigned NOT NULL COMMENT '标志id',"
				+ "`identify_md5` varchar(50) NOT NULL COMMENT '标志md5',"
				+ "`keyword` varchar(500) NOT NULL COMMENT '关键词'," + "`first_time` datetime NOT NULL COMMENT '首次发现时间',"
				+ "`update_time` datetime NOT NULL COMMENT '最新更新时间',"
				+ "`ip` varchar(20) NOT NULL COMMENT '该记录发布的ip地址',"
				+ "`location` varchar(100) NOT NULL COMMENT '该记录发布的区域地址',"
				+ "`geo` varchar(500) NOT NULL COMMENT '该记录发布的地理位置信息',"
				+ "`receive_addr` varchar(50) NOT NULL COMMENT '收件人地址',"
				+ "`append_addr` varchar(200) NOT NULL COMMENT '抄送人地址',"
				+ "`send_addr` varchar(50) NOT NULL COMMENT '发送人地址',"
				+ "`source_name` varchar(50) NOT NULL COMMENT '来源名称',"
				+ "`source_type` mediumint(9) unsigned NOT NULL COMMENT '来源类型',"
				+ "`country_code` mediumint(9) unsigned NOT NULL COMMENT '国家代码',"
				+ "`location_code` mediumint(9) unsigned NOT NULL COMMENT '区域编码',"
				+ "`province_code` mediumint(9) unsigned NOT NULL COMMENT '省份编码',"
				+ "`city_code` mediumint(9) unsigned NOT NULL COMMENT '城市编码',"
				+ "PRIMARY KEY (`rid`),UNIQUE KEY `id` (`id`), KEY `timestamp` (`timestamp`)) "
				+ "ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='舆情数据表' AUTO_INCREMENT=1 ;";
		execSQL(sql);
	}

	/**
	 * 执行sql语句
	 */
	private void execSQL(String sql) {
		try (Connection conn = getConnection(); Statement pstmt = conn.createStatement();) {
			pstmt.execute(sql);
		} catch (SQLException e) {
			logger.error("Exception:{}", LogbackUtil.expection2Str(e));
			throw new RuntimeException("SQLException: " + e);
		}
	}

}
