package zx.soft.model.aws;

/**
 * 根据Url得到的快照信息
 *
 * @author wanggang
 *
 */
public class SnapShot {

	// url的md5值
	private String identify;
	// 需要快照的url
	private String url;
	// 快照结果
	private String html;

	public SnapShot() {
		//
	}

	public SnapShot(String identify, String url, String html) {
		this.identify = identify;
		this.url = url;
		this.html = html;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

}
