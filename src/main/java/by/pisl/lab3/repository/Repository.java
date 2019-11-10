package by.pisl.lab3.repository;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0.0
 */
public interface Repository<T> {
    List<T> findAll();

    Optional<T> findById(Long id);

    Optional<T> findRandom();

    T save(T entity);
}
