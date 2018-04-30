package com.fx.init;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Lo4jInit extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Lo4jInit.class);

	/* web容器初始化 */
	public void init() {
		/* 部署在tomcat下的绝对路径 */
		String path = this.getServletContext().getRealPath("/");
		/* 获取web.xml文件中name为log4j_init_path的value值 */
		String file = this.getInitParameter("log4j_init_path");

		if (file != null) {
			Properties prop = new Properties();
			String root = path;
			/* 设置文件输出的路径 */
			String root1 = root + "/logs/log.txt";
			String root2 = root + "logs/error.txt";

			System.out.println("root1:" + root1);
			System.out.println("root2:" + root2);
			
			try {
				prop.load(new FileInputStream(path + file)); // 加载log4j.properties
				prop.setProperty("log4j.appender.D.File", root1); // 设置日志文件的输出路径
				prop.setProperty("log4j.appender.E.File", root2); // 设置日志文件的输出路径
				PropertyConfigurator.configure(prop); // 加载配置项
			} catch (Exception e) {
				System.out.println("any exception??");
				logger.info("初始化log4j日志输入路径异常，请检查web.xml参数配置是否正常，异常发生在" + this.getClass().getName()
						+ "类的public void init()方法，异常的愿意是：" + e.getMessage(), e.fillInStackTrace());
			}
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
