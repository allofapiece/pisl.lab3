package by.pisl.lab3.repository.exception;

public class ConnectionPoolException extends DAOException {
    public ConnectionPoolException(String message, Exception e){
        super(message);
    }
}

