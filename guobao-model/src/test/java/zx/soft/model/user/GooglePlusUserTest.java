package zx.soft.model.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GooglePlusUserTest {

	@Test
	public void testGooglePlusUser_为了验证字段是否已经设置了正确的值() {
		GooglePlusUser user = new GooglePlusUser.Builder("执行人的个人资源ID", "昵称").setName("姓名")
				.setUrl("http://www.google.com").setImage_url("http://www.image.url").build();
		assertEquals(
				"GooglePlusUser [id=执行人的个人资源ID, display_name=昵称, name=姓名, url=http://www.google.com, image_url=http://www.image.url]",
				user.toString());
		assertEquals("执行人的个人资源ID", user.getId());
		assertEquals("昵称", user.getDisplay_name());
		assertEquals("姓名", user.getName());
		assertEquals("http://www.google.com", user.getUrl());
		assertEquals("http://www.image.url", user.getImage_url());
	}

}
