package zx.soft.api.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import zx.soft.model.aws.SnapShot;

/**
 * 页面快照Mapper
 * 主要用于页面信息取证
 *
 * @author wanggang
 *
 */
public interface SnapShotMapper {

	/**
	 * 插入url和md5信息
	 */
	@Insert("INSERT INTO `url_snapshot` (`identify`,`url`,`html`,`lasttime`) VALUES (#{identify},#{url},#{html},NOW())")
	public void insertSnapShot(SnapShot snapShot);

	/**
	 * 查询html为空的url列表，根据flag=0查找
	 */
	@Select("SELECT `url` FROM `url_snapshot` WHERE `flag` = '0'")
	public List<String> selectSnapShotUrls();

	/**
	 * 更新url快照信息
	 */
	@Update("UPDATE `url_snapshot` SET `html` = #{html}, `flag` = 1 , `lasttime` = NOW() WHERE `identify` = #{identify}")
	public void updateSnapShot(SnapShot snapShot);

	/**
	 * 根据lasttime的过期时间删除
	 */
	@Delete("DELETE FROM `url_snapshot` WHERE `lasttime` < #{0}")
	public void deleteSnapShots(Date expire);

	/**
	 * 根据url的md5查询html
	 */
	@Select("SELECT `html` FROM `url_snapshot` WHERE `identify` = #{identify}")
	public String selectSnapShotHtml(String identify);

	/**
	 * 查询过后更新lasttime
	 */
	@Update("UPDATE `url_snapshot` SET `lasttime` = NOW() WHERE `identify` = #{identify}")
	public void updateSnapShotLasttime(String identify);

}
