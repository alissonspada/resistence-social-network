package org.example.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class AbstractRepository<T extends GenericEntity> implements Repository<T> {
    private final List<T> absRepoList = new ArrayList<>();

    public void save(T entity) {
        absRepoList.add(entity);
    }

    public List<T> findAll() {
        return absRepoList;
    }

    public Optional<T> findById(UUID id) {
        return absRepoList.stream().filter(o -> o.getId().equals(id)).findFirst();
    }

    public void deleteById(UUID id) {
        absRepoList.remove(absRepoList.stream().filter(o -> o.getId().equals(id)).findFirst().orElseThrow());
    }

    public void deleteAll() {
        absRepoList.clear();
    }

    public boolean existsById(UUID id) {
        return findById(id).isEmpty();
    }
}