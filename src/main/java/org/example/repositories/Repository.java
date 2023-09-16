package org.example.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Repository<T> {

    void save(T entity);

    List<T> findAll();

    Optional<T> findById(UUID id);

    void deleteById(UUID id);

    void deleteAll();
}
