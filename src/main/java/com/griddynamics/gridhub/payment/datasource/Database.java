package com.griddynamics.gridhub.payment.datasource;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Getter
@Component
@AllArgsConstructor
public class Database {

    private HikariDataSource dataSource;

    @SneakyThrows
    public Connection getConnection() {
        return dataSource.getConnection();
    }

}

