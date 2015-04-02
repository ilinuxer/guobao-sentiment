package zx.soft.api.restlet;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.api.domain.ErrorResponse;
import zx.soft.utils.codec.URLCodecUtils;

public class UserResource extends ServerResource {

	private static Logger logger = LoggerFactory.getLogger(UserResource.class);

	private UserApplication application;

	private String sns = "";
	private String uid = "";

	@Override
	public void doInit() {
		logger.info("Request Url: " + URLCodecUtils.decoder(getReference().toString(), "utf-8") + ".");
		application = (UserApplication) getApplication();
		sns = (String) this.getRequest().getAttributes().get("sns");
		uid = (String) this.getRequest().getAttributes().get("uid");
	}

	@Get("json")
	public Object getSpecialResult() {
		if (sns == null || sns.length() == 0 || uid == null || uid.length() == 0) {
			logger.error("Params `sns` or `uid` is null.");
			return new ErrorResponse.Builder(-1, "params error!").build();
		}
		return application.selectUser(sns, uid);
	}

}
