package org.example.repositories;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    void save(T entity);

    List<T> findAll();

    Optional<T> findById(Integer id);

    void deleteById(Integer id);

    void deleteAll();

    boolean existsById(Integer id);
}
