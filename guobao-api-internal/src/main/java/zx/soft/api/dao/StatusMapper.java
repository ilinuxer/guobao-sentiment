package zx.soft.api.dao;

import org.apache.ibatis.annotations.Insert;

import zx.soft.model.sentiment.RecordInsert;

/**
 * 用户状态信息Mapper
 *
 * @author wanggang
 *
 */
public interface StatusMapper {

	/**
	 * 插入索引Record数据
	 */
	@Insert("INSERT INTO ${tablename} (`id`,`platform`,`mid`,`username`,`nickname`,`original_id`,`original_uid`,`original_name`,"
			+ "`original_title`,`original_url`,`url`,`home_url`,`title`,`type`,`isharmful`,`content`,`comment_count`,`read_count`,"
			+ "`favorite_count`,`attitude_count`,`repost_count`,`video_url`,`pic_url`,`voice_url`,`timestamp`,`source_id`,"
			+ "`lasttime`,`server_id`,`identify_id`,`identify_md5`,`keyword`,`first_time`,`update_time`,`ip`,`location`,"
			+ "`geo`,`receive_addr`,`append_addr`,`send_addr`,`source_name`,`source_type`,`country_code`,`location_code`,"
			+ "`province_code`,`city_code`) VALUES (#{id},#{platform},#{mid},#{username},#{nickname},#{original_id},#{original_uid},"
			+ "#{original_name},#{original_title},#{original_url},#{url},#{home_url},#{title},#{type},#{isharmful},#{content},"
			+ "#{comment_count},#{read_count},#{favorite_count},#{attitude_count},#{repost_count},#{video_url},#{pic_url},"
			+ "#{voice_url},#{timestamp},#{source_id},#{lasttime},#{server_id},#{identify_id},#{identify_md5},#{keyword},"
			+ "#{first_time},#{update_time},#{ip},#{location},#{geo},#{receive_addr},#{append_addr},#{send_addr},#{source_name},"
			+ "#{source_type},#{country_code},#{location_code},#{province_code},#{city_code});")
	public void insertRecord(RecordInsert recordInsert);

}
