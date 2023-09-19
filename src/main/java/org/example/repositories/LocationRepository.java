package org.example.repositories;

import org.example.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepository {
    private final List<Location> locationsRepoList = new ArrayList<>();
    private static Integer id = 0;

    public void save(Location location) {
        location.setId(id);
        locationsRepoList.add(location);
        id++;
    }

    public List<Location> findAll() {
        return locationsRepoList;
    }

    public Optional<Location> findById(Integer id) {
        return locationsRepoList.stream().filter(o -> o.getId().equals(id)).findFirst();
    }

    public void deleteById(Integer id) {
        locationsRepoList.remove(locationsRepoList.stream().filter(o -> o.getId().equals(id)).findFirst().orElseThrow());
    }

    public void deleteAll() {
        locationsRepoList.clear();
    }

    public boolean existsById(Integer id) {
        return findById(id).isPresent();
    }

}
