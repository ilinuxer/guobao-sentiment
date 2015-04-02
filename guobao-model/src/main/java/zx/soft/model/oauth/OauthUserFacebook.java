package zx.soft.model.oauth;

import java.io.Serializable;

/**
 * Facebook授权用户信息
 * 专门用来关注一批用户的：给后台管理员使用
 *
 * @author wanggang
 *
 */
public class OauthUserFacebook implements Serializable {

	private static final long serialVersionUID = 8678160828528000761L;

	// 用户帐号信息
	private String username;
	private String password;
	// 授权对应的App信息
	private String consumer_key;
	private String consumer_secret;
	// 生成token信息
	private String access_token;
	// 有效期,2015-03-04 15:26:19
	private String expires_at;

	public OauthUserFacebook() {
		//
	}

	public OauthUserFacebook(Builder builder) {
		this.username = builder.username;
		this.password = builder.password;
		this.consumer_key = builder.consumer_key;
		this.consumer_secret = builder.consumer_secret;
		this.access_token = builder.access_token;
		this.expires_at = builder.expires_at;
	}

	@Override
	public String toString() {
		return "OauthUserFacebook [username=" + username + ", password=" + password + ", consumer_key=" + consumer_key
				+ ", consumer_secret=" + consumer_secret + ", access_token=" + access_token + ", expires_at="
				+ expires_at + "]";
	}

	public static class Builder {

		private String username;
		private String password;
		private String consumer_key = "";
		private String consumer_secret = "";
		private String access_token = "";
		private String expires_at = "2000-01-01 15:26:19";

		public Builder(String username, String password) {
			this.username = username;
			this.password = password;
		}

		public Builder setConsumer_key(String consumer_key) {
			this.consumer_key = consumer_key;
			return this;
		}

		public Builder setConsumer_secret(String consumer_secret) {
			this.consumer_secret = consumer_secret;
			return this;
		}

		public Builder setAccess_token(String access_token) {
			this.access_token = access_token;
			return this;
		}

		public Builder setExpires_at(String expires_at) {
			this.expires_at = expires_at;
			return this;
		}

		public OauthUserFacebook build() {
			return new OauthUserFacebook(this);
		}

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConsumer_key() {
		return consumer_key;
	}

	public void setConsumer_key(String consumer_key) {
		this.consumer_key = consumer_key;
	}

	public String getConsumer_secret() {
		return consumer_secret;
	}

	public void setConsumer_secret(String consumer_secret) {
		this.consumer_secret = consumer_secret;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_at() {
		return expires_at;
	}

	public void setExpires_at(String expires_at) {
		this.expires_at = expires_at;
	}

}
