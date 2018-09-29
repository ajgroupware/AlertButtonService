package co.com.tipi.alertbutton.repository.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//@Configuration
public class MySqlDBConfig {

    private static final Logger logger = LoggerFactory.getLogger(MySqlDBConfig.class);

    //@Bean(name = "mysqlDBConnection")
    public Connection mysqlDBConnection() {
        Connection conn = null;
        try {
            String driver = "com.mysql.jdbc.Driver";
            //String driver = "com.mysql.cj.jdbc.Driver";

            Class.forName(driver);
            // connection string
            //String url = "jdbc:mysql://mysql-rfam-public.ebi.ac.uk:4497/Rfam"; //Base de datos de pruebas
            String url = "jdbc:mysql://localhost:3306/asteriskcdrdb"; //CMC Rionegro local (Base de datos Asterisk)
            //String url = "jdbc:mysql://192.168.0.3:3306/asteriskcdrdb"; //CMC Rionegro (Base de datos Asterisk)

            try {
                //conn = DriverManager.getConnection(url, "rfamro", null);
                conn = DriverManager.getConnection(url, "asteriskuser", "Pw.123456");
                //conn = DriverManager.getConnection(url, "root", "Pw.123456");
                logger.info("--Connected to database mysql");
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
