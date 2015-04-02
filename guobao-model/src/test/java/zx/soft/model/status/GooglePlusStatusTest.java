package zx.soft.model.status;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GooglePlusStatusTest {

	@Test
	public void testGooglePlusStatus_查看各字段设置是否正确() {
		GooglePlusStatus status = new GooglePlusStatus.Builder("此动态的ID", "执行人的个人资源ID", "执行人昵称").setTitle("此动态的标题")
				.setPublished("2000-01-01 15:26:19").setUpdated("2000-01-01 15:26:19").setUrl("指向此动态的链接")
				.setObject_id("对象的 ID。转发动态时，它就是转发的动态的ID").setObject_actor_id("原始执行人的ID")
				.setObject_actor_display_name("原始执行人昵称（适合用于显示）").setObject_original_content("作者提供的内容")
				.setObject_url("指向链接资源的网址").setObject_replies_totalitems(100).setObject_plusoners_totalitems(200)
				.setObject_resharers_totalitems(300).setObject_attachments_content("附件文本内容")
				.setAnnotation("动态分享者额外添加的内容").setLatitude(-122.59986d).setLongitude(63.155889d)
				.setPlace_name("此动态发生地点的名称").build();
		assertEquals(
				"GooglePlusStatus [id=此动态的ID, title=此动态的标题, published=2000-01-01 15:26:19, updated=2000-01-01 15:26:19, url=指向此动态的链接, actor_id=执行人的个人资源ID, actor_display_name=执行人昵称, object_id=对象的 ID。转发动态时，它就是转发的动态的ID, object_actor_id=原始执行人的ID, object_actor_display_name=原始执行人昵称（适合用于显示）, object_original_content=作者提供的内容, object_url=指向链接资源的网址, object_replies_totalitems=100, object_plusoners_totalitems=200, object_resharers_totalitems=300, object_attachments_content=附件文本内容, annotation=动态分享者额外添加的内容, latitude=-122.59986, longitude=63.155889, place_name=此动态发生地点的名称]",
				status.toString());
		assertEquals("此动态的ID", status.getId());
		assertEquals("执行人的个人资源ID", status.getActor_id());
		assertEquals("执行人昵称", status.getActor_display_name());
		assertEquals("此动态的标题", status.getTitle());
		assertEquals("2000-01-01 15:26:19", status.getPublished());
		assertEquals("2000-01-01 15:26:19", status.getUpdated());
		assertEquals("指向此动态的链接", status.getUrl());
		assertEquals("对象的 ID。转发动态时，它就是转发的动态的ID", status.getObject_id());
		assertEquals("原始执行人的ID", status.getObject_actor_id());
		assertEquals("原始执行人昵称（适合用于显示）", status.getObject_actor_display_name());
		assertEquals("作者提供的内容", status.getObject_original_content());
		assertEquals("指向链接资源的网址", status.getObject_url());
		assertEquals(100, status.getObject_replies_totalitems());
		assertEquals(200, status.getObject_plusoners_totalitems());
		assertEquals(300, status.getObject_resharers_totalitems());
		assertEquals("附件文本内容", status.getObject_attachments_content());
		assertEquals("动态分享者额外添加的内容", status.getAnnotation());
		assertEquals(-122.59986d, status.getLatitude(), 0.0d);
		assertEquals(63.155889d, status.getLongitude(), 0.0d);
		assertEquals("此动态发生地点的名称", status.getPlace_name());
	}

}
