package com.jisbruzzi;

import com.jisbruzzi.config.MyBankApplicationConfiguration;
import jakarta.servlet.ServletContext;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Optional;

public class ApplicationLauncher {
	public static void main(String[] args) throws LifecycleException {

		Tomcat tomcat = new Tomcat();
		Optional<Integer> cmdPort=Optional.ofNullable(System.getProperty("server.port")).map(Integer::parseInt);
		tomcat.setPort(cmdPort.orElse(8080));
		tomcat.getConnector();

		Context tomcatCtx = tomcat.addContext("", null);

		WebApplicationContext appCtx= createApplicationContext(tomcatCtx.getServletContext());
		DispatcherServlet dispatcherServlet = new DispatcherServlet(appCtx);
		Wrapper servlet = Tomcat.addServlet(tomcatCtx, "dispatcherServlet", dispatcherServlet);
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/*");

		tomcat.start();
	}

	private static WebApplicationContext createApplicationContext(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(MyBankApplicationConfiguration.class);
		ctx.setServletContext(servletContext);
		ctx.refresh();
		ctx.registerShutdownHook();
		return ctx;
	}
}