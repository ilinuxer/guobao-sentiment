package zx.soft.model.status;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TwitterStatusTest {

	@Test
	public void testTwitterStatus_查看各字段设置是否正确() {
		TwitterStatus status = new TwitterStatus.Builder(12345678987654321L, 123456789L, "昵称").setLatitude(-122.22366d)
				.setLongitude(16.112668d).setCreated_at("2000-01-01 15:26:19").setText("该微博创建时间").setRetweet_count(100)
				.setPossibly_sensitive(Boolean.TRUE).setLocation("所在城市信息").setRetweeted_id(98765432123456789L)
				.setRetweeted_user_id(987654321L).setRetweeted_screen_name("转发用户的昵称").build();
		assertEquals(
				"TwitterStatus [id=12345678987654321, user_id=123456789, screen_name=昵称, latitude=-122.22366, longitude=16.112668, created_at=2000-01-01 15:26:19, text=该微博创建时间, retweet_count=100, possibly_sensitive=true, location=所在城市信息, retweeted_id=98765432123456789, retweeted_user_id=987654321, retweeted_screen_name=转发用户的昵称]",
				status.toString());
		assertEquals(12345678987654321L, status.getId());
		assertEquals(123456789L, status.getUser_id());
		assertEquals("昵称", status.getScreen_name());
		assertEquals(-122.22366d, status.getLatitude(), 0.0d);
		assertEquals(16.112668d, status.getLongitude(), 0.0d);
		assertEquals("2000-01-01 15:26:19", status.getCreated_at());
		assertEquals("该微博创建时间", status.getText());
		assertEquals(100, status.getRetweet_count());
		assertEquals(Boolean.TRUE, status.isPossibly_sensitive());
		assertEquals("所在城市信息", status.getLocation());
		assertEquals(98765432123456789L, status.getRetweeted_id());
		assertEquals(987654321L, status.getRetweeted_user_id());
		assertEquals("转发用户的昵称", status.getRetweeted_screen_name());
	}

}
