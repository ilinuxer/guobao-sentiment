package zx.soft.api.test.data.user;

import java.util.ArrayList;
import java.util.List;

import zx.soft.model.user.TwitterUser;
import zx.soft.utils.json.JsonUtils;

public class TwitterUsers {

	public static void main(String[] args) {
		List<TwitterUser> users = new ArrayList<>();
		TwitterUser user1 = new TwitterUser.Builder(1234567891L, "用户姓名1", "昵称1")
				.setProfile_image_url("http://www.head.info1").setCreated_at("2000-01-01 15:26:19")
				.setLocation("用户所在城市1").setUrl("http://www.main.page1").setFavourites_count(1001).setUtc_offset(-34561)
				.setListed_count(2001).setFollowers_count(3001).setLang("母语或者主要语言1").setDescription("描述信息1")
				.setVerified(Boolean.TRUE).setTime_zone("时区1").setStatuses_count(4001).setFriends_count(5001).build();
		TwitterUser user2 = new TwitterUser.Builder(12345678922L, "用户姓名2", "昵称2")
				.setProfile_image_url("http://www.head.info2").setCreated_at("2000-01-01 15:26:19")
				.setLocation("用户所在城市2").setUrl("http://www.main.page2").setFavourites_count(1002).setUtc_offset(-34562)
				.setListed_count(2002).setFollowers_count(3002).setLang("母语或者主要语言2").setDescription("描述信息2")
				.setVerified(Boolean.TRUE).setTime_zone("时区2").setStatuses_count(4002).setFriends_count(5002).build();
		users.add(user1);
		users.add(user2);
		System.out.println(JsonUtils.toJsonWithoutPretty(users));
	}

}
