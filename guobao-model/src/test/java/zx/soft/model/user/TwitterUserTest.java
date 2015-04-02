package zx.soft.model.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TwitterUserTest {

	@Test
	public void testTwitterUser_为了验证字段是否已经设置了正确的值() {
		TwitterUser user = new TwitterUser.Builder(123456789L, "用户姓名", "昵称")
				.setProfile_image_url("http://www.head.info").setCreated_at("2015-03-04 15:26:19")
				.setLocation("用户所在城市").setUrl("http://www.main.page").setFavourites_count(100).setUtc_offset(-3456)
				.setListed_count(200).setFollowers_count(300).setLang("母语或者主要语言").setDescription("描述信息")
				.setVerified(Boolean.TRUE).setTime_zone("时区").setStatuses_count(400).setFriends_count(500).build();
		assertEquals(
				"TwitterUser [id=123456789, name=用户姓名, screen_name=昵称, profile_image_url=http://www.head.info, created_at=2015-03-04 15:26:19, location=用户所在城市, url=http://www.main.page, favourites_count=100, utc_offset=-3456, listed_count=200, followers_count=300, lang=母语或者主要语言, description=描述信息, verified=true, time_zone=时区, statuses_count=400, friends_count=500]",
				user.toString());
		assertEquals(123456789L, user.getId());
		assertEquals("用户姓名", user.getName());
		assertEquals("昵称", user.getScreen_name());
		assertEquals("http://www.head.info", user.getProfile_image_url());
		assertEquals("2015-03-04 15:26:19", user.getCreated_at());
		assertEquals("用户所在城市", user.getLocation());
		assertEquals("http://www.main.page", user.getUrl());
		assertEquals(100, user.getFavourites_count());
		assertEquals(-3456, user.getUtc_offset());
		assertEquals(200, user.getListed_count());
		assertEquals(300, user.getFollowers_count());
		assertEquals("母语或者主要语言", user.getLang());
		assertEquals("描述信息", user.getDescription());
		assertEquals(Boolean.TRUE, user.isVerified());
		assertEquals("时区", user.getTime_zone());
		assertEquals(400, user.getStatuses_count());
		assertEquals(500, user.getFriends_count());
	}

}
