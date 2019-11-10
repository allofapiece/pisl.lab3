package by.pisl.lab3.repository;

import by.pisl.lab3.repository.exception.DAOException;

import java.util.List;

/**
 * Class provides access to entities through database.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
public interface DataBaseDAO<T> {
    /**
     * @param id id of requested by.pisl.lab3.entity
     * @return T found by.pisl.lab3.entity
     * @throws EntityNotFoundException if by.pisl.lab3.entity did not found
     */
    T find(long id) throws DAOException;

    /**
     * @return List the list of found entities
     */
    List<T> findAll() throws DAOException;

    /**
     * @param entity object of by.pisl.lab3.entity, that need to save in database
     */
    void create(T entity);

    /**
     * @param entity object of by.pisl.lab3.entity, that need to delete from database
     * @throws DAOException if by.pisl.lab3.entity did not found
     */
    void delete(T entity) throws DAOException;

    /**
     * @param entity object of by.pisl.lab3.entity, that need to update in database. New
     *               object will be inserted in database instead of old
     * @throws DAOException if by.pisl.lab3.entity did not found.
     */
    void update(T entity) throws DAOException;
}
