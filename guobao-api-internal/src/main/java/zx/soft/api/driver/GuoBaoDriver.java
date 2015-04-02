package zx.soft.api.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.api.restlet.UserServer;
import zx.soft.api.server.ApiServer;

/**
 * 驱动类
 *
 * @author wanggang
 *
 */
public class GuoBaoDriver {

	private static Logger logger = LoggerFactory.getLogger(GuoBaoDriver.class);

	/**
	 * 主函数
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		//		int exitCode = -1;
		//		ProgramDriver pgd = new ProgramDriver();
		//		try {
		//			// 运行在hefei12(192.168.31.12)机器上
		//			pgd.addClass("apiServer", ApiServer.class, "国保项目接口服务");
		//			pgd.addClass("userServer", UserServer.class, "国保项目接口服务");
		//			pgd.driver(args);
		//			// Success
		//			exitCode = 0;
		//		} catch (Throwable e) {
		//			throw new RuntimeException(e);
		//		}
		if (args.length == 0) {
			System.err.println("Usage: Input <class-name>, eg: \n" + //
					"`apiServer` 国保项目接口服务\n" + //
					"`userServer` 国保项目用户接口服务");
			System.exit(-1);
		}
		String[] leftArgs = new String[args.length - 1];
		System.arraycopy(args, 1, leftArgs, 0, leftArgs.length);

		switch (args[0]) {
		case "apiServer":
			logger.info("国保项目接口服务");
			ApiServer.main(leftArgs);
			break;
		case "userServer":
			logger.info("国保项目用户接口服务");
			UserServer.main(leftArgs);
			break;
		default:
			return;
		}

	}

}
