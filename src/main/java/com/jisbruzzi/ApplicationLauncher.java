package com.jisbruzzi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jisbruzzi.config.MyBankApplicationConfiguration;
import com.jisbruzzi.service.TransactionService;
import com.jisbruzzi.web.MyFirstServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ApplicationLauncher {
	public static void main(String[] args) throws LifecycleException {

		ApplicationContext context = new AnnotationConfigApplicationContext(MyBankApplicationConfiguration.class);

		Tomcat tomcat = new Tomcat();
		Optional<Integer> cmdPort=Optional.ofNullable(System.getProperty("server.port")).map(Integer::parseInt);
		tomcat.setPort(cmdPort.orElse(8080));
		tomcat.getConnector();

		Context ctx = tomcat.addContext("", null);
		Wrapper servlet = Tomcat.addServlet(ctx, "myFirstServlet", new MyFirstServlet(context.getBean(ObjectMapper.class), context.getBean(TransactionService.class)));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/*");

		tomcat.start();
	}
}