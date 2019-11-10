package by.pisl.lab3.repository;

import by.pisl.lab3.repository.exception.DAOException;

/**
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public interface Factory<T> {
    /**
     * @param type
     * @return T
     * @throws DAOException if implementations of this method throw child
     *                      exceptions of {@link DAOException}
     */
    T create(String type) throws DAOException;
}
