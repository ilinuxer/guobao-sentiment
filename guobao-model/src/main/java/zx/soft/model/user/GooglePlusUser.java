package zx.soft.model.user;

import java.io.Serializable;

/**
 * Google+用户基本信息，原始数据类型
 *
 * @author wanggang
 *
 */
public class GooglePlusUser implements Serializable {

	private static final long serialVersionUID = -5180615207616583984L;

	private String id; // 执行人的个人资源ID
	private String display_name; // 昵称
	private String name; // 姓名
	private String url; // 指向执行人的Google个人资料的链接，即主页地址
	private String image_url; // 头像地址

	public GooglePlusUser() {
		//
	}

	public GooglePlusUser(Builder builder) {
		this.id = builder.id;
		this.display_name = builder.display_name;
		this.name = builder.name;
		this.url = builder.url;
		this.image_url = builder.image_url;
	}

	@Override
	public String toString() {
		return "GooglePlusUser [id=" + id + ", display_name=" + display_name + ", name=" + name + ", url=" + url
				+ ", image_url=" + image_url + "]";
	}

	public static class Builder {

		private String id;
		private String display_name;
		private String name = "";
		private String url = "";
		private String image_url = "";

		public Builder(String id, String display_name) {
			this.id = id;
			this.display_name = display_name;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setUrl(String url) {
			this.url = url;
			return this;
		}

		public Builder setImage_url(String image_url) {
			this.image_url = image_url;
			return this;
		}

		public GooglePlusUser build() {
			return new GooglePlusUser(this);
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

}
