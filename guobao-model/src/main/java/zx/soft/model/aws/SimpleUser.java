package zx.soft.model.aws;

import java.io.Serializable;

/**
 * POST到AWS上的简单用户信息，即客户添加的监控用户id和用户名（昵称或其他）
 *
 * @author wanggang
 *
 */
public class SimpleUser implements Serializable {

	private static final long serialVersionUID = -2796802484012725455L;

	// 用户唯一id
	private String uid;
	// 用户名或昵称，必须唯一
	private String name;
	// 社交平台信息
	private String sns;

	public SimpleUser() {
		//
	}

	public SimpleUser(String uid, String name, String sns) {
		this.uid = uid;
		this.name = name;
		this.sns = sns;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSns() {
		return sns;
	}

	public void setSns(String sns) {
		this.sns = sns;
	}

}
