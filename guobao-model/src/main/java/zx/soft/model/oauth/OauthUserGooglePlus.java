package zx.soft.model.oauth;

import java.io.Serializable;

/**
 * GooglePlus授权用户信息
 * 专门用来关注一批用户的：给后台管理员使用
 *
 * @author wanggang
 *
 */
public class OauthUserGooglePlus implements Serializable {

	private static final long serialVersionUID = -1195814259051629059L;

	// 用户帐号信息
	private String username;
	private String password;
	// 自己的应用信息
	private String app_name;
	private String client_id;
	private String client_secret;

	public OauthUserGooglePlus() {
		//
	}

	public OauthUserGooglePlus(Builder builder) {
		this.username = builder.username;
		this.password = builder.password;
		this.app_name = builder.app_name;
		this.client_id = builder.client_id;
		this.client_secret = builder.client_secret;
	}

	@Override
	public String toString() {
		return "OauthUserGooglePlus [username=" + username + ", password=" + password + ", app_name=" + app_name
				+ ", client_id=" + client_id + ", client_secret=" + client_secret + "]";
	}

	public static class Builder {

		private String username;
		private String password;
		private String app_name = "";
		private String client_id = "";
		private String client_secret = "";

		public Builder(String username, String password) {
			this.username = username;
			this.password = password;
		}

		public Builder setApp_name(String app_name) {
			this.app_name = app_name;
			return this;
		}

		public Builder setClient_id(String client_id) {
			this.client_id = client_id;
			return this;
		}

		public Builder setClient_secret(String client_secret) {
			this.client_secret = client_secret;
			return this;
		}

		public OauthUserGooglePlus build() {
			return new OauthUserGooglePlus(this);
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

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

}
