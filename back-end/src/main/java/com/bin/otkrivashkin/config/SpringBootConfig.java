package com.bin.otkrivashkin.config;

import com.bin.otkrivashkin.util.DataBaseManagerImpl;
import com.bin.otkrivashkin.util.FileManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@AllArgsConstructor
@Configuration
public class SpringBootConfig {


	@Bean
	public FileManager fileManager() {
		return new DataBaseManagerImpl();
	}

	@Bean
	@Primary
	public DataBaseManagerImpl getDBManager(){
		return new DataBaseManagerImpl();
	}


}
