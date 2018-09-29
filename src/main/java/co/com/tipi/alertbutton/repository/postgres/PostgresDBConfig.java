package co.com.tipi.alertbutton.repository.postgres;


import co.com.tipi.alertbutton.repository.sqlite.SqliteDBConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//@Configuration
public class PostgresDBConfig {

    private static final Logger logger = LoggerFactory.getLogger(SqliteDBConfig.class);

    /*
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite:alertbutton.db");
        return dataSourceBuilder.build();
    }
    */

    //@Bean(name = "postgresDBConnection")
    public Connection postgresDBConnection() {
        Connection conn = null;
        try {
            String driver = "org.postgresql.Driver";
            Class.forName(driver);
            // connection string
            String url = "jdbc:postgresql://192.168.95.68:5432/nuevaeps_prod2";

            try {
                conn = DriverManager.getConnection(url, "postgres", "nEpsdB1");
                logger.info("--Connected to database postgresql");
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
