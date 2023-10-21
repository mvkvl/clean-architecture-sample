package ws.slink.ca.application.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ws.slink.ca.application.exception.UncheckedSQLException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Supplier;

@Configuration
public class DatabaseConnectionProviderConfig {

//    private final HikariDataSource hikari;
//
//    public DatabaseConnectionProviderConfig(@Autowired HikariDataSource hikari) {
//        this.hikari = hikari;
//    }
//
//    @Bean
//    Supplier<Connection> dbConnectionProvider() {
//        return () -> {
//            try {
//                return hikari.getConnection();
//            } catch(SQLException e) {
//                throw new UncheckedSQLException(e);
//            }
//        };
//    }


}
