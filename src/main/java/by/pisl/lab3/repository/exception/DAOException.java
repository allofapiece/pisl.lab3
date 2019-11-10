package by.pisl.lab3.repository.exception;

import java.sql.SQLException;

public class DAOException extends SQLException {
    /**
     * Default constructor.
     */
    public DAOException() {
        super("Data Access Object error");
    }

    /**
     * @param message
     */
    public DAOException(String message) {
        super(message);
    }
}
