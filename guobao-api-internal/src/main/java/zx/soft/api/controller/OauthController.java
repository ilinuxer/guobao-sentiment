package zx.soft.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import zx.soft.api.domain.ErrorResponse;
import zx.soft.api.domain.PostResponse;
import zx.soft.api.service.CurdService;
import zx.soft.model.oauth.OauthUserFacebook;
import zx.soft.model.oauth.OauthUserGooglePlus;
import zx.soft.model.oauth.OauthUserTwitter;
import zx.soft.utils.log.LogbackUtil;

/**
 * 授权信息控制类
 * 包括：Twitter、Facebook、Google+
 * <h> type: 表示社交网络类型，如：tw,fb,gp</h>
 *
 * @author wanggang
 *
 */
@Controller
@RequestMapping("/oauth")
public class OauthController {

	private static final Logger logger = LoggerFactory.getLogger(OauthController.class);

	@Inject
	private CurdService curdService;

	/**
	 * 添加三个社交网站的授权用户信息
	 * @param sns 社交网站类型
	 * @param users 授权用户对象列表
	 * @return 未成功列表
	 */
	@RequestMapping(value = "/tw", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Object createOauthUsersTW(@RequestBody List<OauthUserTwitter> users) {
		List<String> unSuccess = new ArrayList<>();
		for (OauthUserTwitter user : users) {
			logger.info("Adding Twitter Oauth user:{}", user.getUsername());
			try {
				curdService.insertOauthUserTW(user);
			} catch (Exception e) {
				logger.error("Exception:{}", LogbackUtil.expection2Str(e));
				unSuccess.add(user.getUsername() + "");
			}
		}
		if (unSuccess.size() > 0) {
			return new PostResponse(-1, unSuccess);
		} else {
			return new PostResponse(0, null);
		}
	}

	@RequestMapping(value = "/fb", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Object createOauthUsersFB(@RequestBody List<OauthUserFacebook> users) {
		List<String> unSuccess = new ArrayList<>();
		for (OauthUserFacebook user : users) {
			logger.info("Adding Facebook Oauth user:{}", user.getUsername());
			try {
				curdService.insertOauthUserFB(user);
			} catch (Exception e) {
				logger.error("Exception:{}", LogbackUtil.expection2Str(e));
				unSuccess.add(user.getUsername() + "");
			}
		}
		if (unSuccess.size() > 0) {
			return new PostResponse(-1, unSuccess);
		} else {
			return new PostResponse(0, null);
		}
	}

	@RequestMapping(value = "/gp", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Object createOauthUsersGP(@RequestBody List<OauthUserGooglePlus> users) {
		List<String> unSuccess = new ArrayList<>();
		for (OauthUserGooglePlus user : users) {
			logger.info("Adding Google+ Oauth user:{}", user.getUsername());
			try {
				curdService.insertOauthUserGP(user);
			} catch (Exception e) {
				logger.error("Exception:{}", LogbackUtil.expection2Str(e));
				unSuccess.add(user.getUsername() + "");
			}
		}
		if (unSuccess.size() > 0) {
			return new PostResponse(-1, unSuccess);
		} else {
			return new PostResponse(0, null);
		}
	}

	/**
	 * 删除三个社交网站的授权用户信息
	 * @param sns 社交网站类型
	 * @param usernames 授权用户列表，逗号分割
	 * @return
	 */
	@RequestMapping(value = "/{sns}/{usernames}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody ErrorResponse deleteOauthUsers(@PathVariable String sns, @PathVariable String usernames) {
		for (String username : usernames.split(",")) {
			if ("tw".equalsIgnoreCase(sns)) {
				logger.info("Deleting Twitter Oauth user:{}", username);
				curdService.deleteOauthUserTW(username);
			} else if ("fb".equalsIgnoreCase(sns)) {
				logger.info("Deleting Facebook Oauth user:{}", username);
				curdService.deleteOauthUserFB(username);
			} else if ("gp".equalsIgnoreCase(sns)) {
				logger.info("Deleting Google+ Oauth user:{}", username);
				curdService.deleteOauthUserGP(username);
			} else {
				return new ErrorResponse.Builder(-1, "param `sns` is error").build();
			}
		}
		return new ErrorResponse.Builder(0, "ok").build();
	}

	/**
	 * 获取三个社交网站的用户授权信息
	 * @param sns 社交网站类型
	 * @param usernames 授权用户列表，逗号分割
	 * @return
	 */
	@RequestMapping(value = "/{sns}/{usernames}", method = RequestMethod.GET)
	public @ResponseBody Object retriveOauthUsers(@PathVariable String sns, @PathVariable String usernames) {
		if ("tw".equalsIgnoreCase(sns)) {
			logger.info("Querying Twitter Oauth users:{}", usernames);
			return curdService.selectOauthUsersTW(usernames);
		} else if ("fb".equalsIgnoreCase(sns)) {
			logger.info("Querying Facebook Oauth users:{}", usernames);
			return curdService.selectOauthUsersFB(usernames);
		} else if ("gp".equalsIgnoreCase(sns)) {
			logger.info("Querying Google+ Oauth users:{}", usernames);
			return curdService.selectOauthUsersGP(usernames);
		} else {
			return new ErrorResponse.Builder(-1, "param `sns` is error").build();
		}
	}

}
