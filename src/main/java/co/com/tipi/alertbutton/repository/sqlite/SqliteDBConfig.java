package co.com.tipi.alertbutton.repository.sqlite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class SqliteDBConfig {

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

    @Bean(name = "sqliteDBConnection")
    public Connection sqliteDBConnection() {
        Connection conn = null;
        try {
            String driver = "org.sqlite.JDBC";
            Class.forName(driver);
            // SQLite connection string
            String dbFile = System.getProperty("user.home") + "/IdeaProjects/alertbutton/alertbutton.db";
            logger.info("--dbFile " + dbFile);
            String url = "jdbc:sqlite:"+dbFile;

            try {
                conn = DriverManager.getConnection(url);
                logger.info("--Connected to database sqlite");
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
