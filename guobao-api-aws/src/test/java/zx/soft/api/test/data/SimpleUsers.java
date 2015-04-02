package zx.soft.api.test.data;

import java.util.ArrayList;
import java.util.List;

import zx.soft.model.aws.SimpleUser;
import zx.soft.utils.json.JsonUtils;

public class SimpleUsers {

	public static void main(String[] args) {
		List<SimpleUser> users = new ArrayList<>();
		SimpleUser user1 = new SimpleUser("twitter-id", "twitter-name", "tw");
		SimpleUser user2 = new SimpleUser("facebook-id", "facebook-name", "fb");
		SimpleUser user3 = new SimpleUser("googleplus-id", "googleplus-name", "gp");
		users.add(user1);
		users.add(user2);
		users.add(user3);
		System.out.println(JsonUtils.toJsonWithoutPretty(users));

	}

}
