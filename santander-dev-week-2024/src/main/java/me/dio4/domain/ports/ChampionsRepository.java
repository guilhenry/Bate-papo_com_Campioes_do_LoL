package me.dio4.domain.ports;


import java.util.List;
import java.util.Optional;

import me.dio4.domain.model.Champions;

public interface ChampionsRepository {
    List<Champions> findAll();
    Optional<Champions> findById(Long id);
    
}