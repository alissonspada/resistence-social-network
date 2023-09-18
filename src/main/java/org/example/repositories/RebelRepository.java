package org.example.repositories;

import org.example.model.Rebel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RebelRepository extends AbstractRepository<Rebel> {
    public Optional<Rebel> findByName(String name) {
        return findAll()
                .stream()
                .filter(rebel -> rebel.getName().equals(name))
                .findFirst();
    }
}
