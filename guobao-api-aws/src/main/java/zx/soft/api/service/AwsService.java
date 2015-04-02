package zx.soft.api.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import zx.soft.api.dao.SimpleUserMapper;
import zx.soft.api.dao.SnapShotMapper;
import zx.soft.model.aws.SimpleUser;
import zx.soft.model.aws.SnapShot;

@Service
public class AwsService {

	@Inject
	private SimpleUserMapper simpleUserMapperImpl;

	@Inject
	private SnapShotMapper snapShotMapperImpl;

	/**
	 * 插入单个用户
	 */
	public void insertSimpleUser(SimpleUser user) {
		simpleUserMapperImpl.insertSimpleUser(user);
	}

	/**
	 * 根据uid删除用户
	 */
	public void deleteSimpleUser(String uid) {
		simpleUserMapperImpl.deleteSimpleUser(uid);
	}

	/**
	 * 查询所有用户
	 */
	public List<SimpleUser> selectSimpleUsers() {
		return simpleUserMapperImpl.selectSimpleUsers();
	}

	/**
	 * 插入url和md5信息
	 */
	public void insertSnapShot(SnapShot snapShot) {
		snapShotMapperImpl.insertSnapShot(snapShot);
	}

	/**
	 * 查询html为空的url列表
	 */
	public List<String> selectSnapShotUrls() {
		return snapShotMapperImpl.selectSnapShotUrls();
	}

	/**
	 * 更新url快照信息
	 */
	public void updateSnapShot(SnapShot snapShot) {
		snapShotMapperImpl.updateSnapShot(snapShot);
	}

	/**
	 * 根据lasttime的过期时间删除
	 */
	public void deleteSnapShots(Date expire) {
		snapShotMapperImpl.deleteSnapShots(expire);
	}

	/**
	 * 根据url的md5查询html
	 */
	public String selectSnapShotHtml(String identify) {
		return snapShotMapperImpl.selectSnapShotHtml(identify);
	}

	/**
	 * 查询过后更新lasttime
	 */
	public void updateSnapShotLasttime(String identify) {
		snapShotMapperImpl.updateSnapShotLasttime(identify);
	}

}
