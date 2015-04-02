package zx.soft.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import zx.soft.api.dao.OauthMapper;
import zx.soft.api.dao.UserMapper;
import zx.soft.model.oauth.OauthUserFacebook;
import zx.soft.model.oauth.OauthUserGooglePlus;
import zx.soft.model.oauth.OauthUserTwitter;
import zx.soft.model.user.GooglePlusUser;
import zx.soft.model.user.TwitterUser;

/**
 * 授权数据、状态信息、用户基本信息CURD服务
 * 创建（Create）、更新（Update）、读取（Retrieve）和删除（Delete）
 *
 * @author wanggang
 *
 */
@Service
public class CurdService {

	@Inject
	private OauthMapper oauthMapperImpl;

	@Inject
	private UserMapper userMapperImpl;

	/**
	 * Twitter授权CURD
	 */
	public void insertOauthUserTW(OauthUserTwitter user) {
		oauthMapperImpl.insertOauthUserTW(user);
	}

	public void deleteOauthUserTW(String username) {
		oauthMapperImpl.deleteOauthUserTW(username);
	}

	public List<OauthUserTwitter> selectOauthUsersTW(String usernames) {
		List<OauthUserTwitter> result = new ArrayList<>();
		for (String username : usernames.split(",")) {
			result.add(selectOauthUserTW(username));
		}
		return result;
	}

	public OauthUserTwitter selectOauthUserTW(String username) {
		return oauthMapperImpl.selectOauthUserTW(username);
	}

	public long selectOauthUserTWSinceId(String username) {
		return oauthMapperImpl.selectOauthUserTWSinceId(username);
	}

	public void updateOauthUserTWSinceId(String username, long sinceId) {
		oauthMapperImpl.updateOauthUserTWSinceId(username, sinceId);
	}

	/**
	 * Facebook授权
	 */
	public void insertOauthUserFB(OauthUserFacebook user) {
		oauthMapperImpl.insertOauthUserFB(user);
	}

	public void deleteOauthUserFB(String username) {
		oauthMapperImpl.deleteOauthUserFB(username);
	}

	public List<OauthUserFacebook> selectOauthUsersFB(String usernames) {
		List<OauthUserFacebook> result = new ArrayList<>();
		for (String username : usernames.split(",")) {
			result.add(selectOauthUserFB(username));
		}
		return result;
	}

	public OauthUserFacebook selectOauthUserFB(String username) {
		return oauthMapperImpl.selectOauthUserFB(username);
	}

	/**
	 * Google+授权
	 */
	public void insertOauthUserGP(OauthUserGooglePlus user) {
		oauthMapperImpl.insertOauthUserGP(user);
	}

	public void deleteOauthUserGP(String username) {
		oauthMapperImpl.deleteOauthUserGP(username);
	}

	public List<OauthUserGooglePlus> selectOauthUsersGP(String usernames) {
		List<OauthUserGooglePlus> result = new ArrayList<>();
		for (String username : usernames.split(",")) {
			result.add(selectOauthUserGP(username));
		}
		return result;
	}

	public OauthUserGooglePlus selectOauthUserGP(String username) {
		return oauthMapperImpl.selectOauthUserGP(username);
	}

	/**
	 * Twitter用户信息
	 */
	public void insertUserInfoTW(TwitterUser user) {
		userMapperImpl.insertUserInfoTW(user);
	}

	public void deleteUserInfoTW(String uid) {
		userMapperImpl.deleteUserInfoTW(uid);
	}

	public List<TwitterUser> selectUserInfosTW(String uids) {
		List<TwitterUser> result = new ArrayList<>();
		for (String uid : uids.split(",")) {
			result.add(selectUserInfoTW(uid));
		}
		return result;
	}

	private TwitterUser selectUserInfoTW(String uid) {
		return userMapperImpl.selectUserInfoTW(uid);
	}

	/**
	 * Google+用户信息
	 */
	public void insertUserInfoGP(GooglePlusUser user) {
		userMapperImpl.insertUserInfoGP(user);
	}

	public void deleteUserInfoGP(String uid) {
		userMapperImpl.deleteUserInfoGP(uid);
	}

	public List<GooglePlusUser> selectUserInfosGP(String uids) {
		List<GooglePlusUser> result = new ArrayList<>();
		for (String uid : uids.split(",")) {
			result.add(selectUserInfoGP(uid));
		}
		return result;
	}

	private GooglePlusUser selectUserInfoGP(String uid) {
		return userMapperImpl.selectUserInfoGP(uid);
	}

}
