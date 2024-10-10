package com.healthhub.hospital.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.healthhub.hospital.dao.BenhNhanDAO;

@Configuration
public class AppConfig {
 
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/phongkham");
        dataSource.setUsername("root");
//        dataSource.setPassword("");
        return dataSource;
    }
 
    @Bean
    public BenhNhanDAO getBenhNhanDAO() {
        return new BenhNhanDAO(dataSource());
    }
}