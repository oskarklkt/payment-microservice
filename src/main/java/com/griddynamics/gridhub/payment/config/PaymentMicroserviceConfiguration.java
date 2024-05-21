package com.griddynamics.gridhub.payment.config;

import com.griddynamics.gridhub.payment.datasource.Database;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentMicroserviceConfiguration {

    @Bean
    public Database database(@Value("${db.url}") String url,
                             @Value("${db.username}") String userName,
                             @Value("${db.password}") String password) {
        Database database = new Database();
        database.setUrl(url);
        database.setUsername(userName);
        database.setPassword(password);
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(database.getUrl());
        config.setUsername(database.getUsername());
        config.setPassword(database.getPassword());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        database.setDataSource(new HikariDataSource(config));
        return database;
    }
}
