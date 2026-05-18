package com.personalprojects.portfolio.config;

import javax.sql.DataSource;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConditionalOnProperty(prefix = "spring.datasource.pool-warmup", name = "enabled", havingValue = "true", matchIfMissing = true)
    public ApplicationRunner datasourceWarmup(DataSource dataSource) {
        return args -> {
            int warmupConnections = 2;
            List<Connection> connections = new ArrayList<>(warmupConnections);
            try {
                for (int i = 0; i < warmupConnections; i++) {
                    connections.add(dataSource.getConnection());
                }
            } finally {
                for (Connection connection : connections) {
                    if (connection != null) {
                        connection.close();
                    }
                }
            }
        };
    }
}
