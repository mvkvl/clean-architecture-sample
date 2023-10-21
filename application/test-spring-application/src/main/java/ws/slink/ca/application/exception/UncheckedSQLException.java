package ws.slink.ca.application.exception;

import java.sql.SQLException;

public class UncheckedSQLException extends RuntimeException {

    public UncheckedSQLException(SQLException e) {
        super(e);
    }

}
