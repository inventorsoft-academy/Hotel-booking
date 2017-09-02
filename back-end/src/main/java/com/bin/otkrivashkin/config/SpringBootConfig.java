package com.bin.otkrivashkin.config;

import com.bin.otkrivashkin.dao.DataBaseManagerImpl;
import com.bin.otkrivashkin.util.ConnectionManager;
import com.bin.otkrivashkin.util.DataManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@AllArgsConstructor
@Configuration
public class SpringBootConfig {

	@Autowired
	ConnectionManager connectionManager;

	@Bean
	public DataManager fileManager() {
		return new DataBaseManagerImpl(connectionManager);
	}

	@Bean
	@Primary
	public DataBaseManagerImpl getDBManager() {
		return new DataBaseManagerImpl(connectionManager);
	}

}
