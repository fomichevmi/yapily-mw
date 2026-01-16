package com.mif.interview.yapily;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfiguration {

  @Value("${POSTGRES_USER}")
  private String dbUser;
  @Value("${POSTGRES_PASSWORD}")
  private String dbPass;

  @Bean
  DataSource dataSource() {

    HikariConfig config = new HikariConfig();

    // JDBC connection settings
    config.setJdbcUrl("jdbc:postgresql://localhost:5432/yapily_db");
    config.setUsername(dbUser);
    config.setPassword(dbPass);
    config.setDriverClassName("org.postgresql.Driver");

    // 2. Fine-grained control (Hikari specific)
    config.setMaximumPoolSize(10); // Senior tip: match this to your load/DB capacity
    config.setMinimumIdle(2);
    config.setIdleTimeout(300000); // 5 minutes
    config.setConnectionTimeout(20000); // 20 seconds
    config.setPoolName("Yapily-HikariPool");

    // 3. Fintech-specific optimization: Prevent connection leaks
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

    return new HikariDataSource(config);
  }
}
