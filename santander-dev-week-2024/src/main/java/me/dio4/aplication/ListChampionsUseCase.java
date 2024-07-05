package me.dio4.aplication;

import java.util.List;

import me.dio4.domain.model.Champions;
import me.dio4.domain.ports.ChampionsRepository;

public record ListChampionsUseCase(ChampionsRepository repository) {

    public List<Champions>findAll(){
        return repository.findAll();
    }
}
