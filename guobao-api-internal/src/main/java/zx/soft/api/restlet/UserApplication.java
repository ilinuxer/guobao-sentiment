package zx.soft.api.restlet;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class UserApplication extends Application {

	private UserInfo userInfo;

	public UserApplication() {
		userInfo = new UserInfo(MybatisConfig.ServerEnum.sentiment);
	}

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attach("/{sns}/{uid}", UserResource.class);
		return router;
	}

	public Object selectUser(String sns, String uid) {
		if ("tw".equalsIgnoreCase(sns)) {
			System.out.println("TW");
			return userInfo.selectTWUser(uid);
		} else if ("fb".equalsIgnoreCase(sns)) {
			return null;
		} else {
			System.out.println("GP");
			return userInfo.selectGPUser(uid);
		}
	}

}
