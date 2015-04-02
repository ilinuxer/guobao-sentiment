package zx.soft.api.restlet;

import java.util.Properties;

import org.restlet.Component;
import org.restlet.data.Protocol;

import zx.soft.utils.config.ConfigUtil;
import zx.soft.utils.jackson.ReplaceConvert;

/**
 * http://192.168.31.12:8900/user/{sns}/{uid}
 *
 * @author wanggang
 *
 */
public class UserServer {

	private final Component component;
	private final UserApplication application;

	private final int PORT;

	public UserServer() {
		Properties props = ConfigUtil.getProps("api.properties");
		PORT = Integer.parseInt(props.getProperty("api.port"));
		component = new Component();
		application = new UserApplication();
	}

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		UserServer specialServer = new UserServer();
		specialServer.start();
	}

	public void start() {
		component.getServers().add(Protocol.HTTP, PORT);
		try {
			component.getDefaultHost().attach("/user", application);
			ReplaceConvert.configureJacksonConverter();
			component.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void stop() {
		try {
			component.stop();
			application.stop();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
