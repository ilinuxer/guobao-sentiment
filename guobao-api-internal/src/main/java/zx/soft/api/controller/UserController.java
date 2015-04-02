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
import zx.soft.model.user.GooglePlusUser;
import zx.soft.model.user.TwitterUser;
import zx.soft.utils.log.LogbackUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Inject
	private CurdService curdService;

	/**
	 * 添加三个社交网站的用户信息
	 * @param sns 社交网站类型
	 * @param users 用户信息对象列表
	 * @return 未成功列表
	 */
	@RequestMapping(value = "/tw", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Object createUserInfosTW(@RequestBody List<TwitterUser> users) {
		List<String> unSuccess = new ArrayList<>();
		for (TwitterUser user : users) {
			logger.info("Adding Twitter Monitor user:{}", user.getId());
			try {
				curdService.insertUserInfoTW(user);
			} catch (Exception e) {
				logger.error("Exception:{}", LogbackUtil.expection2Str(e));
				unSuccess.add(user.getId() + "");
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
	public @ResponseBody Object createUserInfosFB(@RequestBody Object users) {
		// TODO
		return "no data!";
	}

	@RequestMapping(value = "/gp", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Object createUserInfosGP(@RequestBody List<GooglePlusUser> users) {
		List<String> unSuccess = new ArrayList<>();
		for (GooglePlusUser user : users) {
			logger.info("Adding Google+ Monitor user:{}", user.getId());
			try {
				curdService.insertUserInfoGP(user);
			} catch (Exception e) {
				logger.error("Exception:{}", LogbackUtil.expection2Str(e));
				unSuccess.add(user.getId() + "");
			}
		}
		if (unSuccess.size() > 0) {
			return new PostResponse(-1, unSuccess);
		} else {
			return new PostResponse(0, null);
		}
	}

	/**
	 * 删除三个社交网站的用户信息
	 * @param sns 社交网站类型
	 * @param uids 用户id列表，逗号分割
	 * @return
	 */
	@RequestMapping(value = "/{sns}/{uids}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody ErrorResponse deleteUserInfos(@PathVariable String sns, @PathVariable String uids) {
		for (String uid : uids.split(",")) {
			if ("tw".equalsIgnoreCase(sns)) {
				logger.info("Deleting Twitter Monitor user:{}", uid);
				curdService.deleteUserInfoTW(uid);
			} else if ("fb".equalsIgnoreCase(sns)) {
				// TODO
			} else if ("gp".equalsIgnoreCase(sns)) {
				logger.info("Deleting Google+ Monitor user:{}", uid);
				curdService.deleteUserInfoGP(uid);
			} else {
				return new ErrorResponse.Builder(-1, "param `sns` is error").build();
			}
		}
		return new ErrorResponse.Builder(0, "ok").build();
	}

	/**
	 * 获取三个社交网站的用户信息
	 * @param sns 社交网站类型
	 * @param uids 用户id列表，逗号分割
	 * @return
	 */
	@RequestMapping(value = "/{sns}/{uids}", method = RequestMethod.GET)
	public @ResponseBody Object retriveUserInfos(@PathVariable String sns, @PathVariable String uids) {
		if ("tw".equalsIgnoreCase(sns)) {
			logger.info("Querying Twitter Monitor users:{}", uids);
			return curdService.selectUserInfosTW(uids);
		} else if ("fb".equalsIgnoreCase(sns)) {
			// TODO
			return null;
		} else if ("gp".equalsIgnoreCase(sns)) {
			logger.info("Querying Google+ Monitor users:{}", uids);
			return curdService.selectUserInfosGP(uids);
		} else {
			return new ErrorResponse.Builder(-1, "param `sns` is error").build();
		}
	}

}
