package zx.soft.api.test.data.user;

import java.util.ArrayList;
import java.util.List;

import zx.soft.model.user.GooglePlusUser;
import zx.soft.utils.json.JsonUtils;

public class GooglePlusUsers {

	public static void main(String[] args) {
		List<GooglePlusUser> users = new ArrayList<>();
		GooglePlusUser user1 = new GooglePlusUser.Builder("执行人的个人资源ID1", "昵称1").setName("姓名1")
				.setUrl("http://www.google.com1").setImage_url("http://www.image.url1").build();
		GooglePlusUser user2 = new GooglePlusUser.Builder("执行人的个人资源ID2", "昵称2").setName("姓名2")
				.setUrl("http://www.google.com2").setImage_url("http://www.image.url2").build();
		users.add(user1);
		users.add(user2);
		System.out.println(JsonUtils.toJsonWithoutPretty(users));
	}

}
