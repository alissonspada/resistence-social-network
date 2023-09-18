package org.example.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T extends GenericEntity> implements Repository<T> {
    private final List<T> absRepoList = new ArrayList<>();
    private static Integer id;

    public void save(T entity) {
        entity.setEntityId(id);
        absRepoList.add(entity);
        id++;
    }

    public List<T> findAll() {
        return absRepoList;
    }

    public Optional<T> findById(Integer id) {
        return absRepoList.stream().filter(o -> o.getEntityId().equals(id)).findFirst();
    }

    public void deleteById(Integer id) {
        absRepoList.remove(absRepoList.stream().filter(o -> o.getEntityId().equals(id)).findFirst().orElseThrow());
    }

    public void deleteAll() {
        absRepoList.clear();
    }

    public boolean existsById(Integer id) {
        return findById(id).isPresent();
    }
}