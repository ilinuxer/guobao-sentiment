package zx.soft.model.status;

import java.io.Serializable;

/**
 * Google+用户状态信息，原始数据类型
 *
 * @author wanggang
 *
 */
public class GooglePlusStatus implements Serializable {

	private static final long serialVersionUID = 4940776865574014605L;

	private String id; // 此动态的ID
	private String title; // 此动态的标题
	private String published; // 此动态最初发布的时间,2015-03-04 15:26:19
	private String updated; // 此动态最后一次更新的时间,2015-03-04 15:26:19
	private String url; // 指向此动态的链接
	private String actor_id; // 执行人的个人资源ID
	private String actor_display_name; // 执行人昵称
	private String object_id; // 对象的 ID。转发动态时，它就是转发的动态的ID
	private String object_actor_id; // 原始执行人的ID
	private String object_actor_display_name; // 原始执行人昵称（适合用于显示）
	private String object_original_content; // 作者提供的内容（文字），此储存内容不带任何 HTML 格式。创建或更新动态时，必须在请求中以纯文本的形式提供此值
	private String object_url; // 指向链接资源的网址
	private int object_replies_totalitems; // 此动态的评论总数
	private int object_plusoners_totalitems; // 对此动态执行 +1 操作的人员总数
	private int object_resharers_totalitems; // 转发此动态的人员总数
	private String object_attachments_content; // 附件文本内容
	private String annotation; // 动态分享者额外添加的内容（仅在转发动态时可用）
	private double latitude; // 纬度
	private double longitude; // 经度
	private String place_name; // 此动态发生地点的名称

	public GooglePlusStatus() {
		//
	}

	public GooglePlusStatus(Builder builder) {
		this.id = builder.id;
		this.title = builder.title;
		this.published = builder.published;
		this.updated = builder.updated;
		this.url = builder.url;
		this.actor_id = builder.actor_id;
		this.actor_display_name = builder.actor_display_name;
		this.object_id = builder.object_id;
		this.object_actor_id = builder.object_actor_id;
		this.object_actor_display_name = builder.object_actor_display_name;
		this.object_original_content = builder.object_original_content;
		this.object_url = builder.object_url;
		this.object_replies_totalitems = builder.object_replies_totalitems;
		this.object_plusoners_totalitems = builder.object_plusoners_totalitems;
		this.object_resharers_totalitems = builder.object_resharers_totalitems;
		this.object_attachments_content = builder.object_attachments_content;
		this.annotation = builder.annotation;
		this.latitude = builder.latitude;
		this.longitude = builder.longitude;
		this.place_name = builder.place_name;
	}

	@Override
	public String toString() {
		return "GooglePlusStatus [id=" + id + ", title=" + title + ", published=" + published + ", updated=" + updated
				+ ", url=" + url + ", actor_id=" + actor_id + ", actor_display_name=" + actor_display_name
				+ ", object_id=" + object_id + ", object_actor_id=" + object_actor_id + ", object_actor_display_name="
				+ object_actor_display_name + ", object_original_content=" + object_original_content + ", object_url="
				+ object_url + ", object_replies_totalitems=" + object_replies_totalitems
				+ ", object_plusoners_totalitems=" + object_plusoners_totalitems + ", object_resharers_totalitems="
				+ object_resharers_totalitems + ", object_attachments_content=" + object_attachments_content
				+ ", annotation=" + annotation + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", place_name=" + place_name + "]";
	}

	public static class Builder {

		private String id;
		private String actor_id;
		private String actor_display_name;
		private String title = "";
		private String published = "2000-01-01 15:26:19";
		private String updated = "2000-01-01 15:26:19";
		private String url = "";
		private String object_id = "";
		private String object_actor_id = "";
		private String object_actor_display_name = "";
		private String object_original_content = "";
		private String object_url = "";
		private int object_replies_totalitems;
		private int object_plusoners_totalitems;
		private int object_resharers_totalitems;
		private String object_attachments_content = "";
		private String annotation = "";
		private double latitude;
		private double longitude;
		private String place_name = "";

		public Builder(String id, String actor_id, String actor_display_name) {
			this.id = id;
			this.actor_id = actor_id;
			this.actor_display_name = actor_display_name;
		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setPublished(String published) {
			this.published = published;
			return this;
		}

		public Builder setUpdated(String updated) {
			this.updated = updated;
			return this;
		}

		public Builder setUrl(String url) {
			this.url = url;
			return this;
		}

		public Builder setObject_id(String object_id) {
			this.object_id = object_id;
			return this;
		}

		public Builder setObject_actor_id(String object_actor_id) {
			this.object_actor_id = object_actor_id;
			return this;
		}

		public Builder setObject_actor_display_name(String object_actor_display_name) {
			this.object_actor_display_name = object_actor_display_name;
			return this;
		}

		public Builder setObject_original_content(String object_original_content) {
			this.object_original_content = object_original_content;
			return this;
		}

		public Builder setObject_url(String object_url) {
			this.object_url = object_url;
			return this;
		}

		public Builder setObject_replies_totalitems(int object_replies_totalitems) {
			this.object_replies_totalitems = object_replies_totalitems;
			return this;
		}

		public Builder setObject_plusoners_totalitems(int object_plusoners_totalitems) {
			this.object_plusoners_totalitems = object_plusoners_totalitems;
			return this;
		}

		public Builder setObject_resharers_totalitems(int object_resharers_totalitems) {
			this.object_resharers_totalitems = object_resharers_totalitems;
			return this;
		}

		public Builder setObject_attachments_content(String object_attachments_content) {
			this.object_attachments_content = object_attachments_content;
			return this;
		}

		public Builder setAnnotation(String annotation) {
			this.annotation = annotation;
			return this;
		}

		public Builder setLatitude(double latitude) {
			this.latitude = latitude;
			return this;
		}

		public Builder setLongitude(double longitude) {
			this.longitude = longitude;
			return this;
		}

		public Builder setPlace_name(String place_name) {
			this.place_name = place_name;
			return this;
		}

		public GooglePlusStatus build() {
			return new GooglePlusStatus(this);
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		this.published = published;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getActor_id() {
		return actor_id;
	}

	public void setActor_id(String actor_id) {
		this.actor_id = actor_id;
	}

	public String getActor_display_name() {
		return actor_display_name;
	}

	public void setActor_display_name(String actor_display_name) {
		this.actor_display_name = actor_display_name;
	}

	public String getObject_id() {
		return object_id;
	}

	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}

	public String getObject_actor_id() {
		return object_actor_id;
	}

	public void setObject_actor_id(String object_actor_id) {
		this.object_actor_id = object_actor_id;
	}

	public String getObject_actor_display_name() {
		return object_actor_display_name;
	}

	public void setObject_actor_display_name(String object_actor_display_name) {
		this.object_actor_display_name = object_actor_display_name;
	}

	public String getObject_original_content() {
		return object_original_content;
	}

	public void setObject_original_content(String object_original_content) {
		this.object_original_content = object_original_content;
	}

	public String getObject_url() {
		return object_url;
	}

	public void setObject_url(String object_url) {
		this.object_url = object_url;
	}

	public int getObject_replies_totalitems() {
		return object_replies_totalitems;
	}

	public void setObject_replies_totalitems(int object_replies_totalitems) {
		this.object_replies_totalitems = object_replies_totalitems;
	}

	public int getObject_plusoners_totalitems() {
		return object_plusoners_totalitems;
	}

	public void setObject_plusoners_totalitems(int object_plusoners_totalitems) {
		this.object_plusoners_totalitems = object_plusoners_totalitems;
	}

	public int getObject_resharers_totalitems() {
		return object_resharers_totalitems;
	}

	public void setObject_resharers_totalitems(int object_resharers_totalitems) {
		this.object_resharers_totalitems = object_resharers_totalitems;
	}

	public String getObject_attachments_content() {
		return object_attachments_content;
	}

	public void setObject_attachments_content(String object_attachments_content) {
		this.object_attachments_content = object_attachments_content;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getPlace_name() {
		return place_name;
	}

	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}

}
