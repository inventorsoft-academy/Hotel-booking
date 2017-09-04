package com.bin.otkrivashkin.util;

import com.bin.otkrivashkin.exception.DataManagerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@PropertySource("classpath:application.properties")
public class ConnectionManager {

	private LogManager log = LogManager.getLogger(ConnectionManager.class);

	@Value("${app.datasource.url}")
	private String URL;
	@Value("${app.datasource.username}")
	private String USER;
	@Value("${app.datasource.password}")
	private String PASSWORD;
	@Value("${app.datasource.driver}")
	private  String DRIVER;

	private Connection connection;

	@PostConstruct
	private void init() throws DataManagerException {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);

			ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
			populator.addScript(new ClassPathResource("schema.sql"));
			populator.populate(connection);

		} catch (ClassNotFoundException | SQLException e) {
			log.error(e.getMessage());
			throw new DataManagerException(e.getMessage());
		}
	}

	public Connection getConnection() {
		return connection;
	}

	@PreDestroy
	private void destoy() {
		try {
			connection.close();
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

}
