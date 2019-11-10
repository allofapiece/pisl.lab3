package by.pisl.lab3.repository.populator;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @version 1.0.0
 */
public interface EntityPopulator<T> {
    T populate(ResultSet resultSet);

    List<T> populateAll(ResultSet resultSet);

    PreparedStatement prepare(T entity, String query, Connection conn) throws IntrospectionException, SQLException, InvocationTargetException, IllegalAccessException;
}
