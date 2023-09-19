package org.example.repositories;

import org.example.model.Rebel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RebelRepository {
    public Optional<Rebel> findByName(String name) {
        return findAll()
                .stream()
                .filter(rebel -> rebel.getName().equals(name))
                .findFirst();
    }

    private final List<Rebel> rebelsRepoList = new ArrayList<>();
    private static Integer id = 0;

    public void save(Rebel rebel) {
        rebel.setId(id);
        rebelsRepoList.add(rebel);
        id++;
    }

    public List<Rebel> findAll() {
        return rebelsRepoList;
    }

    public Optional<Rebel> findById(Integer id) {
        return rebelsRepoList.stream().filter(o -> o.getId().equals(id)).findFirst();
    }

    public void deleteById(Integer id) {
        rebelsRepoList.remove(rebelsRepoList.stream().filter(o -> o.getId().equals(id)).findFirst().orElseThrow());
    }

    public void deleteAll() {
        rebelsRepoList.clear();
    }

    public boolean existsById(Integer id) {
        return findById(id).isPresent();
    }
}
