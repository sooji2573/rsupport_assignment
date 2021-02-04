package com.rsupport.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Profile("dev")
public class H2ServerConfiguration {

	@Bean
	@ConfigurationProperties("spring.datasource.hikari")
	public DataSource dataSource() throws SQLException {
		Server server = defaultRun();
		return new HikariDataSource();
	}
	
	private Server defaultRun() throws SQLException {
		return Server.createTcpServer(
				"-tcp",
				"-tcpAllowOthers",
				"-tcpPort", 9002 + "").start();
	}
}
