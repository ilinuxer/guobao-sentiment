package zx.soft.api.test.data.oauth;

import java.util.ArrayList;
import java.util.List;

import zx.soft.model.oauth.OauthUserGooglePlus;
import zx.soft.utils.json.JsonUtils;

public class OauthUserGooglePluss {

	public static void main(String[] args) {

		List<OauthUserGooglePlus> users = new ArrayList<>();
		OauthUserGooglePlus user1 = new OauthUserGooglePlus.Builder("googleplus-user1", "googleplus-user1-password")
				.setApp_name("googleplus-user1-app-name").setClient_id("googleplus-user1-client-id")
				.setClient_secret("googleplus-user1-client-secret").build();
		OauthUserGooglePlus user2 = new OauthUserGooglePlus.Builder("googleplus-user2", "googleplus-user2-password")
				.setApp_name("googleplus-user2-app-name").setClient_id("googleplus-user2-client-id")
				.setClient_secret("googleplus-user2-client-secret").build();
		users.add(user1);
		users.add(user2);
		System.out.println(JsonUtils.toJsonWithoutPretty(users));

	}

}
