package zx.soft.api.test.data.oauth;

import java.util.ArrayList;
import java.util.List;

import zx.soft.model.oauth.OauthUserTwitter;
import zx.soft.utils.json.JsonUtils;

public class OauthUserTwitters {

	public static void main(String[] args) {

		List<OauthUserTwitter> users = new ArrayList<>();
		OauthUserTwitter user1 = new OauthUserTwitter.Builder("twitter-user1", "twitter-user1-password")
				.setConsumer_key("twitter-user1-consumer-key").setConsumer_secret("twitter-user1-consumer-secret")
				.setAccess_token("twitter-user1-access-token").setToken_secret("twitter-user1-token-secret").build();
		OauthUserTwitter user2 = new OauthUserTwitter.Builder("twitter-user2", "twitter-user2-password")
				.setConsumer_key("twitter-user2-consumer-key").setConsumer_secret("twitter-user2-consumer-secret")
				.setAccess_token("twitter-user2-access-token").setToken_secret("twitter-user2-token-secret")
				.setSince_id(123456789L).build();
		users.add(user1);
		users.add(user2);
		System.out.println(JsonUtils.toJsonWithoutPretty(users));

	}

}
