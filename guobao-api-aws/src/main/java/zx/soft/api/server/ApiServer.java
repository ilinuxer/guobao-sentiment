package zx.soft.api.server;

import java.io.IOException;
import java.util.Properties;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 综合API接口服务
 *
 * @author wanggang
 *
 */
public class ApiServer {

	private static final Logger logger = LoggerFactory.getLogger(ApiServer.class);

	// 默认端口
	private static final int DEFAULT_PORT = 8888;
	// Context路径
	private static final String CONTEXT_PATH = "/";
	// Mapping路径
	private static final String MAPPING_PATH = "/*";

	/**
	 * 主函数
	 */
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.load(ApiServer.class.getClassLoader().getResourceAsStream("api.properties"));
		new ApiServer().startJetty(Integer.valueOf(props.getProperty("api.port", String.valueOf(DEFAULT_PORT))));
	}

	private void startJetty(int port) throws Exception {
		logger.info("Starting server at port {}", port);
		Server server = new Server(port);
		server.setHandler(getServletContextHandler(new XmlWebApplicationContext()));
		server.start();
		logger.info("Server started at port {}", port);
		server.join();
	}

	private static ServletContextHandler getServletContextHandler(WebApplicationContext context) throws IOException {
		ServletContextHandler contextHandler = new ServletContextHandler();
		// 错误处理句柄
		contextHandler.setErrorHandler(null);
		// Context路径
		contextHandler.setContextPath(CONTEXT_PATH);
		// 添加Servlet
		contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_PATH);
		// 添加事件监听
		contextHandler.addEventListener(new ContextLoaderListener(context));
		// 设置资源基本路径
		contextHandler.setResourceBase(new ClassPathResource("webapp").getURI().toString());
		return contextHandler;
	}
}