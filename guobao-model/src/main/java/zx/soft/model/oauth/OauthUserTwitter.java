package zx.soft.model.oauth;

import java.io.Serializable;

/**
 * Twitter授权用户信息
 * 专门用来关注一批用户的：给后台管理员使用
 *
 * @author wanggang
 *
 */
public class OauthUserTwitter implements Serializable {

	private static final long serialVersionUID = -5590279923792384678L;

	// 用户帐号信息
	private String username;
	private String password;
	// 授权对应的App信息
	private String consumer_key;
	private String consumer_secret;
	// 生成的token信息
	private String access_token;
	private String token_secret;
	// SinceID，为了记录上次读取twitter文的位置
	private long since_id;

	public OauthUserTwitter() {
		//
	}

	public OauthUserTwitter(Builder builder) {
		this.username = builder.username;
		this.password = builder.password;
		this.consumer_key = builder.consumer_key;
		this.consumer_secret = builder.consumer_secret;
		this.access_token = builder.access_token;
		this.token_secret = builder.token_secret;
		this.since_id = builder.since_id;
	}

	@Override
	public String toString() {
		return "OauthUserTwitter [username=" + username + ", password=" + password + ", consumer_key=" + consumer_key
				+ ", consumer_secret=" + consumer_secret + ", access_token=" + access_token + ", token_secret="
				+ token_secret + ", since_id=" + since_id + "]";
	}

	public static class Builder {

		private String username;
		private String password;
		private String consumer_key = "";
		private String consumer_secret = "";
		private String access_token = "";
		private String token_secret = "";
		private long since_id;

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

		public Builder setToken_secret(String token_secret) {
			this.token_secret = token_secret;
			return this;
		}

		public Builder setSince_id(long since_id) {
			this.since_id = since_id;
			return this;
		}

		public OauthUserTwitter build() {
			return new OauthUserTwitter(this);
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

	public String getToken_secret() {
		return token_secret;
	}

	public void setToken_secret(String token_secret) {
		this.token_secret = token_secret;
	}

	public long getSince_id() {
		return since_id;
	}

	public void setSince_id(long since_id) {
		this.since_id = since_id;
	}

}
