package zx.soft.api.test.data.oauth;

import java.util.ArrayList;
import java.util.List;

import zx.soft.model.oauth.OauthUserFacebook;
import zx.soft.utils.json.JsonUtils;

public class OauthUserFacebooks {

	public static void main(String[] args) {

		List<OauthUserFacebook> users = new ArrayList<>();
		OauthUserFacebook user1 = new OauthUserFacebook.Builder("facebook-user1", "facebook-user1-password")
				.setConsumer_key("facebook-user1-consumer-key").setConsumer_secret("facebook-user1-consumer-secret")
				.setAccess_token("facebook-user1-access-token").setExpires_at("2015-03-04 15:26:19").build();
		OauthUserFacebook user2 = new OauthUserFacebook.Builder("facebook-user2", "facebook-user2-password")
				.setConsumer_key("facebook-user2-consumer-key").setConsumer_secret("facebook-user2-consumer-secret")
				.setAccess_token("facebook-user2-access-token").setExpires_at("2015-03-07 15:26:19").build();
		users.add(user1);
		users.add(user2);
		System.out.println(JsonUtils.toJsonWithoutPretty(users));

	}

}
