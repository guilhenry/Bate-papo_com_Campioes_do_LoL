package me.dio4.aplication;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import me.dio4.domain.model.Champions;

@SpringBootTest
public class ListChampionsUseCaseIntegrate {
    @Autowired
    private ListChampionsUseCase listChampionsUseCase;

    @Test
    public void testListChampions(){
        List<Champions> champions = listChampionsUseCase.findAll();
        Assertions.assertEquals(12 ,champions.size());
    }
}
