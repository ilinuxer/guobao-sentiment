package zx.soft.model.user;

import java.io.Serializable;

/**
 * Twitter用户基本信息，原始数据类型
 *
 * @author wanggang
 *
 */
public class TwitterUser implements Serializable {

	private static final long serialVersionUID = -789601225937445231L;

	private long id; // 用户唯一id
	private String name; // 用户姓名
	private String screen_name; // 昵称
	private String profile_image_url; // 头像信息
	private String created_at; // 用户创建时间,2000-01-01 15:26:19
	private String location; // 用户所在城市
	private String url; // 用户主页地址
	private int favourites_count; // 收藏数
	private int utc_offset; // UTC时差
	private int listed_count; // 用户关注的公共列表数
	private int followers_count; // 粉丝数量
	private String lang; // 母语或者主要语言
	private String description; // 描述信息
	private boolean verified; // 是否验证
	private String time_zone; // 时区
	private int statuses_count; // 发布的状态数
	private int friends_count; // 关注数量

	public TwitterUser() {
		//
	}

	public TwitterUser(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.screen_name = builder.screen_name;
		this.profile_image_url = builder.profile_image_url;
		this.created_at = builder.created_at;
		this.location = builder.location;
		this.url = builder.url;
		this.favourites_count = builder.favourites_count;
		this.utc_offset = builder.utc_offset;
		this.listed_count = builder.listed_count;
		this.followers_count = builder.followers_count;
		this.lang = builder.lang;
		this.description = builder.description;
		this.verified = builder.verified;
		this.time_zone = builder.time_zone;
		this.statuses_count = builder.statuses_count;
		this.friends_count = builder.friends_count;
	}

	@Override
	public String toString() {
		return "TwitterUser [id=" + id + ", name=" + name + ", screen_name=" + screen_name + ", profile_image_url="
				+ profile_image_url + ", created_at=" + created_at + ", location=" + location + ", url=" + url
				+ ", favourites_count=" + favourites_count + ", utc_offset=" + utc_offset + ", listed_count="
				+ listed_count + ", followers_count=" + followers_count + ", lang=" + lang + ", description="
				+ description + ", verified=" + verified + ", time_zone=" + time_zone + ", statuses_count="
				+ statuses_count + ", friends_count=" + friends_count + "]";
	}

	public static class Builder {

		private long id;
		private String name;
		private String screen_name;
		private String profile_image_url = "";
		private String created_at = "2000-01-01 15:26:19";
		private String location = "";
		private String url = "";
		private int favourites_count;
		private int utc_offset;
		private int listed_count;
		private int followers_count;
		private String lang = "";
		private String description = "";
		private boolean verified = Boolean.FALSE;
		private String time_zone = "";
		private int statuses_count;
		private int friends_count;

		public Builder(long id, String name, String screen_name) {
			this.id = id;
			this.name = name;
			this.screen_name = screen_name;
		}

		public Builder setProfile_image_url(String profile_image_url) {
			this.profile_image_url = profile_image_url;
			return this;
		}

		public Builder setCreated_at(String created_at) {
			this.created_at = created_at;
			return this;
		}

		public Builder setLocation(String location) {
			this.location = location;
			return this;
		}

		public Builder setUrl(String url) {
			this.url = url;
			return this;
		}

		public Builder setFavourites_count(int favourites_count) {
			this.favourites_count = favourites_count;
			return this;
		}

		public Builder setUtc_offset(int utc_offset) {
			this.utc_offset = utc_offset;
			return this;
		}

		public Builder setListed_count(int listed_count) {
			this.listed_count = listed_count;
			return this;
		}

		public Builder setFollowers_count(int followers_count) {
			this.followers_count = followers_count;
			return this;
		}

		public Builder setLang(String lang) {
			this.lang = lang;
			return this;
		}

		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder setVerified(boolean verified) {
			this.verified = verified;
			return this;
		}

		public Builder setTime_zone(String time_zone) {
			this.time_zone = time_zone;
			return this;
		}

		public Builder setStatuses_count(int statuses_count) {
			this.statuses_count = statuses_count;
			return this;
		}

		public Builder setFriends_count(int friends_count) {
			this.friends_count = friends_count;
			return this;
		}

		public TwitterUser build() {
			return new TwitterUser(this);
		}

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScreen_name() {
		return screen_name;
	}

	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}

	public String getProfile_image_url() {
		return profile_image_url;
	}

	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getFavourites_count() {
		return favourites_count;
	}

	public void setFavourites_count(int favourites_count) {
		this.favourites_count = favourites_count;
	}

	public int getUtc_offset() {
		return utc_offset;
	}

	public void setUtc_offset(int utc_offset) {
		this.utc_offset = utc_offset;
	}

	public int getListed_count() {
		return listed_count;
	}

	public void setListed_count(int listed_count) {
		this.listed_count = listed_count;
	}

	public int getFollowers_count() {
		return followers_count;
	}

	public void setFollowers_count(int followers_count) {
		this.followers_count = followers_count;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getTime_zone() {
		return time_zone;
	}

	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}

	public int getStatuses_count() {
		return statuses_count;
	}

	public void setStatuses_count(int statuses_count) {
		this.statuses_count = statuses_count;
	}

	public int getFriends_count() {
		return friends_count;
	}

	public void setFriends_count(int friends_count) {
		this.friends_count = friends_count;
	}

}
