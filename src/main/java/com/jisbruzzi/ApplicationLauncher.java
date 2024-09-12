package com.jisbruzzi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jisbruzzi.service.TransactionService;
import com.jisbruzzi.web.MyFirstServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ApplicationLauncher {
	public static void main(String[] args) throws LifecycleException {

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,true);
		mapper.setDateFormat(new StdDateFormat());
		TransactionService service = new TransactionService();

		Tomcat tomcat = new Tomcat();
		Optional<Integer> cmdPort=Optional.ofNullable(System.getProperty("server.port")).map(Integer::parseInt);
		tomcat.setPort(cmdPort.orElse(8080));
		tomcat.getConnector();

		Context ctx = tomcat.addContext("", null);
		Wrapper servlet = Tomcat.addServlet(ctx, "myFirstServlet", new MyFirstServlet(mapper, service));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/*");

		tomcat.start();
	}
}